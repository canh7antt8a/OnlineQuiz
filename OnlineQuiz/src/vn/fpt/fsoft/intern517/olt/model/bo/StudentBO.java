package vn.fpt.fsoft.intern517.olt.model.bo;
import java.util.ArrayList;


import vn.fpt.fsoft.intern517.olt.model.bean.Student;
/**
 * StudentBO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 14, 2017        Nguyen Cong Huong          	Create
 */
import vn.fpt.fsoft.intern517.olt.model.dao.StudentDAO;

public class StudentBO {
	StudentDAO studentDAO = new StudentDAO();
	
	public String getStudentName(String userName){
		return studentDAO.getStudentName(userName);
	}
	
	public ArrayList<Student> getListStudent(String classID){
		return studentDAO.getListStudent(classID);
	}
	
	public void deleteStudent(String studentID) {
		studentDAO.deleteStudent(studentID);
	}
	
	public String getClassID(String userName){
		return studentDAO.getClassID(userName);
	}

	public Student getInformationStudent(String studentID) {
		return studentDAO.getInformationStudent(studentID);
	}

	public void addStudent(String userName, String password, String fullName,
			String sex, String email, String phoneNumber, String classID) {
		studentDAO.addStudent(userName, password, fullName, sex, phoneNumber,
				email, classID);
	}

	public void editStudent(String studentID, String fullName, String sex,
			String email, String phoneNumber) {
		studentDAO.editStudent(studentID, fullName, sex, phoneNumber, email);
	}
	
	public ArrayList<Student> getListAllStudent() {
		return studentDAO.getListAllStudent();
	}
}
