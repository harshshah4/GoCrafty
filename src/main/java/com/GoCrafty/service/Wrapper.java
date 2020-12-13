package com.GoCrafty.service;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.entity.User;

public class Wrapper {
	
	public static Student studentWrapper(User theUser)
	{
		Student theStudent=new Student();
		theStudent.setId(theUser.getId());
		
		theStudent.setFirstName(theUser.getFirstName());
		theStudent.setFirstName(theUser.getLastName());
		theStudent.setEmail(theUser.getEmail());
		theStudent.setPassword(theUser.getPassword());
		System.out.println("User name: |"+theUser.getEmail()+"|\nStudent Name: |"+theStudent.getEmail()+"|");

		return theStudent;
		
	}
	

	public static Instructor instructorWrapper(User theUser)
	{
		Instructor theInstructor=new Instructor();
		theInstructor.setId(theUser.getId());
		theInstructor.setFirstName(theUser.getFirstName());
		theInstructor.setFirstName(theUser.getLastName());
		theInstructor.setEmail(theUser.getEmail());
		theInstructor.setPassword(theUser.getPassword());
		
		return theInstructor;
		
	}

}
