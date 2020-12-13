package com.GoCrafty.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.service.Encryption;
import com.ibm.icu.util.Calendar;

@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Student getStudent(int id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Student myStudent= currentSession.get(Student.class, id);
		return myStudent;
	}

	@Override
	public String getImage(int id) {
		Session currentSession= sessionFactory.getCurrentSession();
		Student thisStudent= currentSession.get(Student.class, id);
		 try {
		 byte[] buffer=thisStudent.getProfilePic();
		 ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
		
	      BufferedImage bImage2 = ImageIO.read(bis);
	       ByteArrayOutputStream os = new ByteArrayOutputStream();
	      ImageIO.write(bImage2, "jpg", os );
	     String img= Base64.getEncoder().encodeToString(os.toByteArray());
	     
	     return img;
		 }
		 catch (Exception e) {
		    
		     return "failed";
			
		}
	}

	@Override
	public String createAccount(Student theStudent) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			Encryption encr = new Encryption();
			String encryptedPassword=encr.encrypt(theStudent.getPassword());
			Student newStudent;
			if(theStudent.getApplyForJob()==null)
			{
				theStudent.setApplyForJob("0");
			}
			newStudent= new Student(theStudent.getFirstName(), theStudent.getLastName(),theStudent.getEmail(),encryptedPassword,theStudent.getBirthDate(),theStudent.getApplyForJob());

	
			currentSession.save(newStudent);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Cannot create user! Please try again";
		}
			return "Success! User created, Please login to continue";
	}

	@Override
	public Student editProfile(ArrayList<String> updatedStudent, String localId) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		
		String firstName = updatedStudent.get(0);
		String lastName = updatedStudent.get(1);
		String password = updatedStudent.get(2);
		
		Encryption encr = new Encryption();
		String encryptedPassword=encr.encrypt(password);
		
		int id = Integer.parseInt(localId);
		
		Student student = currentSession.get(Student.class, id);
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setPassword(encryptedPassword);
		
		Student updatedStudent1 = currentSession.get(Student.class, id);
       return updatedStudent1;
	}

	@Override
	public Student setCurrentLogin(int id) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		Student myStudent = currentSession.get(Student.class, id);

		Date currentDate = Calendar.getInstance().getTime();
		String lastLogin = currentDate.toString();
		myStudent.setLogs(lastLogin);
		currentSession.save(myStudent);
		return null;
	}

	@Override
	public String uploadImage(byte[] bytes, int studentId) {
		try {
			Session  currentSession= sessionFactory.getCurrentSession();
			Student theStudent=currentSession.get(Student.class,studentId);
			System.out.println("name: "+theStudent.getFirstName());
			theStudent.setProfilePic(bytes);
			return "ok";
			
			
		}
		catch (Exception e) {
			
			e.printStackTrace();	
			return "not ok";
			}
		}

	@Override
	public String deleteProfile(String userId) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = Integer.parseInt(userId);
			Student student = currentSession.get(Student.class, id);
			currentSession.delete(student);
			return "Student Deleted";
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return "Sorry for the inconvinience! Student can not be deleted. Please contact the website Administrator.";
		}
	}
}
