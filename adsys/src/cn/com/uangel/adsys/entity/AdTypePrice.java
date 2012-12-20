package cn.com.uangel.adsys.entity;

import java.io.Serializable;
import java.util.Date;

public class AdTypePrice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String ad_type;
	private double cur_money=0.0;
	private String is_newest;
	private Date change_time;
	public Date getChange_time() {
		return change_time;
	}
	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAd_type() {
		return ad_type;
	}
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	public double getCur_money() {
		return cur_money;
	}
	public void setCur_money(double cur_money) {
		this.cur_money = cur_money;
	}
	public String getIs_newest() {
		return is_newest;
	}
	public void setIs_newest(String is_newest) {
		this.is_newest = is_newest;
	}
	
	

}
