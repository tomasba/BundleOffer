package seb.domain.question;

// TODO probably the QuestionDto couldbe replaced with the simple one.
public class Question {

	private int age;
	private boolean student;
	private double income;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isStudent() {
		return student;
	}
	public void setStudent(boolean student) {
		this.student = student;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	
}
