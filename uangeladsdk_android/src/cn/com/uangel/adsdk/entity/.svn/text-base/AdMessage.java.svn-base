package cn.com.uangel.adsdk.entity;

public class AdMessage {
	private String code = "0000";

	private String result = "0000";

	private String body = "";
	
	private byte[] picture;

	private String bodyLength = body.length() + "";

	private String header = "";

	public AdMessage() {

	}

	public String toString() {
		header = code + result + bodyLength;
		return header + getBody();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
		bodyLength = AdMessage.padRight((body.length()) + "", 8, ' ');
	}

	public String getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(String length) {
		bodyLength = length;
	}
	
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public static String padRight(String s, int l, char c) {
		if (s.length() > l) {
			s = s.substring(0, l - 1);
		} else {
			while (s.length() < l) {
				s += c;
			}
		}
		return s;
	}
}
