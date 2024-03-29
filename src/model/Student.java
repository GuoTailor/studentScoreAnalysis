/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;



public class Student implements Serializable, Cloneable{
	private String studentId;
	private String name;
	private String password;
	private int attendenceScore;
	private int finalScore;
	private Course course = new Course();

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student(){}
	
	public Student(String studentId, String name, int attendenceScore,int finalScore) {
		this.studentId = studentId;
		this.name = name;
		this.attendenceScore = attendenceScore;
		this.finalScore = finalScore;
		/*总评为前面四项之和*/
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student(String studentId,String name,String password){
		this.password = password;
		this.studentId = studentId;
		this.name = name;
	}

	
	public String getStudentId() {
		return studentId;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAttendenceScore() {
		return attendenceScore;
	}

	public int getFinalScore() {
		return finalScore;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAttendenceScore(int attendenceScore) {
		this.attendenceScore = attendenceScore;
	}

	
	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}
		
	@Override
	public Object clone() throws CloneNotSupportedException {
		Student student = (Student)this.clone();
		return student;
	}

	@Override
	public String toString() {
		return "Student{" +
				"studentId='" + studentId + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", attendenceScore=" + attendenceScore +
				", finalScore=" + finalScore +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return attendenceScore == student.attendenceScore &&
				finalScore == student.finalScore &&
				Objects.equals(studentId, student.studentId) &&
				Objects.equals(name, student.name) &&
				Objects.equals(password, student.password) ;
	}

}
