package vn.fpt.fsoft.intern517.olt.model.bo;

/**
 * TopicBO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 15, 2017        Nguyen Cong Huong          	Create
 */

import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.Topic;
import vn.fpt.fsoft.intern517.olt.model.dao.TopicDAO;

public class TopicBO {
	TopicDAO topicDAO = new TopicDAO();

	public ArrayList<Topic> getListTopic() {
		return topicDAO.getListTopic();
	}

	public void deleteTopic(String topicID) {
		topicDAO.deleteTopic(topicID);
	}

	public void cloneTopic(String topicID, String topicName, String date) {
		topicDAO.cloneTopic(topicID, topicName, date);
	}

	public boolean checkTopicName(String topicName) {
		return topicDAO.checkTopicName(topicName);
	}

	public int getTopicID(String topicName) {
		return topicDAO.getTopicID(topicName);
	}

	public void addTopic(String topicName, String cloneDay) {
		topicDAO.addTopic(topicName, cloneDay);
	}

	public void deleteTopic() {
		topicDAO.deleteTopic();
	}

	public ArrayList<Topic> getCurrentTopic() {
		return topicDAO.getCurrentTopic();
	}

	public String getTopicName(String topicID) {
		return topicDAO.getTopicName(topicID);
	}

	public void editTopicName(String topicID, String topicName) {
		topicDAO.editTopicName(topicID, topicName);
	}

	public boolean checkIP(String studentID, String topicID) {
		return topicDAO.checkIP(studentID, topicID);
	}

	public ArrayList<Topic> getListTopic2() {
		return topicDAO.getListTopic2();
	}
}
