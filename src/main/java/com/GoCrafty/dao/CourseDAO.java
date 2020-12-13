package com.GoCrafty.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.GoCrafty.entity.Course;

public interface CourseDAO {
	public List<String> showCategories(String recruitment);

	public List<Course> showCoursesByCategory(String category);

	public HashMap<String, String> getInstructorNames(List<Course> course);

	public Course getCourseById(String id);

	public String enroll(String useId, String courseId);

	public Course addCourse(Course course);
	public List<Course> getEnrolledCourses(int id);

	public Course modifyCourse(Course course, String courseId);
	
	public float getScore(String email, List<String> ResponseLink, int courseID);

	public String uploadVideo(String uploadVideo, String courseId);

	public String uploadQuiz(String uploadQuiz, String courseId, String responseLink);

	public HashMap<String, String> getStudentsEnrolled(String newCourseId);

	public ArrayList<String> getGrades(List<Course> enrolledCourses, int id);

	public Boolean deleteCourse(String courseId);

	public String dropCourse(String studentId, String courseId);

	public String getInstructorEmail(String id);

	


}
