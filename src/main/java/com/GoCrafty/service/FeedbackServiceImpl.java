package com.GoCrafty.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.CourseDAO;
import com.GoCrafty.dao.FeedbackDAO;
import com.GoCrafty.entity.Feedback;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Override
	@Transactional
	public HashMap<String, String> getFeedback(int id) {
		return feedbackDAO.getFeedback(id);
	}

	@Override
	@Transactional

	public String addFeedback(Feedback theFeedback, String courseId) {
		return feedbackDAO.addFeedback(theFeedback,courseId);

	}

}
