package com.GoCrafty.dao;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.CourseEnrolled;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.service.SheetsAndJava;
import com.GoCrafty.entity.Student;

@Repository
public class CourseDAOImpl implements CourseDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<String> showCategories(String recruitment) {
		System.out.println(recruitment);
		Session  currentSession= sessionFactory.getCurrentSession();
		String query = Integer.parseInt(recruitment) == 1? "select distinct c.category from Course c":"select distinct c.category from Course c where c.category!='Recruitment'"; 
		List<String> categories = currentSession.createQuery(query, String.class)
			.getResultList();
		
		
		return categories;
	}

	@Override
	public List<Course> showCoursesByCategory(String category) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from Course c WHERE c.category= :category");
		query.setParameter("category", category);
		@SuppressWarnings("unchecked")
		List<Course> course = query.getResultList();
		 
		return course;
	}

	@Override
	public HashMap<String, String> getInstructorNames(List<Course> course) {
		Session  currentSession= sessionFactory.getCurrentSession();

		HashMap<String, String> courseId_InstructorName = new HashMap<String, String>();
		Query query =null;
		Instructor theInstructor=new Instructor();
		for (Course theCourse:course)
		{
			query = currentSession.createQuery("from Instructor i WHERE i.id= :id");
			query.setParameter("id", theCourse.getInstructor_id());
			theInstructor=(Instructor) query.getSingleResult();
			courseId_InstructorName.put(String.valueOf(theCourse.getInstructor_id()), theInstructor.getFirstName()+" "+theInstructor.getLastName());
		}
		
		return courseId_InstructorName;
	}

	@Override
	public Course getCourseById(String id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Course theCourse=new Course();
		try {
			Query query=currentSession.createQuery("from Course c WHERE c.id= :id");
			query.setParameter("id", Integer.parseInt(id));
			theCourse=(Course) query.getSingleResult();
			
		}
		catch (Exception e) {
			return null;
		}
		
		return theCourse;
	}

	@Override
	public String enroll(String useId, String courseId) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			Query query=currentSession.createQuery("select count(*) from CourseEnrolled c WHERE c.studentId= :sid and c.courseId= :cid");
			query.setParameter("cid", Integer.parseInt(courseId));
			query.setParameter("sid", Integer.parseInt(useId));
//			System.out.println(query.getSingleResult());
			int alreadyregistered = Integer.parseInt(query.getSingleResult().toString());
			System.out.println(alreadyregistered);
			if (alreadyregistered == 0) {
				CourseEnrolled enroll= new CourseEnrolled(Integer.parseInt(courseId),Integer.parseInt(useId), "0");
				currentSession.save(enroll);
				return "You are enrolled";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return "You are already Enrolled. Cannot enroll again.";
		
	}

	@Override
	public Course addCourse(Course course) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			Course theCourse = new Course(course.getName(), course.getDescription(),course.getEstimatedTimeToComplete(),course.getCategory(),course.getInstructor_id(), course.getVideoLink(), course.getQuizLink(), course.getResponseLink());	
			currentSession.save(theCourse);
			return theCourse;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getEnrolledCourses(int id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		ArrayList<CourseEnrolled> courseEnrolled=new ArrayList<CourseEnrolled>();
		ArrayList<Course> courseList=new ArrayList<Course>();

		try 
		{
			Query query=currentSession.createQuery("from CourseEnrolled c WHERE c.studentId= :id");
			query.setParameter("id", id);
			courseEnrolled=(ArrayList<CourseEnrolled>)query.getResultList();
			
			for(CourseEnrolled theCourseEnrolled: courseEnrolled)
			{
				int courseId=theCourseEnrolled.getCourseId();
				Course theCourse=getCourseById(String.valueOf(courseId));
				courseList.add(theCourse);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return courseList;
	}

	@Override
	public Course modifyCourse(Course course, String courseId) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = Integer.parseInt(courseId);
			Course myCourse = currentSession.get(Course.class,id);
			myCourse.setName(course.getName());
			myCourse.setDescription(course.getDescription());
			myCourse.setEstimatedTimeToComplete(course.getEstimatedTimeToComplete());
			myCourse.setCategory(course.getCategory());
			currentSession.save(myCourse);
			return myCourse;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public float getScore(String email, List<String> responseLink, int courseID) {
		System.out.println(email+ " "+responseLink);
		float percentage = 0;
		for(String response_ID : responseLink) {
			try {
				System.out.println(response_ID);
				 percentage = SheetsAndJava.getGrades(email, response_ID);
				 updateGrade(email, String.valueOf(percentage), courseID);
				System.out.println(percentage);
			} catch (IOException | GeneralSecurityException e) {
				e.printStackTrace();
			}
		}
		return percentage;
	}
	
	private void updateGrade(String email, String percentage, int courseID) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Query query=currentSession.createQuery("from Student s WHERE s.email= :email");
		query.setParameter("email", email);
		Student theStudent = (Student) query.getSingleResult();
		
		query=currentSession.createQuery("from CourseEnrolled c WHERE c.studentId= :sid and c.courseId = :cid");
		query.setParameter("sid", String.valueOf(theStudent.getId()));
		query.setParameter("cid", String.valueOf(courseID));
		CourseEnrolled courseEnrolled = (CourseEnrolled) query.getSingleResult();
		courseEnrolled.setGrades(percentage);
		currentSession.save(courseEnrolled);		
	}

	public String uploadVideo(String uploadVideo, String courseId) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = Integer.parseInt(courseId);
			Query query=currentSession.createQuery("select c.videoLink from Course c WHERE c.id= :id");
			query.setParameter("id", id);
			String videoLink = null;
			videoLink = (String)query.getSingleResult();
			if(videoLink ==null) {
				videoLink = uploadVideo;
			}
			else {
			videoLink = videoLink+","+uploadVideo;
			}
			query = currentSession.createQuery("update Course c set c.videoLink= :link where c.id= :id");
			query.setParameter("id", id);
			query.setParameter("link", videoLink);
			query.executeUpdate();
			return "ok";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "notok";
		}
	}

	@Override
	public String uploadQuiz(String uploadQuiz, String courseId, String responseLink) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = Integer.parseInt(courseId);
			Query query=currentSession.createQuery("select c.quizLink from Course c WHERE c.id= :id");
			query.setParameter("id", id);
			String quizLink = null;
			quizLink = (String)query.getSingleResult();
			if(quizLink ==null) {
				quizLink = uploadQuiz;
			}
			else {
			quizLink = quizLink+","+uploadQuiz;
			}
			query = currentSession.createQuery("update Course c set c.quizLink= :link where c.id= :id");
			query.setParameter("id", id);
			query.setParameter("link", quizLink);
			query.executeUpdate();
			
			query=currentSession.createQuery("select c.responseLink from Course c WHERE c.id= :id");
			query.setParameter("id", id);
			String responseLink1 = null;
			responseLink1 = (String)query.getSingleResult();
			if(responseLink1 ==null) {
				responseLink1 = responseLink;
			}
			else {
			responseLink1 = responseLink1+","+responseLink;
			}
			query = currentSession.createQuery("update Course c set c.responseLink= :link where c.id= :id");
			query.setParameter("id", id);
			query.setParameter("link", responseLink1);
			query.executeUpdate();
			return "ok";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "notok";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, String> getStudentsEnrolled(String newCourseId) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = Integer.parseInt(newCourseId);
			Query query=currentSession.createQuery("from CourseEnrolled s WHERE s.courseId= :id");
			query.setParameter("id", id);
			ArrayList<CourseEnrolled> enrolleds = new ArrayList<CourseEnrolled>();
			enrolleds = (ArrayList<CourseEnrolled>) query.getResultList();
//			System.out.println("fewbvbwuv"+students);
//			System.out.println("fewbvbv"+newCourseId);
			HashMap<String, String> s= new HashMap<String, String>();
			Student tempStudent;
			for(CourseEnrolled enrolled:enrolleds) {
				tempStudent = currentSession.get(Student.class, enrolled.getStudentId());
				s.put(enrolled.getGrades(), tempStudent.getFirstName()+" "+tempStudent.getLastName()+"@"+tempStudent.getEmail());
			}
//			System.out.println("wdcwjhwww"+s.get(0).getFirstName());
			return s;
		}
		catch (Exception e) {
			e.printStackTrace();
			 HashMap<String, String> s = null;
			 return s;
		}
	}

	@Override
	public ArrayList<String> getGrades(List<Course> enrolledCourses, int id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		ArrayList<String> grades = new ArrayList<String>();
		for (Course theCourse:enrolledCourses )
		{
			Query query=currentSession.createQuery("select c.grades from CourseEnrolled c WHERE c.studentId= :sid AND c.courseId= :cid");
			query.setParameter("sid", id);
			query.setParameter("cid", theCourse.getId());
			String theGrade=(String) query.getSingleResult();
			grades.add(theGrade);
			
		}
		
		
		System.out.println("course dao impl size: "+grades.size());
		return grades;
	}

	@Override
	public Boolean deleteCourse(String courseId) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			int id = Integer.parseInt(courseId);
			Course myCourse = currentSession.get(Course.class, id);
			currentSession.delete(myCourse);
			return true;
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String dropCourse(String studentId, String courseId) {
		try {
			Session  currentSession= sessionFactory.getCurrentSession();
			Query query = currentSession.createQuery("delete CourseEnrolled c  where c.courseId= :cid and c.studentId = :sid");
			query.setParameter("sid",studentId);
			query.setParameter("cid",courseId);

			query.executeUpdate();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "course dropped";
	}
	@Override
	public String getInstructorEmail(String courseId) {
		String instructorEmail;
		try {
		Session  currentSession= sessionFactory.getCurrentSession();
		Query query=currentSession.createQuery("from Instructor i WHERE i.id= :id");
		query.setParameter("id", courseId);
		Instructor theInstructor=(Instructor) query.getSingleResult();
		instructorEmail=theInstructor.getEmail();
		}
		catch (Exception e) {
			e.printStackTrace();
			instructorEmail=null;
			
		}
		return instructorEmail;
	}

}
