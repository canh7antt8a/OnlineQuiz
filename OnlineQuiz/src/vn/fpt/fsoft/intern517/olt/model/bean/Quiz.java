package vn.fpt.fsoft.intern517.olt.model.bean;

public class Quiz {
	private String quizID;
	private int quizName;
	private String topicID;
	private String answer;
	private int maxAnswer;

	public String getQuizID() {
		return quizID;
	}

	public void setQuizID(String quizID) {
		this.quizID = quizID;
	}

	public int getQuizName() {
		return quizName;
	}

	public void setQuizName(int quizName) {
		this.quizName = quizName;
	}

	public String getTopicID() {
		return topicID;
	}

	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getMaxAnswer() {
		return maxAnswer;
	}

	public void setMaxAnswer(int maxAnswer) {
		this.maxAnswer = maxAnswer;
	}

}
