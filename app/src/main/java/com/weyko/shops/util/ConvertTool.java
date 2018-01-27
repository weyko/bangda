package com.weyko.shops.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.weyko.shops.base.BaseBean;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.login.RegisterBean;
import com.weyko.shops.task.get.TaskInfoBean;

import java.io.UnsupportedEncodingException;

/**
 * Description: 内容转换工具
 * Created  by: weyko on 2017/6/2.
 */

public class ConvertTool {
    /**
     * 根据返回的数据流，转化成对应的实体类l
     * @param data
     * @param clzz
     * @param <T>
     * @return
     */
    public static <T> T getObject(byte[] data,Class<T>clzz) throws UnsupportedEncodingException {
        T result=null;
        String dataStr = new String(data, UDPConfig.BUFF_FORMAT);
        ShowUtil.d("dataStr="+dataStr);
        try {
            result=(T)JSONObject.parseObject(dataStr,clzz);
        }catch (JSONException e){
            result= (T) JSONObject.parseObject(dataStr,BaseBean.class);
        }
        return result;
    }
    /**
     * 根据返回的数据流，转化成对应的实体类
     * @param data
     * @param instruct
     * @param <T>
     * @return
     */
    public static <T> T getObject(byte[] data,int instruct) throws UnsupportedEncodingException {
        T result=null;
            result=(T)JSONObject.parseObject(new String(data,UDPConfig.BUFF_FORMAT),getParseClass(instruct));
        return result;
    }

    /**
     * 将对象转成json字符串
     * @param obj
     * @return
     */
    public static String toJsonStr(Object obj){
         return  JSONObject.toJSON(obj).toString();
    }
    public static String toString(Object obj){
        return String.valueOf(obj);
    }
    public static Class getParseClass(int instruct){
        Class clzz=null;
        switch (instruct){
            case UDPConfig.ACTION_REGISTER:
                clzz= RegisterBean.RegsiterData.class;
                break;
            case UDPConfig.ACTION_GET_TASK:
            case UDPConfig.ACTION_REQUEST_PAY:
                clzz= TaskInfoBean.class;
                break;
            default:
                clzz= BaseBean.class;
                break;
        }
        return clzz;
    }
}
