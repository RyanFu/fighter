package cn.com.uangel.adsdk.entity;

public class ClickMessage extends AdMessage {
	private String imei;//机器号
	private String appId;//应用ID
	private String marketId;//发布应用的ID
	public ClickMessage() {
		super();
		setCode("1013");
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	
	
}
