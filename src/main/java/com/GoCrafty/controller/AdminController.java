package com.GoCrafty.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.Admin;
import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.service.AdminService;

@Controller
@RequestMapping("home/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/adminLogin")
	public String adminLogin(@ModelAttribute("admin") Admin admin, Model m,@SessionAttribute(name="tempSession") HashMap<String,String> adminSession)
	{
		int id = admin.getId();
		String password = admin.getPassword();
		Admin AdminList = adminService.adminLogin(id,password);

		if(AdminList == null)
		{
			m.addAttribute("message","Invalid ID or Password!");
			return "redirect:/home/showAdminLogin";
		}
		else 
		{
			String localId = String.valueOf(id);
			adminSession.put("id", localId);
			m.addAttribute("adminlist",AdminList);
			return "admin-profile";
		}
	}
	
	@RequestMapping("/logOut")
	public String logOut(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			return "redirect:/";
		}
		catch (Exception e) {
			
			return "redirect:/";
		}
	}
	
	@RequestMapping("/getInstructor")
	public String getInstructor(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession,Model m) {
		
		if(adminSession.get("id").equals(null) || adminSession.get("id").equals("temp")) {
			m.addAttribute("Message", "Something Went wrong! Please Log-in again");
			return "redirect:/home/showAdminLogin";
		}
		
		else
		{
		List<Instructor> instructorList = adminService.getInstructor();
		m.addAttribute("instructorList",instructorList);
		return "all-instructor";
		}
	}

	@RequestMapping("/getStudent")
	public String getStudent(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, Model m) {
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null))) {
			List<Student> student = adminService.getStudent();
			m.addAttribute("studentList", student);
			return "all-student";
		}
		
		else {
			m.addAttribute("Message", "Something Went wrong! Please Login again");
			return "redirect:/home/showAdminLogin";
		}
	}

	@RequestMapping("/getCourse")
	public String getCourse(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, Model m) {
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null))) {
			List<Course> courses = adminService.getCourse();
			m.addAttribute("courseList", courses);
			return "all-course";
		}
		
		else {
			m.addAttribute("Message", "Something Went wrong! Please Login again");
			return "redirect:/home/showAdminLogin";
		}
	}
	
	@RequestMapping("/deleteInstructor")
	public String deleteInstructor(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, Model m,@RequestParam("instructorId") int instructorId)
	{
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
		{
			
			String message = adminService.deleteInstructor(instructorId);
			if(message.equals("Instructor Deleted Successfully"))
			{
				m.addAttribute(message);
			}
			else
			{
				m.addAttribute(message);
			}
			return "redirect:/home/admin/getInstructor";
			}
		
		else {
			m.addAttribute("Message", "Something Went wrong! Please Login again");
			return "redirect:/home/showAdminLogin";
		}
		
	}
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, Model m,@RequestParam("studentId") int userId)
	{
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
		{
			@SuppressWarnings("unused")
			String message = adminService.deleteStudent(userId); 
			return "redirect:/home/admin/getStudent";
		}
		else {
			m.addAttribute("Message", "Something Went wrong! Please Login again");
			return "redirect:/home/showAdminLogin";
		}
	}
	
	
	
	@GetMapping("/deleteCourse")
	public String deletebarberShop(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, Model m,@RequestParam("courseId") int courseId)
	{
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
		{
			@SuppressWarnings("unused")
			String message = adminService.deleteCourse(courseId); 
			return "redirect:/home/admin/getCourse";
		}
		else {
			m.addAttribute("Message", "Something Went wrong! Please Login again");
			return "redirect:/home/showAdminLogin";
		}
	}
	
	@GetMapping("/showCourseForm")
	public String showCourseForm(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, Model m)
	{
		
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
		{
			Course course=new Course();
			adminSession.put("status", "admin");
			m.addAttribute("course",course);
			return "add-course";
		}
		else {
			m.addAttribute("Message", "Something Went wrong! Please Login again");
			return "redirect:/home/showAdminLogin";
		}
	}
	
//	@PostMapping("/addBarberShop")
//	public String addbarberShop(@ModelAttribute(name="theBarberShop")BarberShops theShop
//			,@SessionAttribute(name="tempSession") HashMap<String,String> adminSession, Model m,
//			@RequestParam("day") List<String> days,@RequestParam("from") String from,
//			@RequestParam("to")String to,@RequestParam("AM_PM_from")String am_pm_from,
//			@RequestParam("AM_PM_to") String am_pm_to)
//	{
//		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
//		{
//			StringBuilder str = new StringBuilder();
//			for(String day:days)
//			{
//				str.append(day+",");
//			}
//			
//			String temp=str.toString();
//			String finalDays = temp.substring(0, temp.length() - 1);
//			String hour=from+am_pm_from+"-"+to+am_pm_to;
//			
//			System.out.println("days: "+finalDays+"\nHours: "+hour);
//			double[] lati_longi = null;
//			try {
//				lati_longi=GetLatLong.getLatiLongi(theShop.getAddress()+" "+theShop.getCity()+" "+theShop.getState());
//			} catch (JSONException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String message=adminService.addBarberShop(theShop,finalDays,hour,lati_longi);
//			
//			if(message.equals("Instructor Shop Added Successfully"))
//			{
//				return "redirect:/home/admin/getShops";
//			}
//			else {
//				m.addAttribute("Message", "Something Went wrong! Please Try to add Instructor again");
//				return "admin-profile";			
//			}
//		}
//	
//		else {
//			m.addAttribute("Message", "Something Went wrong! Please Login again");
//			return "redirect:/home/showAdminLogin";
//		}		
//	}
//	
	@GetMapping("/showInstructorForm")
	public String showInstructorForm(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession,Model m) {
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
		{
			Instructor theInstructor= new Instructor();
			adminSession.put("status", "admin");
			m.addAttribute("instructor",theInstructor);
			return "instructor-signup";
		}
		else { 
			m.addAttribute("Message", "Something Went wrong! Please Try to add Instructor again");
			return "admin-profile";
			}
	}
	
	@GetMapping("/showStudentForm")
	public String showStudentForm(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession,Model m) {
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
		{
			Student theStudent= new Student();
			adminSession.put("status", "admin");
			m.addAttribute("student",theStudent);
			return "student-signup";
		}
		else { 
			m.addAttribute("Message", "Something Went wrong! Please Try to add Instructor again");
			return "admin-profile";}
	}
	
	@GetMapping("/showChangePassword")
	public String showChangePassword()
	{
		
		return "admin-change-password";
	}
	
	@GetMapping("/changePassword")
	public String changePassword(@SessionAttribute(name="tempSession") HashMap<String,String> adminSession,Model m,
									@RequestParam("previousPassword") String previousPassword,
									@RequestParam("confirmPassword") String confirmPassword)
	{
		int adminId=Integer.parseInt(adminSession.get("id"));
//		System.out.println("id: " +adminId);
		
		if(!(adminSession.get("id").equals("temp")) || (adminSession.get("id").equals(null)))
		{
			String message= adminService.updatePassword(adminId,previousPassword,confirmPassword);
//			System.out.println("previous pass: " +previousPassword);
//			System.out.println("confirm pass: " +confirmPassword);
			if(message.equals("Previous Password is incorrect"))
			{
				m.addAttribute("message",message);
				return "admin-profile";//change pass
			}
			else if(message.equals("Password updated"))
			{
				
				m.addAttribute("message", message);
				return "admin-profile";
				
			}
			
		}
		else { 
			m.addAttribute("message", "Something Went wrong! Please log in again");
			return "admin-profile";
			}

		
		
		return " ";
	}

}
