package com.GoCrafty.dao;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Feedback;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public HashMap<String, String> getFeedback(int id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		HashMap<String,String> email_comments=new HashMap<String, String>();
		try {
			Query query = currentSession.createQuery("from Feedback f WHERE f.courseId= :cid");
			query.setParameter("cid", id);
			@SuppressWarnings("unchecked")
			List<Feedback> feedback = query.getResultList();
			for(Feedback theFeedback: feedback)
			{
				email_comments.put(theFeedback.getStudentEmail(), theFeedback.getComment());
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return email_comments;
	}

	@Override
	public String addFeedback(Feedback theFeedback, String courseId) {
		String msg=null;
		try {
			Session  currentSession= sessionFactory.getCurrentSession();
			Feedback newFeedback=new Feedback(Integer.parseInt(courseId), theFeedback.getComment(), theFeedback.getStudentEmail());
			currentSession.save(newFeedback);
			msg="feedback added";
			
		}
		catch (Exception e) {
			msg="feedback added faild";
			e.printStackTrace();
		}
		
		return msg;
	}

}
