package com.GoCrafty.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "estimated_time_to_complete")
	private String estimatedTimeToComplete;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "instructor_id")
	private int instructor_id;
	
	@Column(name = "video_link")
	private String videoLink;
	
	@Column(name = "quiz_link")
	private String quizLink;
	
	@Column(name = "response_link")
	private String responseLink;

	@OneToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.ALL})
	@JoinColumn(name = "course_id")
	private List<CourseEnrolled> courseEnrolled;
	
	public List<CourseEnrolled> getCourseEnrolled() {
		return courseEnrolled;
	}

	public void setCourseEnrolled(List<CourseEnrolled> courseEnrolled) {
		this.courseEnrolled = courseEnrolled;
	}

	public Course() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEstimatedTimeToComplete() {
		return estimatedTimeToComplete;
	}

	public void setEstimatedTimeToComplete(String estimatedTimeToComplete) {
		this.estimatedTimeToComplete = estimatedTimeToComplete;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(int instructor_id) {
		this.instructor_id = instructor_id;
	}
	
	public String getResponseLink() {
		return responseLink;
	}

	public void setResponseLink(String responseLink) {
		this.responseLink = responseLink;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getQuizLink() {
		return quizLink;
	}

	public void setQuizLink(String quizLink) {
		this.quizLink = quizLink;
	}

	public Course(String name, String description, String estimatedTimeToComplete, String category,
			int instructor_id, String videoLink, String quizLink, String responseLink) {
		super();
		this.name = name;
		this.description = description;
		this.estimatedTimeToComplete = estimatedTimeToComplete;
		this.category = category;
		this.instructor_id = instructor_id;
		this.videoLink = videoLink;
		this.quizLink = quizLink;
		this.responseLink = responseLink;
	}
	
}
