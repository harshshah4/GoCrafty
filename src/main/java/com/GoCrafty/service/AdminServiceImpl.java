package com.GoCrafty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.AdminDAO;
import com.GoCrafty.entity.Admin;
import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	@Transactional
	public Admin adminLogin(int id, String password) {
		// TODO Auto-generated method stub
		return adminDAO.adminLogin(id,password);
	}

	@Override
	@Transactional
	public List<Instructor> getInstructor() {
		// TODO Auto-generated method stub
		return adminDAO.getInstructor();
	}

	@Override
	@Transactional
	public List<Student> getStudent() {
		// TODO Auto-generated method stub
		return adminDAO.getStudent();
	}

	@Override
	@Transactional
	public String deleteInstructor(int instructorId) {
		// TODO Auto-generated method stub
		return adminDAO.deleteInstructor(instructorId);
	}

	@Override
	@Transactional
	public String deleteStudent(int userId) {
		// TODO Auto-generated method stub
		return adminDAO.deleteStudent(userId);
	}

	@Override
	@Transactional
	public List<Course> getCourse() {
		// TODO Auto-generated method stub
		return adminDAO.getCourse();
	}

	@Override
	@Transactional
	public String deleteCourse(int courseId) {
		return adminDAO.deleteCourse(courseId);
	}

//	@Override
//	@Transactional
//	public String addBarberShop(BarberShops theShop, String finalDays, String hour,double[] lati_longi) {
//		// TODO Auto-generated method stub
//		return adminDAO.addBarberShop(theShop,finalDays,hour,lati_longi);
//	}

	@Override
	@Transactional
	public String updatePassword(int adminId,String previousPassword, String confirmPassword) {
		// TODO Auto-generated method stub
		return  adminDAO.updatePassword(adminId,previousPassword,confirmPassword);
	}
	
	

}
