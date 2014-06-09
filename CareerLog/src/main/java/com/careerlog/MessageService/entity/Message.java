package com.careerlog.MessageService.entity;

import java.sql.Timestamp;
public class Message {
	private int messageId;
	private int userId;
	private String userName;
	private String title;
	private String text;
	private Timestamp creationDate;
	private Timestamp lastEditDate;
	private String tags;
	private String messageTypeId;
	private int viewCount;
	private int score;
	private int commentCount;
	private int parentId;
	
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(Timestamp lastEditDate) {
		this.lastEditDate = lastEditDate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getMessageTypeId() {
		return messageTypeId;
	}
	public void setMessageTypeId(String messageTypeId) {
		this.messageTypeId = messageTypeId;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String toString(){
		return "Message Id:"+messageId+"\n User Id:"+userId+"\n User Name:"+userName+"\n Title:"+title+"\n Text:"+text+"\n Creation Data:"+creationDate+"\n Last Edit Date:"+"\n Tags:"+tags+
				"\n Message Type Id:"+messageTypeId+"\n View Count:"+viewCount+"\n Score:"+score+"\n Comment Count:"+commentCount+"\n Parent Id:"+parentId;
	}
	
}
