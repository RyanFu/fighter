package cn.com.uangel.adsys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值记录类
 * 
 * @author Tree
 * 
 */
public class RechargeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer mem_id;
	private Date recharge_time;
	private String ord_number;
	private Double recharge_count=0.0;
	private Double real_count=0.0;
	private String state;
	private String bank_num;

	public String getBank_num() {
		return bank_num;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMem_id() {
		return mem_id;
	}

	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}

	public Date getRecharge_time() {
		return recharge_time;
	}

	public void setRecharge_time(Date recharge_time) {
		this.recharge_time = recharge_time;
	}

	public String getOrd_number() {
		return ord_number;
	}

	public void setOrd_number(String ord_number) {
		this.ord_number = ord_number;
	}

	public Double getRecharge_count() {
		return recharge_count;
	}

	public void setRecharge_count(Double recharge_count) {
		this.recharge_count = recharge_count;
	}

	public Double getReal_count() {
		return real_count;
	}

	public void setReal_count(Double real_count) {
		this.real_count = real_count;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
