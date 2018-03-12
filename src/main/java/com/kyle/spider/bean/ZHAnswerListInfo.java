package com.kyle.spider.bean;

public class ZHAnswerListInfo {
	private boolean is_end;
	private int totals;
	private String previous;
	private boolean is_start;
	private String next;

	public void setIs_end(boolean is_end) {
		this.is_end = is_end;
	}

	public boolean getIs_end() {
		return is_end;
	}

	public void setTotals(int totals) {
		this.totals = totals;
	}

	public int getTotals() {
		return totals;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getPrevious() {
		return previous;
	}

	public void setIs_start(boolean is_start) {
		this.is_start = is_start;
	}

	public boolean getIs_start() {
		return is_start;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getNext() {
		return next;
	}
}
