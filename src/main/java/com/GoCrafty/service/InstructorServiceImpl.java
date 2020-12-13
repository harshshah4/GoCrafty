package com.GoCrafty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.InstructorDAO;
import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Instructor;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorDAO instructorDAO;
	
	@Override
	@Transactional
	public String instructorLogin(String email, String password) {
	
		return instructorDAO.instructorLogin(email, password);
		}

	@Override
	@Transactional
	public Instructor getInstructor(int id) {
		return instructorDAO.getUser(id);
		}

	@Override
	@Transactional
	public String getImage(int id) {
		return instructorDAO.getImage(id);
	}

	@Override
	@Transactional
	public String createAccount(Instructor theInstructor) {
		return instructorDAO.createAccount(theInstructor);
	}

	@Override
	@Transactional
	public Instructor setCurrentLogin(int id) {
		return instructorDAO.setCurrentLogin(id);
		
	}

	@Override
	@Transactional
	public Instructor editProfile(ArrayList<String> updatedInstructor, String localId) {
		// TODO Auto-generated method stub
		return instructorDAO.editProfile(updatedInstructor,localId);
	}

	@Override
	@Transactional
	public List<Course> getCourseByInstructorId(int id) {
		// TODO Auto-generated method stub
		return instructorDAO.getCourseByInstructorId(id);
	}

	@Override
	@Transactional
	public String deleteProfile(String userId) {
		// TODO Auto-generated method stub
		return instructorDAO.deleteProfile(userId);
		
	}

	@Override
	@Transactional
	public String uploadImage(byte[] bytes, int userId) {
		return instructorDAO.uploadImage(bytes,userId);
	}
}
