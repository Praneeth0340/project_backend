package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer>
{

}
