package cn.com.uangel.adsys.entity;

import java.io.Serializable;

/**
 * 广告展现方式信息类
 * 
 * @author Tree
 * 
 */
public class ADShowType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return id + "‖" + ad_id + "‖" + show_type_name + "‖" + title + "‖" + ad_info_url + "‖" + click_effect + "‖"
				+ ad_detail + "‖" + click_url + "‖" + pay_mode + price;
	}

	private Integer id;
	private Integer ad_id;
	/** 条幅型，插屏型等 */
	private String show_type_name;
	/** 广告标题 */
	private String title;
	/** 广告图片等存放路径 */
	private String ad_info_url;
	/** 点击效果 */
	private String click_effect;
	/** 广告详细描述 */
	private String ad_detail;
	/** 点击后跳转的地址 */
	private String click_url;
	/** 支付方式 */
	private String pay_mode;
	/** 竞价价格 */
	private Double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAd_id() {
		return ad_id;
	}

	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}

	public String getShow_type_name() {
		return show_type_name;
	}

	public void setShow_type_name(String show_type_name) {
		this.show_type_name = show_type_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAd_info_url() {
		return ad_info_url;
	}

	public void setAd_info_url(String ad_info_url) {
		this.ad_info_url = ad_info_url;
	}

	public String getClick_effect() {
		return click_effect;
	}

	public void setClick_effect(String click_effect) {
		this.click_effect = click_effect;
	}

	public String getAd_detail() {
		return ad_detail;
	}

	public void setAd_detail(String ad_detail) {
		this.ad_detail = ad_detail;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public String getPay_mode() {
		return pay_mode;
	}

	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
