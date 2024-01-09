package com.project.Controller;

import com.project.Entity.Feedback;
import com.project.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/give")
    public ResponseEntity<Feedback> giveFeedback(@RequestBody Feedback feedback, @RequestParam String key,@RequestParam int pgId){
        Feedback f=feedbackService.giveFeedback(feedback, key, pgId);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public  ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback feedback,@RequestParam String key){
        Feedback f=feedbackService.updateFeedback(feedback, key);
        return new ResponseEntity<>(f,HttpStatus.OK);
    }
    @GetMapping("/view")
    public ResponseEntity<Feedback> viewFeedback(@RequestParam Integer feedbackId,@RequestParam String key){
        Feedback feedback=feedbackService.viewFeedback(feedbackId, key);
        return new ResponseEntity<>(feedback,HttpStatus.OK);
    }
    @GetMapping("/viewall")
    public ResponseEntity<List<Feedback>> viewAll(@RequestParam String key){
        List<Feedback> feedbacks=feedbackService.viewAllFeedback(key);
        return new ResponseEntity<>(feedbacks,HttpStatus.OK);
    }
}
