package com.GoCrafty.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "course_enrolled")
public class CourseEnrolled {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "student_id")
	private int studentId;
	
	@Column(name = "grades")
	private String grades;
	
	@Column(name = "progress")
	private String progress;
	
	public CourseEnrolled() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public CourseEnrolled(int courseId, int studentId, String grades) {
		this.courseId = courseId;
		this.studentId = studentId;
		this.grades = grades;
	}

	
	
	
	
	

}
