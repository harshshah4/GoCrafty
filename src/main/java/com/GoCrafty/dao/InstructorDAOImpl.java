package com.GoCrafty.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.service.Encryption;
import com.ibm.icu.util.Calendar;

@Repository
public class InstructorDAOImpl implements InstructorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String instructorLogin(String email, String password) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			Query q= currentSession.createQuery("from Instructor s WHERE s.email= :email");
			q.setParameter("email", email);
			Instructor myInstructor=(Instructor) q.getSingleResult();
			String fetchPassword= myInstructor.getPassword();
			Encryption encr = new Encryption();
			String decryptedPassword=encr.decrypt(fetchPassword);
			String id=String.valueOf(myInstructor.getId());
			if(decryptedPassword.contentEquals(password)){
				return id;
				}
			else 
				return null;
				}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Instructor getUser(int id) {
		try {
		Session currentSession= sessionFactory.getCurrentSession();
//		Query query=currentSession.createSQLQuery("Set foreign_key_checks =0");
//		query.executeUpdate();
		Instructor myInstructor= currentSession.get(Instructor.class, id);
		return myInstructor;
		}
		catch (Exception e) {
			System.out.println("HELLOOOOOOOO");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getImage(int id) {
		Session currentSession= sessionFactory.getCurrentSession();
		Instructor thisInstructor= currentSession.get(Instructor.class, id);
		 try {
			 byte[] buffer=thisInstructor.getProfilePic();
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
	public String createAccount(Instructor theInstructor) {

		Session  currentSession= sessionFactory.getCurrentSession();
			try {
				Encryption encr = new Encryption();
				String encryptedPassword=encr.encrypt(theInstructor.getPassword());
				Instructor newInstructor;
				if(theInstructor.getRecruiter()==null)
				{
					theInstructor.setRecruiter("0");
				}
				newInstructor= new Instructor(theInstructor.getFirstName(), theInstructor.getLastName(),theInstructor.getEmail(),encryptedPassword,theInstructor.getRecruiter());

		
				currentSession.save(newInstructor);
			}
			catch (Exception e) {
				e.printStackTrace();
				return "Cannot create Instructor! Please try again";
			}
				return "Success! Instructor created, Please login to continue";
		}

	@Override
	public Instructor setCurrentLogin(int id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Instructor myInstructor = currentSession.get(Instructor.class, id);

		Date currentDate = Calendar.getInstance().getTime();
		String lastLogin = currentDate.toString();
		myInstructor.setLogs(lastLogin);
		currentSession.save(myInstructor);
		return null;
	}

	@Override
	public Instructor editProfile(ArrayList<String> updatedInstructor, String localId) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		
		String firstName = updatedInstructor.get(0);
		String lastName = updatedInstructor.get(1);
		String password = updatedInstructor.get(2);
		
		Encryption encr = new Encryption();
		String encryptedPassword=encr.encrypt(password);
		
		int id = Integer.parseInt(localId);
		
		Instructor instructor = currentSession.get(Instructor.class, id);
		
		instructor.setFirstName(firstName);
		instructor.setLastName(lastName);
		instructor.setPassword(encryptedPassword);
		
		Instructor updatedInstructor1 = currentSession.get(Instructor.class, id);
       return updatedInstructor1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCourseByInstructorId(int id) {
		// TODO Auto-generated method stub
		Session  currentSession= sessionFactory.getCurrentSession();
		ArrayList<Course> courses = new ArrayList<Course>(); 
		try {
			Query query=currentSession.createQuery("from Course c WHERE c.instructor_id= :id");
			query.setParameter("id", id);
			courses= (ArrayList<Course>) query.getResultList();
			System.out.println("Course"+courses.get(0).getName());
			return courses;
		}
		catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public String deleteProfile(String userId) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = Integer.parseInt(userId);
			Instructor instructor = currentSession.get(Instructor.class, id);
			currentSession.delete(instructor);
			return "Instructor Deleted";
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return "Sorry for the inconvinience! Instructor can not be deleted. Please contact the website Administrator.";
		}
	}

	@Override
	public String uploadImage(byte[] bytes, int userId) {
		try {
			Session  currentSession= sessionFactory.getCurrentSession();
			Instructor theInstructor=currentSession.get(Instructor.class,userId);
			theInstructor.setProfilePic(bytes);
			return "ok";
				
			
		}
		catch (Exception e) {
			
			e.printStackTrace();	
			return "not ok";
			}
	}
}
