package vn.fpt.fsoft.intern517.olt.model.bo;

/**
 * UserBO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 14, 2017        Nguyen Cong Huong          	Create
 */
import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.User;
import vn.fpt.fsoft.intern517.olt.model.dao.UserDAO;

public class UserBO {
	UserDAO userDAO = new UserDAO();

	public boolean checkLogin(String userName, String password) {
		return userDAO.checkLogin(userName, password);
	}

	public String checkAuthorization(String userName) {
		return userDAO.checkAuthorization(userName);
	}

	public ArrayList<User> getListAdmin() {
		return userDAO.getListAdmin();
	}

	public void deleteAdmin(String userName) {
		userDAO.deleteAdmin(userName);
	}

	public void addAdmin(String userName, String password) {
		userDAO.addAdmin(userName, password);
	}

	public void editPassword(String userName, String password) {
		userDAO.editPassword(userName, password);
	}
	
	public void deleteUser(String type){
		userDAO.deleteUser(type);
	}
	
	public boolean checkUserName(String userName){
		return userDAO.checkUserName(userName);
	}
}
