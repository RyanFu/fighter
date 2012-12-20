package cn.com.uangel.adsys.entity;

import java.io.Serializable;

/**
 * 统计信息类
 * 
 * @author Tree
 * 
 */
public class AdStatInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String date;
	private Integer show_times;
	private Integer click_times;
	private Double click_rate;
	private Double cpmPay;// CPM消费金额
	private Double cpcPay;

	public Double getCpmPay() {
		return cpmPay;
	}

	public void setCpmPay(Double cpmPay) {
		this.cpmPay = cpmPay;
	}

	public Double getCpcPay() {
		return cpcPay;
	}

	public void setCpcPay(Double cpcPay) {
		this.cpcPay = cpcPay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getShow_times() {
		return show_times;
	}

	public void setShow_times(Integer show_times) {
		this.show_times = show_times;
	}

	public Integer getClick_times() {
		return click_times;
	}

	public void setClick_times(Integer click_times) {
		this.click_times = click_times;
	}

	public Double getClick_rate() {
		return click_rate;
	}

	public void setClick_rate(Double click_rate) {
		this.click_rate = click_rate;
	}


}
