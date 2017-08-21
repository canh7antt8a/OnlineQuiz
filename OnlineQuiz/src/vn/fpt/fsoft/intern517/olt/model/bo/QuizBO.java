package vn.fpt.fsoft.intern517.olt.model.bo;

import java.util.ArrayList;

import vn.fpt.fsoft.intern517.olt.model.bean.Quiz;

/**
 * QuizBO.java
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          			DESCRIPTION
 * -----------------------------------------------------------------------
 * June 29, 2017        Nguyen Cong Huong          	Create
 */

import vn.fpt.fsoft.intern517.olt.model.dao.QuizDAO;

public class QuizBO {
	QuizDAO quizDAO = new QuizDAO();

	public boolean checkQuiz(String topicID){
		return quizDAO.checkQuiz(topicID);
	}
	
	public ArrayList<Quiz> getListAnswer(String topicID){
		return quizDAO.getListAnswer(topicID);
	}
	
	public void addAnswer(int quizName, String topicID, String answer,
			int maxAnswer) {
		quizDAO.addAnswer(quizName, topicID, answer, maxAnswer);
 }
	
	public void editAnswer(int quizName, String topicID, String answer){
		quizDAO.editAnswer(quizName, topicID, answer);
	}
	
	public void doTest(String topicID, int quizName,
			String answerStudent, String studentID) {
		quizDAO.doTest(topicID, quizName, answerStudent, studentID);
	}
	
	public ArrayList<Quiz> getListQuiz(String topicID) {
		return quizDAO.getListQuiz(topicID);
	}
	
}
