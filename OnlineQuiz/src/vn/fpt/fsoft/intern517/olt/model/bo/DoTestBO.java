package vn.fpt.fsoft.intern517.olt.model.bo;
/**
 * DoTestBO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 29, 2017        Nguyen Cong Huong          	Create
 */

import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.DoTest;
import vn.fpt.fsoft.intern517.olt.model.dao.DoTestDAO;

public class DoTestBO {
	DoTestDAO doTestDAO = new DoTestDAO();
	
	public ArrayList<DoTest> getListStudent(String classID){
		return doTestDAO.getListStudent(classID);
	}
	
	public ArrayList<DoTest> getListAnswer(String studentID, String topicID) {
		return doTestDAO.getListAnswer(studentID, topicID);
	}
	
	public ArrayList<DoTest> getListStudentInTopic(String topicID) {
		return doTestDAO.getListStudentInTopic(topicID);
	}
}
