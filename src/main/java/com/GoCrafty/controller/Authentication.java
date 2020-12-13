package com.GoCrafty.controller;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.User;
import com.GoCrafty.service.AuthenticationService;
import com.GoCrafty.service.JavaEmailWithGMail;

@Controller
@RequestMapping("home/authentication")
public class Authentication {
	
	@Autowired
	AuthenticationService  authenticationService;
	
	@RequestMapping("/login")
	public String login(Model theModel,@ModelAttribute("user") User theUser,@RequestParam("role") String role,@SessionAttribute(name="tempSession") HashMap<String,String> userSession)
	{
		
		String id=authenticationService.login(theUser,role);
		if (id==null)
		{
			int retry=Integer.parseInt(userSession.get("retry"));
			if(retry>=3)
			{
				//email block
				try {
					JavaEmailWithGMail.Email(theUser.getEmail(), "Someone just tried login with 3 wrong password. Please review your account.");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Sending Email failed ");
				}
				userSession.put("retry", "0");//reset counter
				theModel.addAttribute("message","You have entered wrong password 3 times! \n please try after some time");
			}
			retry=retry+1;
			userSession.put("retry",String.valueOf(retry));
			theModel.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/userLogin?role="+role;
		}
		
		 if(role.equals("student"))
		{
			userSession.put("id", id);
			return "redirect:/home/student/viewProfile";
		}
		else if(role.equals("instructor"))
		{
			userSession.put("id", id);
			return "redirect:/home/instructor/viewProfile";
		}
		else {
			int retry=Integer.parseInt(userSession.get("retry"));
			if(retry>=3)
			{
				//email block
				theModel.addAttribute("message","You have entered wrong password 3 times! \n please try after some time");
			}
			retry=retry+1;
			userSession.put("retry",String.valueOf(retry));
			theModel.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/userLogin?role="+role;
		}
	}
	
	@RequestMapping("/logOut")
	public String logOut(@SessionAttribute(name="tempSession") HashMap<String,String> userSession, HttpServletRequest request) {
	try {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
		}
	catch (Exception e) {
		
		return "redirect:/";
		}
	}

}
