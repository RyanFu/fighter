package cn.com.uangel.adsys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员类
 * 
 * @author Tree
 * 
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String password;
	private Date regist_time;
	private String name;
	private String telephone;
	private String cellphone;
	private String account_type;
	private String qq;
	private String com_name;
	/** 企业网址 */
	private String com_homepage;
	private String com_address;
	private String zipcode;
	private String account_attr;
	private String invoice_able;
	private String get_money_mode;
	
	private String open_bank;
	private String account_number;
	private String open_name;
	private String address_bank;
	private String zipcode_bank;
	
	
	
	
	private Double ad_balance=0.0;
	private Double income_balance=0.0;
	private String state;
	private String ser_num;
	private String pay_mode;

	public String getPay_mode() {
		return pay_mode;
	}

	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_homepage() {
		return com_homepage;
	}

	public void setCom_homepage(String com_homepage) {
		this.com_homepage = com_homepage;
	}

	public String getCom_address() {
		return com_address;
	}

	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAccount_attr() {
		return account_attr;
	}

	public void setAccount_attr(String account_attr) {
		this.account_attr = account_attr;
	}

	public String getInvoice_able() {
		return invoice_able;
	}

	public void setInvoice_able(String invoice_able) {
		this.invoice_able = invoice_able;
	}

	public String getGet_money_mode() {
		return get_money_mode;
	}

	public void setGet_money_mode(String get_money_mode) {
		this.get_money_mode = get_money_mode;
	}

	public Double getAd_balance() {
		return ad_balance;
	}

	public void setAd_balance(Double ad_balance) {
		this.ad_balance = ad_balance;
	}

	public Double getIncome_balance() {
		return income_balance;
	}

	public void setIncome_balance(Double income_balance) {
		this.income_balance = income_balance;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSer_num() {
		return ser_num;
	}

	public void setSer_num(String ser_num) {
		this.ser_num = ser_num;
	}

	public String getOpen_bank() {
		return open_bank;
	}

	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getOpen_name() {
		return open_name;
	}

	public void setOpen_name(String open_name) {
		this.open_name = open_name;
	}

	public String getAddress_bank() {
		return address_bank;
	}

	public void setAddress_bank(String address_bank) {
		this.address_bank = address_bank;
	}

	public String getZipcode_bank() {
		return zipcode_bank;
	}

	public void setZipcode_bank(String zipcode_bank) {
		this.zipcode_bank = zipcode_bank;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
