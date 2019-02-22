package com.example.userservice.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Msg implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//100成功，200失败
	private int code;


	private String msg;

	
	@Override
	public String toString() {
		return "Msg [code=" + code + ", msg=" + msg + ", map=" + map + "]";
	}

	private Map<String, Object> map = new HashMap<String, Object>();

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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public static Msg success(String message) {
		Msg msg = new Msg();
		msg.setCode(100);
		msg.setMsg(message);
		return msg;
	}

	public static Msg error(String message) {
		Msg msg = new Msg();
		msg.setCode(200);
		msg.setMsg(message);
		return msg;
	}

	public Msg add(String key, Object value) {
		map.put(key, value);
		return this;
	}

}
