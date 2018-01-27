package com.weyko.shops.base;

import java.io.Serializable;

/**
 * Bean基类 提取接口共用字段。
 */
public class BaseBean implements Serializable {
	private int instruct;//指令
	private int code=-1;//错误码
	private String msg;//提示信息

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isOk(){
		return code==0;
	}

	public int getInstruct() {
		return instruct;
	}

	public void setInstruct(int instruct) {
		this.instruct = instruct;
	}

	@Override
	public String toString() {
		return "{ instruct:" +instruct
				+" code:"+code
				+" msg:"+msg+
				"}";
	}
}