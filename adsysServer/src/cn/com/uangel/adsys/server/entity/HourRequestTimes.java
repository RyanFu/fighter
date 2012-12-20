package cn.com.uangel.adsys.server.entity;

import java.io.Serializable;

public class HourRequestTimes implements Serializable {

	private static final long serialVersionUID = 1L;

	private String hour; // 当前是哪个小时，如2011_12_23_14,23号下午14时
	private int times;

	public HourRequestTimes(String hour, int times) {
		super();
		this.hour = hour;
		this.times = times;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
