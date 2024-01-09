package com.project.Service;

import com.project.Dao.CurrentUserSessionRepo;
import com.project.Dao.FeedbackRepo;
import com.project.Dao.PgRepo;
import com.project.Dao.UserRepo;
import com.project.Entity.*;
import com.project.Exception.PgException;
import com.project.Exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService implements FeedbackServiceInterface{

    @Autowired
    private FeedbackRepo feedbackRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PgRepo pgRepo;
    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;

    @Override
    public Feedback giveFeedback(Feedback feedback,String key,int pgId){
        CurrentUserSession loggedInUser=currentUserSessionRepo.findByUuid(key);
        if(loggedInUser==null) {
            throw new UserException("Please provide a valid key to update user");
        }
        User user = userRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
        if(user.getId()== loggedInUser.getUserId())
        {
            Pg pg=pgRepo.findById(pgId).orElseThrow(()->new PgException("Pg not found"));
            feedback.setPg(pg);
            feedback.setUser(user);
            return feedbackRepo.save(feedback);
        }else throw new UserException("Invalid User Id");
    }

    @Override
    public Feedback updateFeedback(Feedback feedback,String key){
        CurrentUserSession loggedInUser=currentUserSessionRepo.findByUuid(key);
        if(loggedInUser==null) {
            throw new UserException("Please provide a valid key to update user");
        }
        User user = userRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
        if(user.getId()== loggedInUser.getUserId())
        {
            Feedback f=feedbackRepo.findById(feedback.getId()).orElse(null);
            if(feedback.getRating()!=null)
                f.setRating(feedback.getRating());
            if(feedback.getComments()!=null)
                f.setComments(feedback.getComments());

            return feedbackRepo.save(f);
        }
        else throw new UserException("Invalid User");

    }

    @Override
    public Feedback viewFeedback(Integer feedbackId,String key){
        CurrentUserSession loggedInUser=currentUserSessionRepo.findByUuid(key);
        if(loggedInUser==null) {
            throw new UserException("Please provide a valid key to update user");
        }
        User user = userRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
        if(user.getId()== loggedInUser.getUserId())
        {
            Feedback feedback=feedbackRepo.findById(feedbackId).orElse(null);
            return feedback;
        }else throw new UserException("Invalid User");

    }

    @Override
    public List<Feedback> viewAllFeedback(String key){
        CurrentUserSession loggedInUser=currentUserSessionRepo.findByUuid(key);
        if(loggedInUser==null) {
            throw new UserException("Please provide a valid key to update user");
        }
        User user = userRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
        if(user.getId()== loggedInUser.getUserId())
        {
            List<Feedback> feedbacks=feedbackRepo.findAll();
            if (!feedbacks.isEmpty())
                return feedbacks;
            else throw new PgException("Feedback not found");
        }else throw new UserException("Invalid user");
    }

}
