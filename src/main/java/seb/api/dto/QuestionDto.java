package seb.api.dto;
// should be enough simply Question dto
public class QuestionDto {

	private final int age;
	private final boolean ageAttrProvided;
	private final boolean student;
	private final boolean studentAttrProvided;
	private final double income;
	private final boolean incomeAttrProvided;
	
	protected QuestionDto(QuestionBuilder builder) {
		this.age = builder.age;
		this.ageAttrProvided = builder.ageAttrProvided;
		this.student = builder.student;
		this.studentAttrProvided = builder.studentAttrProvided;
		this.income = builder.income;
		this.incomeAttrProvided = builder.incomeAttrProvided;
	}
	
	public int getAge() {
		return age;
	}

	public double getIncome() {
		return income;
	}

	public boolean isStudent() {
		return student;
	}

	public boolean isAgeAttrProvided() {
		return ageAttrProvided;
	}	
	
	public boolean isStudentAttrProvided() {
		return studentAttrProvided;
	}

	public boolean isIncomeAttrProvided() {
		return incomeAttrProvided;
	}


	public static class QuestionBuilder {
		
		private final int age;
		private final boolean ageAttrProvided;
		private boolean student;
		private boolean studentAttrProvided;
		private double income;
		private boolean incomeAttrProvided;
		
		public QuestionBuilder(int age) {
			this.age = age;
			this.ageAttrProvided = true;
		}		
		
		public QuestionBuilder student(boolean student) {
			this.student = student;
			this.studentAttrProvided = true;
			return this;
		}		
		
		public QuestionBuilder income(double income) {
			this.income = income;
			this.incomeAttrProvided = true;
			return this;
		}				
		
		public QuestionDto build() {
			return new QuestionDto(this);
		}
	}	
}