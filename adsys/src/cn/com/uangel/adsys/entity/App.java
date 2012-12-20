package cn.com.uangel.adsys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用类
 * 
 * @author Tree
 * 
 */
public class App implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String app_name;
	private String app_type;
	private String pid;
	private Date create_time;
	private Integer mem_id;
	private String app_platform;
	private String pakage_name;
	private String app_crowd;
	private String app_gender;
	private String apk_url;
	private String app_state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getApp_platform() {
		return app_platform;
	}

	public void setApp_platform(String app_platform) {
		this.app_platform = app_platform;
	}
	public String getPakage_name() {
		return pakage_name;
	}
	public void setPakage_name(String pakage_name) {
		this.pakage_name = pakage_name;
	}
	public String getApp_crowd() {
		return app_crowd;
	}
	public void setApp_crowd(String app_crowd) {
		this.app_crowd = app_crowd;
	}
	public String getApp_gender() {
		return app_gender;
	}
	public void setApp_gender(String app_gender) {
		this.app_gender = app_gender;
	}
	public String getApk_url() {
		return apk_url;
	}
	public void setApk_url(String apk_url) {
		this.apk_url = apk_url;
	}
	public String getApp_state() {
		return app_state;
	}
	public void setApp_state(String app_state) {
		this.app_state = app_state;
	}

}
