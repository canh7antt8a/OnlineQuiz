package vn.fpt.fsoft.intern517.olt.model.bo;

/**
 * ExamBO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 29, 2017        Nguyen Cong Huong          	Create
 */

import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.Exam;
import vn.fpt.fsoft.intern517.olt.model.dao.ExamDAO;

public class ExamBO {
	ExamDAO examDAO = new ExamDAO();

	public void resetID() {
		examDAO.resetID();
	}

	public ArrayList<Exam> getListIP() {
		return examDAO.getListIP();
	}

	public void deleteIP(String examID) {
		examDAO.deleteIP(examID);
	}

	public void changeAllIP(String classID) {
		examDAO.changeAllIP(classID);
	}

	public ArrayList<Exam> getListIPDuplicate() {
		return examDAO.getListIPDuplicate();
	}

	public void editResult(String studentID, String topiID, String result) {
		examDAO.editResult(studentID, topiID, result);
	}
	
	public ArrayList<Exam> getListResult(String classID) {
		return examDAO.getListResult(classID);
	}
	
	public ArrayList<Exam> getListResult(int topicID) {
		return examDAO.getListResult(topicID);
	}
	
	public void addTimeIP(String studentID, String topicID, String startTime,
			String addressIP) {
		examDAO.addTimeIP(studentID, topicID, startTime, addressIP);
	}
	
	public boolean checkIP(String studentID, String topicID, String addressIP) {
		return examDAO.checkIP(studentID, topicID, addressIP);
	}
	
	public void updateTime(String studentID, String topicID, String endTime) {
		examDAO.updateTime(studentID, topicID, endTime);
	}
	
	public String checkEndTime(String endTime, String studentID, String topicID) {
		return examDAO.checkEndTime(endTime, studentID, topicID);
	}
	
	public String getAddressIP(String studentID) {
		return examDAO.getAddressIP(studentID);
	}
	
	public String checkEndTime(String examID) {
		return examDAO.checkEndTime(examID);
	}
}
