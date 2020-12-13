package com.GoCrafty.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Student;
import com.GoCrafty.service.StudentService;
import com.GoCrafty.service.CourseService;
import com.GoCrafty.service.Encryption;


@Controller
@RequestMapping("home/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;

	
	@GetMapping("/viewProfile")
	String viewProfile(Model m,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		try {

			int id=Integer.parseInt(studentSession.get("id"));
			Student student= studentService.getStudent(id);
			String image = studentService.getImage(id);
				
			studentService.setCurrentLogin(id);
			
			studentSession.put("firstName",student.getFirstName());
			studentSession.put("lastName",student.getLastName());
			studentSession.put("email",student.getEmail());
			studentSession.put("recruitment", student.getApplyForJob());
			
			m.addAttribute("img",image);
			m.addAttribute("student",student);
			
			
			//show Enrolled course block start
			
			List<Course> enrolledCourses=courseService.getEnrolledCourses(id);
			m.addAttribute("enrolledCourses", enrolledCourses);
			HashMap<String, String> instructorName=courseService.getInstructorNames(enrolledCourses);
			m.addAttribute("instructorName", instructorName);
			//show Enrolled course block end
			
			//getGradesFrom DB
			
			ArrayList<String> grades=courseService.getGrades(enrolledCourses,id);
			
			m.addAttribute("grades",grades);
			//getGradesFrom DB
			
			sendToHeader(m);
			return "student-profile";
			}
		catch (Exception e)
			{
			e.printStackTrace();
			return "error-page";
			}
	}
	
	String sendToHeader(Model m) 
	{
		return "student-header";
	}
		
	@PostMapping("/createAccount")
	public String createAccount(@SessionAttribute(name="tempSession") HashMap<String,String> studentSession,@ModelAttribute(name="student") Student theStudent,Model theModel) {
		
		String message=studentService.createAccount(theStudent);
		if (message.equals("Cannot create user! Please try again"))
		{
			theModel.addAttribute("message", message);
			return "student-login-form?role=student";
		}
		else
		{
			theModel.addAttribute("message", message);
			if(studentSession.containsKey("status")){
				return "redirect:/home/admin/getStudent";
			}
			else
				return "redirect:/home/userLogin?role=student";
		}
	}
	
	@GetMapping("/showEditProfile")
	public String showEditProfile(@SessionAttribute(name="tempSession") HashMap<String,String> studentSession,Model theModel) {
		String localId = studentSession.get("id");
		if(!(localId.contentEquals("temp") || localId.equals(null))) {
			int id = Integer.parseInt(localId);
			Student student = studentService.getStudent(id);
			Encryption encr= new Encryption();
			String encryptedPass= student.getPassword();
			String decryptedPass=encr.decrypt(encryptedPass);
			student.setPassword(decryptedPass);
			
			theModel.addAttribute("student", student);
			return "edit-profile-student";
		}
		else { 
			theModel.addAttribute("Message", "Your Session was Expired! Please login again to continue");
			return "redirect:/home/authentication/userLogin?role=student";
		
		}
	}
	
	@PostMapping("/editProfile")
	public String editProfile(@SessionAttribute(name="tempSession") HashMap<String,String> studentSession,
									@RequestParam("firstName") String firstName,
									@RequestParam("lastName") String lastName,
									@RequestParam("password") String password, 
									Model model) {
		

		String localId = studentSession.get("id");
		if(!(localId.contentEquals("temp") || localId.equals(null))) {
			
			ArrayList<String> updatedStudent = new ArrayList<>();
			updatedStudent.add(firstName);
			updatedStudent.add(lastName);
			updatedStudent.add(password);
			
			Student student = studentService.editProfile(updatedStudent,localId);
			if(updatedStudent == null || localId == null)
			{
				return "redirect:/home/student/viewProfile";
			}
			else {
				model.addAttribute("student",student);
				studentSession.put("firstName",student.getFirstName());
				studentSession.put("lastName",student.getLastName());
				studentSession.put("email",student.getEmail());
				studentSession.put("recruitment", student.getApplyForJob());
				return "redirect:/home/student/viewProfile";
				}
		}
		else {
			model.addAttribute("Message", "Your Session was Expired! Please login again to continue");
			return "redirect:/home/authentication/userLogin?role=student";
		}
}
	
	@PostMapping("/doUpload")
	public String doUpload(Model theModel,@RequestParam("fileUpload") MultipartFile file,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		
		String localId = studentSession.get("id");

		if(!(localId.contentEquals("temp") || localId.equals(null))) 
	{
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
			} 
		catch (IOException e) 
			{
			// TODO Auto-generated catch block
			return "error-page";
			}
		int userId=Integer.parseInt(localId);

		String message=studentService.uploadImage(bytes,userId);
		if (message.equals("ok"))
			{		
			return "redirect:/home/student/viewProfile";
			}
		else {
			theModel.addAttribute("message","An error has occured while uploading image\n Please login again");

			return "redirect:/home/userLogin";
			}
	}
		else {
			theModel.addAttribute("message","Something went Wrong! Please login again");
			return "redirect:/home/userLogin";	
			}
	}
	
	@GetMapping("/deleteProfile")
	public String deleteProfile(@SessionAttribute(name="tempSession") HashMap<String,String> studenSession, Model theModel) {
		
		String userId=studenSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {
			String message = studentService.deleteProfile(userId);
			theModel.addAttribute("message", message);
			return "redirect:/home/userLogin?role=instructor";
		}
	}
}