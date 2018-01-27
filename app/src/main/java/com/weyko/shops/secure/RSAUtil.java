package com.weyko.shops.secure;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
public class RSAUtil {

	/**
	 * 生成RSA密钥对
	 * 
	 * @param 
	 * @return keyset
	 */
    public static void generateKey() {  
        try {  
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");  
            kpg.initialize(1024);  
            KeyPair kp = kpg.genKeyPair();  
            PublicKey pbkey =  kp.getPublic();  
            PrivateKey prkey =  kp.getPrivate();  
            byte[] pbk = pbkey.getEncoded();
            byte[] prk = prkey.getEncoded();
            System.out.println("pbkey:" + encryptBASE64(pbk));
            System.out.println("prkey:" + encryptBASE64(prk));
        } catch (Exception e) {  
        	System.out.println("Error:" + e);
        }  
    }  

	/**
	 * RSA公钥加密
	 * 
	 * @param 明文
	 * @param 公钥
	 * @return 密文
	 */
    public static String encryptByPublicKey(String data, String key) throws Exception {
        // 得到公钥
    	byte[] publicKey =  decryptBASE64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey keyPublic = (RSAPublicKey) kf.generatePublic(keySpec);
        // 加密数据
        Cipher cp = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cp.init(Cipher.ENCRYPT_MODE, keyPublic);
        
        // 模长  
        int key_len = keyPublic.getModulus().bitLength() / 8;  
        // 加密数据长度 <= 模长-11  
        String[] datas = splitString(data, key_len - 11);  
        String mi = "";  
        //如果明文长度大于模长-11则要分组加密  
        for (String s : datas) {  
            mi += bcd2Str(cp.doFinal(s.getBytes()));  
        }  
        System.out.println("encrypted:" + mi);
        return mi;  
        
    }

	/**
	 * RSA私钥解密
	 * 
	 * @param 密文
	 * @param 私钥
	 * @return 明文
	 */
    public static String decryptByPrivateKey(String encrypted, String key) throws Exception {
        // 得到私钥
    	byte[] privateKey =  decryptBASE64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey keyPrivate = (RSAPrivateKey)kf.generatePrivate(keySpec);

        // 解密数据
        Cipher cp = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cp.init(Cipher.DECRYPT_MODE, keyPrivate);
        
        //模长  
        int key_len = keyPrivate.getModulus().bitLength() / 8;  
        byte[] bytes = encrypted.getBytes();  
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);  
        System.err.println(bcd.length);  
        //如果密文长度大于模长则要分组解密  
        String ming = "";  
        byte[][] arrays = splitArray(bcd, key_len);  
        for(byte[] arr : arrays){  
            ming += new String(cp.doFinal(arr));  
        }  
        System.out.println("decrypted:" + ming);
        return ming;  

    }

	public static String encryptBASE64(byte[] key) throws Exception {               
        return (new BASE64Encoder()).encodeBuffer(key);
    } 
	
	public static byte[] decryptBASE64(String key) throws Exception {               
        return (new BASE64Decoder()).decodeBuffer(key);
    } 
	
	/** 
     * ASCII码转BCD码 
     *  
     */  
    public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
        byte[] bcd = new byte[asc_len / 2];  
        int j = 0;  
        for (int i = 0; i < (asc_len + 1) / 2; i++) {  
            bcd[i] = asc_to_bcd(ascii[j++]);  
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
        }  
        return bcd;  
    }  
    
    public static byte asc_to_bcd(byte asc) {  
        byte bcd;  
  
        if ((asc >= '0') && (asc <= '9'))  
            bcd = (byte) (asc - '0');  
        else if ((asc >= 'A') && (asc <= 'F'))  
            bcd = (byte) (asc - 'A' + 10);  
        else if ((asc >= 'a') && (asc <= 'f'))  
            bcd = (byte) (asc - 'a' + 10);  
        else  
            bcd = (byte) (asc - 48);  
        return bcd;  
    }  
    
	/** 
     * BCD转字符串 
     */  
    public static String bcd2Str(byte[] bytes) {  
        char temp[] = new char[bytes.length * 2], val;  
  
        for (int i = 0; i < bytes.length; i++) {  
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);  
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
  
            val = (char) (bytes[i] & 0x0f);  
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
        }  
        return new String(temp);  
    } 
    
    /** 
     * 拆分字符串 
     */  
    public static String[] splitString(String string, int len) {  
        int x = string.length() / len;  
        int y = string.length() % len;  
        int z = 0;  
        if (y != 0) {  
            z = 1;  
        }  
        String[] strings = new String[x + z];  
        String str = "";  
        for (int i=0; i<x+z; i++) {  
            if (i==x+z-1 && y!=0) {  
                str = string.substring(i*len, i*len+y);  
            }else{  
                str = string.substring(i*len, i*len+len);  
            }  
            strings[i] = str;  
        }  
        return strings;  
    } 
    
    /** 
     *拆分数组  
     */  
    public static byte[][] splitArray(byte[] data,int len){  
        int x = data.length / len;  
        int y = data.length % len;  
        int z = 0;  
        if(y!=0){  
            z = 1;  
        }  
        byte[][] arrays = new byte[x+z][];  
        byte[] arr;  
        for(int i=0; i<x+z; i++){  
            arr = new byte[len];  
            if(i==x+z-1 && y!=0){  
                System.arraycopy(data, i*len, arr, 0, y);  
            }else{  
                System.arraycopy(data, i*len, arr, 0, len);  
            }  
            arrays[i] = arr;  
        }  
        return arrays;  
    }  
    
	public static void main(String[] args) throws Exception {
        //生成密钥对
		//generateKey();
		
		//测试
		String plainText = "abcd1234";
		System.out.println("plainText:{}"+ plainText);
		String pbk = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfWPRj0SsegirwLLrbFDSqS9yPucfUaAnd+WYsK1G3eZLOWOwUTH2HSGy/v/8humDaOuBmFxmBNOgM24JiebkU7DpO8j9E4szsRI+mZ/m0dLUYUB54qFZ9cUs5qnwaQVng9G8/T/9VwgRuaLYIJFCkLPQgonpOzkUaVTXPkZOo+wIDAQAB";
    	
		String pik = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ9Y9GPRKx6CKvAsutsUNKpL3I+5x9RoCd35ZiwrUbd5ks5Y7BRMfYdIbL+//yG6YNo64GYXGYE06AzbgmJ5uRTsOk7yP0TizOxEj6Zn+bR0tRhQHnioVn1xSzmqfBpBWeD0bz9P/1XCBG5otggkUKQs9CCiek7ORRpVNc+Rk6j7AgMBAAECgYBU1F1eWjczSaakL5vWD16uHi6wEP4R1MUT97Zt4y3gmAcQdQRRFhtpjqPePM8M3JS8+MUHdFquj0TNu3ohBJ4Dbae3IFQjrGy+AYQr9dwEzYs21y7auZ418dK9L70S3a9jhNlYoOcdg9ZhlTKSmz08MI1XHVpYxc97kfQqTsHaeQJBAOVq1pCwrQ3uZzPfm6ALRhSRjjUbSOvh+xVRAZ+qxWHrg6t4aV5Nu3lIs2G4RAqwcP6oAWGfUFVYnJ+dyS7y9mUCQQCxz6XfpMRlTHKpI4Y/87HVOV2qlrGsaImbyYE7GrAf2HxowGFVduJtZHvkBtRPZYSyAKpD/haxRjvhhLXoUvvfAkAskqZfdEQbKc+ZT3+QB8C07FbB0KxJUB2VVajIGcjBrp1laj6mmNmsxcg8Wy21GfTKNWAXVHmqK0464NAetAvVAkEAhkasGbagxWq0z/vOvu62JbI7hL5mEC+lc2r5V3vw8yuVz7kwB81OcpPGZ02s+QRN7+pGqi/wTREf2U9R288G+wJAPDFXkkoLlFSBYB802fXACrchW5vWwjRS/+nnLMPQEFEHjNtP5n2LM9zJGkvkf5klw1W6r8okVsmVrV1itZF5Mg==";
		try {
			String encryptedStr = encryptByPublicKey(plainText,pbk);
			System.out.println("encryptedStr:" + encryptedStr);
			
			String decryptedStr = decryptByPrivateKey(encryptedStr, pik);
			System.out.println("decryptedStr:"+ decryptedStr);
			/*String encryptedStr = rsaEncrypt(plainText);
			System.out.println("encryptedStr:{}" + encryptedStr);
			String decryptedStr = rsaDecrypt(encryptedStr);
			System.out.println("decryptedStr:{}"+ decryptedStr);
			if (decryptedStr.equals(plainText)){
				System.out.println("Success");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//验签
/*        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs1MhXQrcDSUgx23qnXZKP0WSY746WEQxk+WIElmW/0/mbkCLqcSh5s4d6SATRrrdjcj5fB9eGe5+IPx9An9cKNrECCLuUFY3Wx+AV4rVPPJAAX/6AdxPxFuI+3+C6J7vNuuve8xk0gBjzXEoqzBdA6c2/LEaa2fO9X64/YEGD3C7DVPH4GDV6etpSQHHfSolnHem/yl3JO/NO6mJZRVm/7Wj8uISgpnPJwK8lJJO+i2MpEK7iT0tJejQM97C1FFVqHoqnfeSnBMOCI0nlXHaIrDBVpK/wXu9oquMV0xJw/tmsfygYwqX5S6UsHAiJ6eKZOv8HvOoXSNzehgpiUSyUwIDAQAB";
        String params = "FB131B1AB528B66DFF59B872B512FB652CE1EC74BC627F697776F60E1D05E7860DF4F842E2F4694E880F1FD5F830293F67E3B4796CCFE86E8D67DF4FEE4B0EC5EA9D359CF90918A980B6D31825BD7B54D4FFE769E2CF78A289B586230DD24DB4B7EBD442AC0FCE70A95DDA79734037B804D2C5557C4644CE70D7DEA69D885488F47B0E7C67F2E0F01B7069EE3C78660CE537C57CF00256C76EBD7E43218857B419C50D9AD064E1A4207194EA437DE84F6F2389058885F3B790AB8608CA4C44E4937AA6DAC036DD535273DEDA5930AEC0C8EACF82468893C8B5876E2762E14C61950F84085E461497DDDF45B0EEE96C6095FD0F120D2DBF6205E03255F1DE0F98963D633398A344A6671ECC14DE2C9085A3A93640BFB02C12A2392642158896988D5FCDEF32BD23B1D36C5B7CB9DE9014EA12F36C812BC0868AC6F6A3886D74B5D447D6C83DC848FAC05A25514E9B318EEEB93E9ADDBAE9578B2C404B7BFDB4720BC911021F234123";
        String signature = "5C4C2994B9CDA6C37582B2C9FB4567B9307308F5BF133DE12CBED1F037183368A03F03EFA53F7F8FD23E8BA963F63AAB7928A492284BA50F07F17EC9466E2BF59DC1F935B307F35821DAC77712BD514E36A370C71FBC5150BA1D49E0CC81314FE3CDB92F142824E3CB330DBC7A03B76251DD5811EDAC3883BD77C337DD2D30D536BE88240E8E87C89F00F6E1878CE66CFAE38413496E32C541BE5A9058DF884955D32C0DE91837B28F212562CADEA605B6F9850EEF202B39D01A2F5B1D43F0694C296441E1F17DA13619CD5DB4FD67E86DEAC0041B6CAF7C34AB3A5B8AC1B7D15160807B208DFC5A14B7A8AECD51D102B7E4EB2D769B80D1B5BDF41B0FAD81A9";
        System.out.println("验签结果："+verify(params.getBytes(), publicKey, signature));
*/		
		//提取参数
/*		final String desKey = "c518de1eb7a54b951d0e5b15c8024869";// DES密钥
		String encryptdStr = "FB131B1AB528B66DFF59B872B512FB652CE1EC74BC627F697776F60E1D05E7860DF4F842E2F4694E880F1FD5F830293F67E3B4796CCFE86E8D67DF4FEE4B0EC5EA9D359CF90918A980B6D31825BD7B54D4FFE769E2CF78A289B586230DD24DB4B7EBD442AC0FCE7037FB8455D130F25E04D2C5557C4644CE70D7DEA69D885488F47B0E7C67F2E0F0B38639E062FD92B8E826ED58650C4AD56EBD7E43218857B4129FBA772454CC61207194EA437DE84F6F2389058885F3B790AB8608CA4C44E4937AA6DAC036DD535273DEDA5930AEC0C8EACF82468893C8B5876E2762E14C61950F84085E461497DDDF45B0EEE96C6095FD0F120D2DBF62C4DECCBF5B60FFC0EB7B9E23E2E10FF2E1E656B3828AE45D119CC3751DBC4D4404EF413BBDE79007CA998BE927128EACD9835B3D9E95964F67ECD5E0C0DDD8C43181BA09F4B7134DFB128CD22EEBF4D91C97E4BB6A986AEA2CA4B4CE9A05AFFC";// 加密字符串
		byte[] desByte = desEdeEcbPkcs5Decrypt(hexString2ByteArray(encryptdStr), hexString2ByteArray(desKey)); 
		System.out.println("3DES解密：" + new String( desByte ,"UTF-8"));*/
		
	}

	

}
