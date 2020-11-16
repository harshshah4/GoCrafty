package com.GoCrafty.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.entity.User;
import com.GoCrafty.service.Encryption;
import com.GoCrafty.service.Wrapper;
@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String login(User theUser, String role) {
		Session  currentSession= sessionFactory.getCurrentSession();

		Student theStudent=new Student();
		Instructor theInstructor= new Instructor();
		Query q = null;
		String id = "0" ;
		String decryptedPassword=null;
		Encryption encr = new Encryption();
		try {
				if(role.equals("student"))
				{
					 theStudent=Wrapper.studentWrapper(theUser);
					 System.out.println("aut dao name: |"+theStudent.getEmail()+"|");
					 q= currentSession.createQuery("from Student s WHERE s.email= :email");
						q.setParameter("email", theStudent.getEmail());
									Student myStudent=(Student) q.getSingleResult();
									System.out.println("my student: |"+myStudent.getEmail()+"|");
									String fetchPassword= myStudent.getPassword();
									decryptedPassword=encr.decrypt(fetchPassword);
									 id=String.valueOf(myStudent.getId());
									if(decryptedPassword.contentEquals(theStudent.getPassword()))
									{
										System.out.println("email and password match \nand id: |"+myStudent.getId()+"|");
										return id;
										}
									else 
										return null;
					 
				}
				
				else if(role.equals("instructor"))
				{
					theInstructor=Wrapper.instructorWrapper(theUser);
					q= currentSession.createQuery("from Instructor i WHERE i.email= :email");
					q.setParameter("email", theInstructor.getEmail());
					Instructor myInstructor=(Instructor) q.getSingleResult();
					String fetchPassword2= myInstructor.getPassword();
					decryptedPassword=encr.decrypt(fetchPassword2);
					id= String.valueOf(myInstructor.getId());
					if(decryptedPassword.contentEquals(theInstructor.getPassword()))
						{
						return id;
						}
					else 
						return null;
						
				}
		}
		catch (Exception e) {
			return null;
		}
		return id;
	}

}
