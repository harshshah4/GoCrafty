package com.GoCrafty.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.Feedback;
import com.GoCrafty.entity.User;
import com.GoCrafty.service.FeedbackService;

@Controller
@RequestMapping("home/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("/addFeedback")
	public String addFeedback(Model theModel,@ModelAttribute("theFeedback") Feedback theFeedback,
			@RequestParam("courseId") String courseId,
			@SessionAttribute(name="tempSession") HashMap<String,String> userSession)
	{
		
		String msg=feedbackService.addFeedback(theFeedback,courseId);
		
		if(msg.equals("feedback added"))
		{
			return "redirect:/home/student/viewProfile";
		}
		else
		{
			return "error-page";
		}
		
	}

}
