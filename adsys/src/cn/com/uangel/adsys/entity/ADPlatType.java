package cn.com.uangel.adsys.entity;

import java.io.Serializable;

/**
 * 广告_平台_类别
 * 
 * @author Tree
 * 
 */
public class ADPlatType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer ad_id;
	/** 所属平台 */
	private String platform;
	private String type_name;

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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

}
