package com.GoCrafty.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Admin;
import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.service.Encryption;


@Repository
public class AdminDAOImpl implements AdminDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Admin adminLogin(int id, String password) {
		
		try {
			Session  currentSession= sessionFactory.getCurrentSession();
//			System.out.println(id);
			Admin admin = currentSession.get(Admin.class, id);
			Encryption encr= new Encryption();
			String decryptedPassword=encr.decrypt(admin.getPassword());
			if(decryptedPassword.contentEquals(password)){
				return admin;
				}
			else return null;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Instructor> getInstructor() {
		try {
		Session currentSession= sessionFactory.getCurrentSession();
		List<Instructor> adminList=currentSession.createQuery("from Instructor").getResultList();
		return adminList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	@Override
	public List<Student> getStudent() {
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Student> studentList = currentSession.createQuery("from Student").getResultList();
			return studentList;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public String deleteInstructor(int instructorId) {
		try {
		Session currentSession = sessionFactory.getCurrentSession();
		Instructor myInstructor = currentSession.get(Instructor.class, instructorId);
		currentSession.delete(myInstructor);
		return "Instructor Deleted Successfully";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "An error has occured! Please try again.";
		}
	}

	@Override
	public String deleteStudent(int userId) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = userId;
			Student student = currentSession.get(Student.class, id);
			currentSession.delete(student);
			return "Student Deleted";
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return "Sorry for the inconvinience! Student can not be deleted. Please contact the website Administrator.";
		}
	}

	@Override
	public List<Course> getCourse() {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
//			Query q=currentSession.createQuery("from BarberShops");
//			//System.out.println("Query: "+q);
//			@SuppressWarnings("unchecked")
//			List<BarberShops> shopList = q.getResultList();
			
			//2n approach
			 CriteriaBuilder builder = currentSession.getCriteriaBuilder();
			 CriteriaQuery<Course> criteria = builder.createQuery(Course.class);
			 Root<Course> root=criteria.from(Course.class);
			 criteria.select(root);
			 Query query= currentSession.createQuery(criteria);
			 @SuppressWarnings("unchecked")
			List<Course> data = query.getResultList();
			return data;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String deleteCourse(int courseId) {
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			
			Course myCourse = currentSession.get(Course.class, courseId);
			currentSession.delete(myCourse);
			return "Shop Deleted Successfully";
			}
			catch (Exception e) {
				e.printStackTrace();
				return "An error has occured! Please try again.";
			}
	}

//	@Override
//	public String addBarberShop(BarberShops theShop, String finalDays, String hour,double[] lati_longi) {
//		try {
//			Session currentSession = sessionFactory.getCurrentSession();
//			Query query=currentSession.createSQLQuery("Set foreign_key_checks = 0");
//			query.executeUpdate();	
//			BarberShops myShop=new BarberShops(theShop.getName(),theShop.getAddress(),lati_longi[0],lati_longi[1],theShop.getCity(),theShop.getState(),theShop.getCountry(),finalDays,hour);
//			currentSession.save(myShop);
//			return "Barber Shop Added Successfully";
//		}
//		catch (Exception e) {
//			return "Failed to add Barber Shop";
//		}
//	}
//
	@Override
	public String updatePassword(int adminId,String previousPassword, String confirmPassword) 
	{

		Session currentSession = sessionFactory.getCurrentSession();
		Admin theAdmin=currentSession.get(Admin.class, adminId);
		
		Encryption encypter= new Encryption();
		String currentpassword=encypter.decrypt(theAdmin.getPassword());
		
		if(currentpassword.equals(previousPassword))
		{
			theAdmin.setPassword(encypter.encrypt(confirmPassword));
			return "Password updated";
		}
		else
		{
			return "Previous Password is incorrect";
		}
		
	}
	
	
}
