package vn.fpt.fsoft.intern517.olt.model.bo;

import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.Class;
import vn.fpt.fsoft.intern517.olt.model.dao.ClassDAO;

/**
 * ClassBO.java
 * 
 * Copyright
 * 
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- June
 * 14, 2017 Nguyen Cong Huong Create
 */

public class ClassBO {
	ClassDAO classDAO = new ClassDAO();

	public String getClassNameSelect(String userName) {
		return classDAO.getClassNameSelect(userName);
	}

	public String getClassNameManage(String classID) {
		return classDAO.getClassNameManage(classID);
	}

	public String getClassName(String userName) {
		return classDAO.getClassName(userName);
	}

	public ArrayList<Class> getListClass() {
		return classDAO.getListClass();
	}

	public void addClass(String className) {
		classDAO.addClass(className);
	}

	public void editClass(String classID, String className) {
		classDAO.editClass(classID, className);
	}

	public void deleteClass(String classID) {
		classDAO.deleteClass(classID);
	}

	public void deleteClass() {
		classDAO.deleteClass();
	}

	public ArrayList<Class> getCurrentClass() {
		return classDAO.getCurrentClass();
	}

	public boolean checkClassName(String className) {
		return classDAO.checkClassName(className);
	}

	public String getClassID(String className) {
		return classDAO.getClassID(className);
	}
}
