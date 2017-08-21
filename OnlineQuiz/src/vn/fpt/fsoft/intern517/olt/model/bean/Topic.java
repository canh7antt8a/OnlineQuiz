package vn.fpt.fsoft.intern517.olt.model.bean;

/**
 * Topic.java
 * 
 * Copyright
 * 
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- June
 * 15, 2017 Nguyen Cong Huong Create
 */
public class Topic {
	private int topicID;
	private String topicName;
	private String cloneDay;

	public String getCloneDay() {
		return cloneDay;
	}

	public void setCloneDay(String cloneDay) {
		this.cloneDay = cloneDay;
	}

	public int getTopicID() {
		return topicID;
	}

	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

}
