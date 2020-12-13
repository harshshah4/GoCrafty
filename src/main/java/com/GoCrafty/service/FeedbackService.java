package com.GoCrafty.service;

import java.util.HashMap;

import com.GoCrafty.entity.Feedback;

public interface FeedbackService {

	HashMap<String, String> getFeedback(int id);

	String addFeedback(Feedback theFeedback, String courseId);

}
