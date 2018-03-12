package com.kyle.spider.bean;

import java.util.List;

public class ZHANSViewpoint {
	private List<String> upvoted_followees;
	private boolean is_author;
	private boolean is_authorized;
	private boolean is_thanked;
	private boolean is_nothelp;
	private int voting;

	public void setUpvoted_followees(List<String> upvoted_followees) {
		this.upvoted_followees = upvoted_followees;
	}

	public List<String> getUpvoted_followees() {
		return upvoted_followees;
	}

	public void setIs_author(boolean is_author) {
		this.is_author = is_author;
	}

	public boolean getIs_author() {
		return is_author;
	}

	public void setIs_authorized(boolean is_authorized) {
		this.is_authorized = is_authorized;
	}

	public boolean getIs_authorized() {
		return is_authorized;
	}

	public void setIs_thanked(boolean is_thanked) {
		this.is_thanked = is_thanked;
	}

	public boolean getIs_thanked() {
		return is_thanked;
	}

	public void setIs_nothelp(boolean is_nothelp) {
		this.is_nothelp = is_nothelp;
	}

	public boolean getIs_nothelp() {
		return is_nothelp;
	}

	public void setVoting(int voting) {
		this.voting = voting;
	}

	public int getVoting() {
		return voting;
	}
}
