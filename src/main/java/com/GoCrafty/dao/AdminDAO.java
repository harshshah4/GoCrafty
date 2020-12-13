package com.GoCrafty.dao;

import java.util.List;

import com.GoCrafty.entity.Admin;
import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;

public interface AdminDAO {

	Admin adminLogin(int id, String password);

	List<Instructor> getInstructor();

	List<Student> getStudent();

	String deleteInstructor(int instructorId);

	String deleteStudent(int userId);

	List<Course> getCourse();

	String deleteCourse(int courseId);

//	String addBarberShop(BarberShops theShop, String finalDays, String hour, double[] lati_longi);
//
	String updatePassword(int adminId, String previousPassword, String confirmPassword);

}
