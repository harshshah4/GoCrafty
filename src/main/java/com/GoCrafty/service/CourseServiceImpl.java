package com.GoCrafty.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.CourseDAO;
import com.GoCrafty.dao.StudentDAO;
import com.GoCrafty.entity.Course;
@Service
public class CourseServiceImpl implements CourseService {

	
	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	@Transactional
	public List<String> showCategories() {
		return courseDAO.showCategories();
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
	public List<Course> getEnrolledCourses(int id) {
		return courseDAO.getEnrolledCourses(id);
	}

}
