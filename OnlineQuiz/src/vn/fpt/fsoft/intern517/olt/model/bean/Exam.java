package vn.fpt.fsoft.intern517.olt.model.bean;

/**
 * Exam.java
 * 
 * Copyright
 * 
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- June
 * 27, 2017 Nguyen Cong Huong Create
 */

public class Exam {
	private int examID;
	private String fullNameStudent;
	private String addressIP;
	private String className;
	private String topicName;
	private String studentID;
	private String startTime;
	private String endTime;
	private String result;

	public int getExamID() {
		return examID;
	}

	public void setExamID(int examID) {
		this.examID = examID;
	}

	public String getFullNameStudent() {
		return fullNameStudent;
	}

	public void setFullNameStudent(String fullNameStudent) {
		this.fullNameStudent = fullNameStudent;
	}

	public String getAddressIP() {
		return addressIP;
	}

	public void setAddressIP(String addressIP) {
		this.addressIP = addressIP;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
