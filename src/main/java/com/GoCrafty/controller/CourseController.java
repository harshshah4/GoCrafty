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

import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Feedback;
import com.GoCrafty.service.CourseService;
import com.GoCrafty.service.FeedbackService;
import com.GoCrafty.service.PdfCreator;
import com.GoCrafty.service.Utilities;
import com.itextpdf.text.DocumentException;

@Controller
@RequestMapping("home/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@GetMapping("/showCategories")
	public String showCategories(Model theModel, @RequestParam("recruitment")String recruitment)
	{
		
		List<String> categories=courseService.showCategories(recruitment);
		theModel.addAttribute("category",categories);
		return "show-category";
	}

	@PostMapping("/showCoursesByCategory")
	public String showCoursesByCategory(Model theModel,@RequestParam("category") String category)
	{
		List<Course> course=courseService.showCoursesByCategory(category);
		 HashMap<String, String> hmap=courseService.getInstructorNames(course);
		 theModel.addAttribute("instructor", hmap);
		theModel.addAttribute("course", course);
		
		return "courses-by-category";
	}

	@RequestMapping("/viewCourseDescription")
	public String viewCourseDescription(@RequestParam("id")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		Course theCourse=courseService.getCourseById(courseId);
		List<Course> course =new ArrayList<Course>();
		course.add(theCourse);
		HashMap<String, String> instructorName=courseService.getInstructorNames(course);
		
		theModel.addAttribute("theCourse",theCourse);
		theModel.addAttribute("instructorName",instructorName);
		
		//if(studentSession.get(""))
		return "view-course-description";
	}
	
	@RequestMapping("/enrollCourse")
	public String enrollCourse(@RequestParam("id")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		String userId=studentSession.get("id");
		studentSession.put("courseId", courseId);
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=student";
		}
		else
		{
			String msg= courseService.enroll(userId,courseId);
			if (msg.contains("already")) {
//				System.out.println("inside already enrolled");
				Course theCourse=courseService.getCourseById(courseId);
				List<Course> course =new ArrayList<Course>();
				course.add(theCourse);
				HashMap<String, String> instructorName=courseService.getInstructorNames(course);
				
				theModel.addAttribute("theCourse",theCourse);
				theModel.addAttribute("instructorName",instructorName);
				theModel.addAttribute("message", msg);
				
				//if(studentSession.get(""))
				return "view-course-description";
			}
			else if(!(msg.contains("enrolled"))) {
//				System.out.println("inside enrolled");
				return "error-page";
			}
		}
		
		return "redirect:/home/student/viewProfile";

	}
	
	@RequestMapping("/addCourse")
	public String addCourse(@ModelAttribute(name="course") Course course,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		
		String userId=instructorSession.get("id");
		System.out.println(userId);
		if (userId==null || userId.equals("temp"))
		{
			if(instructorSession.containsKey("status")){
				return "redirect:/home/admin/showAdminLogin";
			}
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			Course myCourse = courseService.addCourse(course);
			int newId = myCourse.getId();
			instructorSession.put("newCourseId", String.valueOf(newId));
			if(instructorSession.containsKey("status")){
				return "redirect:/home/admin/getCourse";
			}
		
			return "redirect:/home/course/showCourseHomeToInstructor?id="+newId;
		}
	}
	
	@RequestMapping("/showCourseHomeToInstructor")
	public String showCourseHomeToInstructor(@RequestParam("id")int id,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,Model theModel) {
		System.out.println("6");
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {
			String newCourseId = null;
//			if(instructorSession.containsKey("newCourseId")) {
//				newCourseId = instructorSession.get("newCourseId");
//			}
//			else {
				newCourseId = String.valueOf(id);
				instructorSession.put("newCourseId", String.valueOf(newCourseId));
				
//			}
			Course newCourse = courseService.getCourseById(String.valueOf(newCourseId));
			HashMap<String, String> studentsEnrolled = courseService.getStudentsEnrolled(newCourseId);
//			System.out.println("ANSJHCasj"+studentsEnrolled.get(0).getFirstName());
//			System.out.println(studentsEnrolled);
//			if(studentsEnrolled != null) {
//			String email[] = studentsEnrolled.get("0").split("T");
//			theModel.addAttribute("email",email[1]);
//			theModel.addAttribute("name",email[0]);}
			int numberOfStudent = studentsEnrolled.size();
			theModel.addAttribute("size",numberOfStudent);
			theModel.addAttribute("students",studentsEnrolled);
			theModel.addAttribute("course",newCourse);
			return "course-home";
		}
	}
	

	@RequestMapping("/generateCertificate")
	public String generateCert(@RequestParam("courseId")String courseId, @RequestParam("vId")String vId, @SessionAttribute(name="tempSession") HashMap<String,String> studentSession, Model theModel) {
		
		String userId=studentSession.get("id");
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=student";
		}
		else {
			String userEmail = studentSession.get("email");
			Course theCourse = courseService.getCourseById(courseId);
			List<String> responses = Utilities.getResponseLink(theCourse.getResponseLink());
			theModel.addAttribute("name",studentSession.get("firstName")+" "+studentSession.get("lastName"));
			theModel.addAttribute("course",theCourse.getName());
			theModel.addAttribute("email",studentSession.get("email"));
			String percentage = String.valueOf(Math.round(courseService.getScore(userEmail, responses, theCourse.getId())));
			theModel.addAttribute("percentage", percentage);
			theModel.addAttribute("theCourse", theCourse);
			List<Course> courses = new ArrayList<Course>();
			courses.add(theCourse);
			HashMap<String, String> instructorName=courseService.getInstructorNames(courses);
			String certGenerated = "no";
			try {
				System.out.println(percentage);
				PdfCreator.genrateCertificate(studentSession.get("firstName")+" "+studentSession.get("lastName"), theCourse.getName(), instructorName.get(String.valueOf(theCourse.getInstructor_id())), percentage);
				System.out.println("certificate generated");
				certGenerated = "yes";
				studentSession.put("certificate", "yes");
//				theModel.addAttribute("certificate", "yes");
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
				System.out.println("certificate not generated");
				studentSession.put("certificate", "no");
//				theModel.addAttribute("certificate", "no");
			}
			return "redirect:/home/course/course-home-student?courseId="+courseId+"&vId="+vId+"&certificate="+certGenerated;
		}
	}
	
	@RequestMapping("/course-home-student")
	public String course_home_student(@RequestParam("courseId")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession
							,@RequestParam("vId")String videoId, @RequestParam("certificate")String cert)
	{
		
		String userId=studentSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=student";
		}
		else 
		{
			Course theCourse=courseService.getCourseById(courseId);
			List<Course> course =new ArrayList<Course>();
			course.add(theCourse);
			HashMap<String, String> instructorName=courseService.getInstructorNames(course);
			
			//getInstructorEmail
			
			String instructorEmail=courseService.getInstructorEmail(String.valueOf(theCourse.getInstructor_id()));
			theModel.addAttribute("instructorEmail", instructorEmail);
			//getInstructorEmail
			
			//getVideoLinks
			HashMap<String, String> videos=Utilities.getVideoLinks(theCourse.getVideoLink()+","+theCourse.getQuizLink());
			
			
			//feedback
			HashMap<String, String> feedback=feedbackService.getFeedback(theCourse.getId());
			
			//feedback
		
			//convert youtube url to embeded url
				if(!(videoId.equals("1"))) 
				{
				String embededLink=Utilities.getEmbededLink(videos.get(videoId));
				
				theModel.addAttribute("embededLink",embededLink);
				}
				
				//empty model attribute for feedback form
				Feedback theFeedback=new Feedback();
				theModel.addAttribute("theFeedback",theFeedback);
				//empty model attribute for feedback form
				
				
				//check for null videos
				int flag=0;
				if(videos.containsKey(null))
				{
					flag=1;
				}
				theModel.addAttribute("flag",flag);
				//check for null videos
			theModel.addAttribute("courseId",courseId);
			theModel.addAttribute("theCourse",theCourse);
			theModel.addAttribute("instructorName",instructorName);
			theModel.addAttribute("videos",videos);
			theModel.addAttribute("feedback", feedback);
			theModel.addAttribute("certificate", cert);
			
			return "course-home-student";
		}
	}
	

	@RequestMapping("/showModifyCourse")
	public String showModifyCourse(@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,Model theModel) {
		
		String courseId = instructorSession.get("newCourseId");
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {
			
			if(!(courseId==null)) {
			
				Course course = courseService.getCourseById(courseId);
				theModel.addAttribute("course",course);
				HashMap<String, String> myVideos = Utilities.getVideoLinks(course.getVideoLink());
				HashMap<String, String> myQuizes = Utilities.getQuizLinks(course.getQuizLink());
				if(course.getResponseLink()!=null) {
				String[] myResponses = course.getResponseLink().split(",");
				theModel.addAttribute("responseList", myResponses);
				}
				if(myVideos.containsKey("null") ) {
					theModel.addAttribute("videoListSize", 0);
				}
				else {
					theModel.addAttribute("videoList",myVideos);
				}
				
				if(myQuizes.containsKey("null") ) {
					theModel.addAttribute("quizListSize", 0);
				}
				else {
					theModel.addAttribute("quizList",myQuizes);
				}
				
				
				return "modify-course";
			}
			
			else {
				return "error-page";
			}
		}
	}
	
	@RequestMapping("/modifyCourse")
	public String modifyCourse(@ModelAttribute(name="course") Course course,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		String courseId = instructorSession.get("newCourseId");
		System.out.println(courseId);
		String userId=instructorSession.get("id");
		System.out.println(userId);
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			Course myCourse = courseService.modifyCourse(course, courseId);
			int newId = myCourse.getId();
			instructorSession.put("newCourseId", String.valueOf(newId));
			return "redirect:/home/course/showModifyCourse";
		}
	}
	
	@RequestMapping("/modifyVideos")
	public String modifyVideos(@RequestParam("videoName")String videoName, @RequestParam("youtubeLink")String youtubeLink, Model theModel, @SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		String courseId = instructorSession.get("newCourseId");
		System.out.println(courseId);
		String userId=instructorSession.get("id");
		System.out.println(userId);
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			
			String uploadVideo = videoName+"@"+youtubeLink;
			System.out.println(uploadVideo);
			courseService.uploadVideo(uploadVideo,courseId);
		return "redirect:/home/course/showModifyCourse";
		}
	}
	
	@RequestMapping("/modifyQuiz")
	public String modifyQuiz(@RequestParam("quizName")String quizName, @RequestParam("docsLink")String docsLink, @RequestParam("respLink")String respLink, Model theModel, @SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		String courseId = instructorSession.get("newCourseId");
		System.out.println(courseId);
		String userId=instructorSession.get("id");
		System.out.println(userId);
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			String uploadQuiz = quizName+"@"+docsLink;
			System.out.println(uploadQuiz+ " "+respLink);
			courseService.uploadQuiz(uploadQuiz,courseId, respLink);
		return "redirect:/home/course/showModifyCourse";
		}
	}
	
	@RequestMapping("/deleteCourse")
	public String deleteCourse(Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		
		String courseId = instructorSession.get("newCourseId");
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			Boolean delete = courseService.deleteCourse(courseId);
			if(delete.equals(true)) {
				return "redirect:/home/instructor/viewProfile";
			}
			else {
				return "error-page";
			}
			
		}
	}
	
	@RequestMapping("/dropCourse")
	public String dropCourse(Model theModel, @SessionAttribute(name="tempSession") HashMap<String,String> studentSession
			, @RequestParam("courseId")String courseId)
	{
		String studentId = studentSession.get("id");
		if (studentId==null || studentId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=student";
		}
		else {

			String msg= courseService.dropCourse(studentId,courseId);
			if(!(msg.equals("course dropped")))
					{
						return "error-page";
					}
			
		}

		
		return "redirect:/home/student/viewProfile ";
	}
}