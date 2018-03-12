package com.kyle.spider.bean;

import java.util.List;

public class ZHAnswerListResponse {
	private ZHAnswerListInfo paging;
	private List<ZHAnswerData> data;

	public void setPaging(ZHAnswerListInfo paging) {
		this.paging = paging;
	}

	public ZHAnswerListInfo getPaging() {
		return paging;
	}

	public void setData(List<ZHAnswerData> data) {
		this.data = data;
	}

	public List<ZHAnswerData> getData() {
		return data;
	}
}
