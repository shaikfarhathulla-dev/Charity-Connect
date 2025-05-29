package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired FeedbackService feedbackService;

    @PostMapping("/feedback")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback, @RequestParam Long userId){
        Feedback f = feedbackService.addFeedback(feedback,userId);

        if(f!=null){
            return ResponseEntity.status(201).body(f);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }

    }

    @GetMapping("/feedback")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllFeedbacks(){
        List<Feedback> f = feedbackService.getAllFeedback();

        if(f!=null){
            return ResponseEntity.status(200).body(f);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }

    }

    @GetMapping("/feedback/user/{userId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getAllFeedbackByUserId(@PathVariable Long userId){
        List<Feedback> f = feedbackService.getAllFeedbackByUserId(userId);

        if(f!=null){
            return ResponseEntity.status(200).body(f);
        }
        else{
            return ResponseEntity.status(404).body(null);
        }

    }

    @DeleteMapping("/feedback/{feedbackId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId){
        Feedback f = feedbackService.deleteFeedback(feedbackId);

        if(f!=null){
            return ResponseEntity.status(200).body(f);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }

    }

    @PutMapping("/feedback/{feedbackId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> editFeedback(@PathVariable Long feedbackId, @RequestBody Feedback updatedFeedback){
        Feedback f = feedbackService.editFeedback(feedbackId, updatedFeedback);

        if(f!=null){
            return ResponseEntity.status(200).body(f);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }
        
    }

}
