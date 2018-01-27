package com.weyko.shops.secure;

import android.os.Build;

import com.weyko.shops.config.Constant;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Description: AES加解密工具类
 * Created  by: weyko on 2017/7/10.
 */

public class AESUtil {
    private static final String CBC_PKCS5_PADDING = "AES/ECB/PKCS5Padding";//AES是加密方式 ECB是工作模式 PKCS5Padding是填充模式
    private static final String AES = "AES";
    /**
     * 加密
     * @return
     * @throws Exception
     */
    public static String encryptRandom(String privateKey, String content) throws Exception {
        byte[] rawKey = getRawKey(privateKey.getBytes());
        byte[] result = encrypt(rawKey, content.getBytes());
        return toHex(result);
    }
    /**
     * 解密
     * @param encrypted
     * @return
     * @throws Exception
     */
    public static String decryptRandom(String privateKey, String encrypted) throws Exception {
        byte[] rawKey = getRawKey(privateKey.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    // 对密钥进行处理
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        byte[] raw=null;
        SecureRandom sr = null;
        SecretKey skey=null;
        if (android.os.Build.VERSION.SDK_INT >=  Build.VERSION_CODES.M){
            //https://android.googlesource.com/platform/development/+/master/samples/BrokenKeyDerivation/src/com/example/android/brokenkeyderivation/BrokenKeyDerivationActivity.java?autodive=0%2F
//            skey=getSecretKey(String.valueOf(seed));
        }else if (android.os.Build.VERSION.SDK_INT >=  Build.VERSION_CODES.JELLY_BEAN_MR1) { // SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        if(sr!=null){
            sr.setSeed(seed);
            kgen.init(256, sr); //256 bits or 128 bits,192bits
            //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
            skey = kgen.generateKey();
        }
        if(skey!=null)
          raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2*buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }
    private final static String HEX = "0123456789ABCDEF";
    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
    }
    /**************方式二（使用系统默认的SecureRandom）**************/
    //https://my.oschina.net/wangxnn/blog/390346?p=1
    /**
     *加密
     */
    public static String encode(String content) throws Exception {
        //加密之后的字节数组,转成16进制的字符串形式输出
        return parseByte2HexStr(encrypt(Constant.publicKey.getBytes(),content.getBytes()));
    }
    /**
     *解密
     */
    public static String decode(String content) throws Exception {
        //解密之前,先将输入的字符串按照16进制转成二进制的字节数组,作为待解密的内容输入
        byte[] b = decrypt(Constant.publicKey.getBytes(),parseHexStr2Byte(content));
        return new String(b);
    }
    /**
     * 将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
