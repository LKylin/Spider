package com.kyle.spider.bean;

public class ZHRewardInfo {
	private boolean is_rewardable;
	private String tagline;
	private int reward_member_count;
	private boolean can_open_reward;
	private int reward_total_money;

	public void setIs_rewardable(boolean is_rewardable) {
		this.is_rewardable = is_rewardable;
	}

	public boolean getIs_rewardable() {
		return is_rewardable;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTagline() {
		return tagline;
	}

	public void setReward_member_count(int reward_member_count) {
		this.reward_member_count = reward_member_count;
	}

	public int getReward_member_count() {
		return reward_member_count;
	}

	public void setCan_open_reward(boolean can_open_reward) {
		this.can_open_reward = can_open_reward;
	}

	public boolean getCan_open_reward() {
		return can_open_reward;
	}

	public void setReward_total_money(int reward_total_money) {
		this.reward_total_money = reward_total_money;
	}

	public int getReward_total_money() {
		return reward_total_money;
	}
}
