package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;

@Service("feedbackServiceImpl")
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired FeedbackRepo feedbackRepo;
    @Autowired UserRepo userRepo;

    @Override
    public Feedback addFeedback(Feedback feedback,Long userId) {
       Optional<User> u=userRepo.findById(userId);

       if(u.isPresent()){
        feedback.setUser(u.get());
        return feedbackRepo.save(feedback);
       }
       else{
        return null;
       }
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    @Override
    public List<Feedback> getAllFeedbackByUserId(Long userId) {
        return feedbackRepo.findByUserUserId(userId);
    }

    @Override
    public Feedback editFeedback(Long feedbackId, Feedback updatedFeedback) {
       updatedFeedback.setFeedbackId(feedbackId);
       return feedbackRepo.save(updatedFeedback);
    }

    @Override
    public Feedback deleteFeedback(Long feedbackId) {
       Optional<Feedback> f = feedbackRepo.findById(feedbackId);

       if(f.isPresent()){
        feedbackRepo.deleteById(feedbackId);
        return f.get();
       }
       else{
        return null;
       }
    }

}
