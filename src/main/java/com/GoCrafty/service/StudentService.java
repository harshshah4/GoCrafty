package com.GoCrafty.service;

import java.util.ArrayList;

import com.GoCrafty.entity.Student;

public interface StudentService {
//	public String studentLogin(String email,String password);
	public Student getStudent(int id);
	public String getImage(int id);
	public String createAccount(Student theStudent);
	public Student editProfile(ArrayList<String> updatedStudent, String localId);
	public Student setCurrentLogin(int id);
	public String deleteProfile(String userId);
	public String uploadImage(byte[] bytes, int userId);


}
