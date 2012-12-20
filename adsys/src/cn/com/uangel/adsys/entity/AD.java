package cn.com.uangel.adsys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 广告类
 * 
 * @author Tree
 * 
 */
public class AD implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Date start_date;
	private Date end_date;
	private String type;
	/** 日预算 */
	private Double max_pay_perday;
	private String description;
	/** 应用平台：Android，Ophone等 */
	private String app_platform;
	/** 展现方式 */
	private String show_type;
	private String show_src;
	private String img_word_type;
	private String title;
	private String img_path;
	private String pay_mode;
	private Integer mem_id;
	private String state;
	private String put_gender;
	private String put_crowd;
	private String put_time;
	/** 局部地区还是按省份投放 */
	private String zoneType;

	// 以下是功能属性，并非数据库字段
	private int showTimes;
	private int clickTimes;
	private double clickRate;
	private double cost;

	private double todayCost; // 今天消费金额，用于判断此广告是否以到达max_pay_perday

	private List<ADShowType> showTypes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getMax_pay_perday() {
		return max_pay_perday;
	}

	public void setMax_pay_perday(Double max_pay_perday) {
		this.max_pay_perday = max_pay_perday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApp_platform() {
		return app_platform;
	}

	public void setApp_platform(String app_platform) {
		this.app_platform = app_platform;
	}

	public String getShow_type() {
		return show_type;
	}

	public void setShow_type(String show_type) {
		this.show_type = show_type;
	}

	public String getShow_src() {
		return show_src;
	}

	public void setShow_src(String show_src) {
		this.show_src = show_src;
	}

	public String getImg_word_type() {
		return img_word_type;
	}

	public void setImg_word_type(String img_word_type) {
		this.img_word_type = img_word_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getPay_mode() {
		return pay_mode;
	}

	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}

	public Integer getMem_id() {
		return mem_id;
	}

	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(int showTimes) {
		this.showTimes = showTimes;
	}

	public int getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(int clickTimes) {
		this.clickTimes = clickTimes;
	}

	public double getClickRate() {
		return clickRate;
	}

	public void setClickRate(double clickRate) {
		this.clickRate = clickRate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getPut_gender() {
		return put_gender;
	}

	public void setPut_gender(String put_gender) {
		this.put_gender = put_gender;
	}

	public String getPut_crowd() {
		return put_crowd;
	}

	public void setPut_crowd(String put_crowd) {
		this.put_crowd = put_crowd;
	}

	public String getPut_time() {
		return put_time;
	}

	public void setPut_time(String put_time) {
		this.put_time = put_time;
	}

	public String getZoneType() {
		return zoneType;
	}

	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}

	public List<ADShowType> getShowTypes() {
		return showTypes;
	}

	public void setShowTypes(List<ADShowType> showTypes) {
		this.showTypes = showTypes;
	}

	public double getTodayCost() {
		return todayCost;
	}

	public void setTodayCost(double todayCost) {
		this.todayCost = todayCost;
	}

	@Override
	public String toString() {
		return "id: " + id + ",name: " + name + ",start_date: " + start_date + ",end_date: " + end_date + ",type: "
				+ type + "" + "," + "max_pay_perday: " + max_pay_perday + "" + ",mem_id: " + mem_id + ",state: "
				+ state;
	}

}
