package com.kyle.spider.bean;

public class ZHSuggestEdit {
	private String reason;
	private ZHANSUnnormalDetails unnormal_details;
	private String tip;
	private String title;
	private String url;
	private boolean status;

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setUnnormal_details(ZHANSUnnormalDetails unnormal_details) {
		this.unnormal_details = unnormal_details;
	}

	public ZHANSUnnormalDetails getUnnormal_details() {
		return unnormal_details;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTip() {
		return tip;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}
}
