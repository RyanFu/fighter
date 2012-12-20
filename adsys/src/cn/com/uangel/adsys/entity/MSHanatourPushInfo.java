package cn.com.uangel.adsys.entity;

import java.io.Serializable;

public class MSHanatourPushInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String token;
	private String device_type;
	private int current_book_count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public int getCurrent_book_count() {
		return current_book_count;
	}

	public void setCurrent_book_count(int current_book_count) {
		this.current_book_count = current_book_count;
	}

}
