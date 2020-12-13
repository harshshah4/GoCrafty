package com.GoCrafty.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.GoCrafty.entity.Admin;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;

import com.GoCrafty.entity.User;


@Controller
@RequestMapping("/home")
public class HomePageController {
	
	@RequestMapping("/userLogin")
	public String UserLogin(Model theModel,@ModelAttribute("message")String message,@RequestParam("role") String role)
	{
			
		if(role.equals("instructor") || role.equals("student")) {
			theModel.addAttribute("message",message);
			theModel.addAttribute("role",role);
			
			User theUser = new User();
			theModel.addAttribute("user",theUser);
			return "login-form";
		}
		
		else return "error-page";
	}
	
	@RequestMapping("/createUser")
	public String createUser(@RequestParam("role") String role, Model theModel)
	{
		if(role.equals("instructor")) {
			Instructor instructor = new Instructor();
			theModel.addAttribute("instructor",instructor);
			return "instructor-signup";
		}
		
		else if (role.equals("student")) {
			Student student = new Student();
			theModel.addAttribute("student",student);
			return "student-signup";	
		}
		
		else return "error-page";
	}
	
	@RequestMapping("/showAdminLogin")
	public String showAdminLogin(Model theModel,@ModelAttribute("message")String message)
	{
		Admin theAdmin=new Admin();
		theModel.addAttribute("admin", theAdmin);
		theModel.addAttribute("message",message);
		return "admin-form";
		
	}

}
