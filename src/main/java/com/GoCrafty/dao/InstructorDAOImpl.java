package com.GoCrafty.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.service.Encryption;

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
		Session  currentSession= sessionFactory.getCurrentSession();
		Instructor myInstructor= currentSession.get(Instructor.class, id);
		return myInstructor;
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
}