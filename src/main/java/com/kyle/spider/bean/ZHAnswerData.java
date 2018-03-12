package com.kyle.spider.bean;

import java.util.List;

public class ZHAnswerData {
	private boolean admin_closed_comment;
	private int comment_count;
	private String collapsed_by;
	private long updated_time;
	private String collapse_reason;
	private String comment_permission;
	private String extras;
	private boolean is_copyable;
	private String reshipment_settings;
	private String type;
	private String content;
	private ZHCommentStatus can_comment;
	private long id;
	private ZHANSViewpoint relationship;
	private ZHANSRelevantInfo relevant_info;
	private long created_time;
	private ZHSuggestEdit suggest_edit;
	private String thumbnail;
	private ZHQuestion question;
	private ZHAuthor author;
	private boolean is_sticky;
	private String editable_content;
	private boolean is_collapsed;
	private int voteup_count;
	private String url;
	private boolean is_normal;
	private ZHRewardInfo reward_info;
	private String excerpt;
	private List<String> annotation_action;

	public void setAdmin_closed_comment(boolean admin_closed_comment) {
		this.admin_closed_comment = admin_closed_comment;
	}

	public boolean getAdmin_closed_comment() {
		return admin_closed_comment;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setCollapsed_by(String collapsed_by) {
		this.collapsed_by = collapsed_by;
	}

	public String getCollapsed_by() {
		return collapsed_by;
	}

	public void setUpdated_time(long updated_time) {
		this.updated_time = updated_time;
	}

	public long getUpdated_time() {
		return updated_time;
	}

	public void setCollapse_reason(String collapse_reason) {
		this.collapse_reason = collapse_reason;
	}

	public String getCollapse_reason() {
		return collapse_reason;
	}

	public void setComment_permission(String comment_permission) {
		this.comment_permission = comment_permission;
	}

	public String getComment_permission() {
		return comment_permission;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public String getExtras() {
		return extras;
	}

	public void setIs_copyable(boolean is_copyable) {
		this.is_copyable = is_copyable;
	}

	public boolean getIs_copyable() {
		return is_copyable;
	}

	public void setReshipment_settings(String reshipment_settings) {
		this.reshipment_settings = reshipment_settings;
	}

	public String getReshipment_settings() {
		return reshipment_settings;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setCan_comment(ZHCommentStatus can_comment) {
		this.can_comment = can_comment;
	}

	public ZHCommentStatus getCan_comment() {
		return can_comment;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setRelationship(ZHANSViewpoint relationship) {
		this.relationship = relationship;
	}

	public ZHANSViewpoint getRelationship() {
		return relationship;
	}

	public void setRelevant_info(ZHANSRelevantInfo relevant_info) {
		this.relevant_info = relevant_info;
	}

	public ZHANSRelevantInfo getRelevant_info() {
		return relevant_info;
	}

	public void setCreated_time(long created_time) {
		this.created_time = created_time;
	}

	public long getCreated_time() {
		return created_time;
	}

	public void setSuggest_edit(ZHSuggestEdit suggest_edit) {
		this.suggest_edit = suggest_edit;
	}

	public ZHSuggestEdit getSuggest_edit() {
		return suggest_edit;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setQuestion(ZHQuestion question) {
		this.question = question;
	}

	public ZHQuestion getQuestion() {
		return question;
	}

	public void setAuthor(ZHAuthor author) {
		this.author = author;
	}

	public ZHAuthor getAuthor() {
		return author;
	}

	public void setIs_sticky(boolean is_sticky) {
		this.is_sticky = is_sticky;
	}

	public boolean getIs_sticky() {
		return is_sticky;
	}

	public void setEditable_content(String editable_content) {
		this.editable_content = editable_content;
	}

	public String getEditable_content() {
		return editable_content;
	}

	public void setIs_collapsed(boolean is_collapsed) {
		this.is_collapsed = is_collapsed;
	}

	public boolean getIs_collapsed() {
		return is_collapsed;
	}

	public void setVoteup_count(int voteup_count) {
		this.voteup_count = voteup_count;
	}

	public int getVoteup_count() {
		return voteup_count;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setIs_normal(boolean is_normal) {
		this.is_normal = is_normal;
	}

	public boolean getIs_normal() {
		return is_normal;
	}

	public void setReward_info(ZHRewardInfo reward_info) {
		this.reward_info = reward_info;
	}

	public ZHRewardInfo getReward_info() {
		return reward_info;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setAnnotation_action(List<String> annotation_action) {
		this.annotation_action = annotation_action;
	}

	public List<String> getAnnotation_action() {
		return annotation_action;
	}
}
