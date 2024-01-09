package com.project.Service;

import com.project.Entity.Feedback;

import java.util.List;

public interface FeedbackServiceInterface
{
    public Feedback giveFeedback(Feedback feedback, String key, int pgId);
    public Feedback updateFeedback(Feedback feedback,String key);
    public Feedback viewFeedback(Integer feedbackId,String key);
    public List<Feedback> viewAllFeedback(String key);
}
