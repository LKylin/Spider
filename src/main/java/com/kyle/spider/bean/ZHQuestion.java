package com.kyle.spider.bean;

public class ZHQuestion {
	private long updated_time;
    private String question_type;
    private long created;
    private long id;
    private String title;
    private String type;
    private String url;
    public void setUpdated_time(long updated_time) {
         this.updated_time = updated_time;
     }
     public long getUpdated_time() {
         return updated_time;
     }

    public void setQuestion_type(String question_type) {
         this.question_type = question_type;
     }
     public String getQuestion_type() {
         return question_type;
     }

    public void setCreated(long created) {
         this.created = created;
     }
     public long getCreated() {
         return created;
     }

    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }
}
