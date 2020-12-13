package com.GoCrafty.dao;

import java.util.HashMap;

import com.GoCrafty.entity.Feedback;

public interface FeedbackDAO {

	HashMap<String, String> getFeedback(int id);

	String addFeedback(Feedback theFeedback, String courseId);

}
