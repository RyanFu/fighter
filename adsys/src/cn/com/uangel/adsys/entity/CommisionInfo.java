package cn.com.uangel.adsys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 佣金发放记录
 * 
 * @author Tree
 * 
 */
public class CommisionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer mem_id;
	private String commision_num;
	private String bank_name;
	private String bank_num;
	private Date start_time;
	private Date end_time;
	private Double income=0.0;
	private Double deduct_count=0.0;
	private Double full_count=0.0;
	private Double poundage=0.0;
	private Double real_count=0.0;
	private String state;

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

	public String getCommision_num() {
		return commision_num;
	}

	public void setCommision_num(String commision_num) {
		this.commision_num = commision_num;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_num() {
		return bank_num;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getDeduct_count() {
		return deduct_count;
	}

	public void setDeduct_count(Double deduct_count) {
		this.deduct_count = deduct_count;
	}

	public Double getFull_count() {
		return full_count;
	}

	public void setFull_count(Double full_count) {
		this.full_count = full_count;
	}

	public Double getPoundage() {
		return poundage;
	}

	public void setPoundage(Double poundage) {
		this.poundage = poundage;
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
