package com.GoCrafty.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	@RequestMapping("/")
	public String showHomePage(HttpSession session) {
		HashMap<String, String> tempSession = new HashMap<String, String>();
		tempSession.put("id", "temp");
		tempSession.put("retry","0");
		session.setAttribute("tempSession", tempSession);
		return "home-page";
	}
}
