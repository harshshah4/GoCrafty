package com.GoCrafty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.CourseDAO;
import com.GoCrafty.entity.Course;
@Service
public class CourseServiceImpl implements CourseService {

	
	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	@Transactional
	public List<String> showCategories(String recruitment) {
		return courseDAO.showCategories(recruitment);
	}

	@Override
	@Transactional
	public List<Course> showCoursesByCategory(String category) {
		return courseDAO.showCoursesByCategory(category);
	}

	@Override
	@Transactional
	public HashMap<String, String> getInstructorNames(List<Course> course) {
		return courseDAO.getInstructorNames(course);	}

	@Override
	@Transactional
	public Course getCourseById(String id) {
		return courseDAO.getCourseById(id);	
		}

	@Override
	@Transactional
	public String enroll(String useId, String courseId) {
		return courseDAO.enroll(useId,courseId );	
	}

	@Override
	@Transactional
	public Course addCourse(Course course) {
		return courseDAO.addCourse(course);
	}

	@Override
	@Transactional
	public List<Course> getEnrolledCourses(int id) {
		return courseDAO.getEnrolledCourses(id);

	}

	@Override
	@Transactional
	public Course modifyCourse(Course course, String courseId) {
		return courseDAO.modifyCourse(course,courseId);
	}
	
	@Override
	@Transactional
	public float getScore(String email, List<String> repsonseLink, int courseID) {
		return courseDAO.getScore(email, repsonseLink, courseID);
	}

	@Override
	@Transactional
	public String uploadVideo(String uploadVideo, String courseId) {
		// TODO Auto-generated method stub
		return courseDAO.uploadVideo(uploadVideo,courseId);
	}

	@Override
	@Transactional
	public String uploadQuiz(String uploadQuiz, String courseId, String responseLink) {
		// TODO Auto-generated method stub
		return courseDAO.uploadQuiz(uploadQuiz,courseId, responseLink);
	}

	@Override
	@Transactional
	public HashMap<String, String> getStudentsEnrolled(String newCourseId) {
		// TODO Auto-generated method stub
		return courseDAO.getStudentsEnrolled(newCourseId);
	}

	@Override
	@Transactional
	public ArrayList<String> getGrades(List<Course> enrolledCourses, int id) {
		return courseDAO.getGrades(enrolledCourses, id);
	}

	@Override
	@Transactional
	public Boolean deleteCourse(String courseId) {
		// TODO Auto-generated method stub
		return courseDAO.deleteCourse(courseId);
	}

	@Override
	@Transactional
	public String dropCourse(String studentId, String courseId) {
		return courseDAO.dropCourse(studentId,courseId);
	}

	@Override
	@Transactional
	public String getInstructorEmail(String id) {
		return courseDAO.getInstructorEmail(id);
	}

}
