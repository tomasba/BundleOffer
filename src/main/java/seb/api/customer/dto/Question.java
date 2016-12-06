package seb.api.customer.dto;

public class Question {

	private int age;
	private boolean student;
	private double income;
	
	public Question() {		
	}
	
	public Question(int age) {
		this.age = age;
	}
	
	public Question(int age, boolean student, double income) {
		this.age = age;
		this.student = student;
		this.income = income;
	}	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		return str.append("age=").append(age).append(" stdent=").append(student).append(" income=").append(income).toString();
	}
	
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
