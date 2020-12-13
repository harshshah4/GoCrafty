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
@Table(name = "student_details")
public class Student extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	

	@Column(name = "birth_date")
	private String birthDate;
	
	@Column(name = "profile_pic")
	private byte[] profilePic;
	
	@Column(name = "apply_for_job")
	private String applyForJob;
	
	@Column(name = "logs")
	private String logs;
	
	@OneToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.ALL})
	@JoinColumn(name ="student_id")
	private List<CourseEnrolled> courses;

	public List<CourseEnrolled> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseEnrolled> courses) {
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public String getApplyForJob() {
		return applyForJob;
	}

	public void setApplyForJob(String applyForJob) {
		this.applyForJob = applyForJob;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}
	public Student() {
	}
	


	public Student(String firstName, String lastName, String email, String password, String birthDate,
			String applyForJob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.applyForJob = applyForJob;
	}
	
	

	
	
	
	
	
}
