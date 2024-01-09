package com.project.Entity;

import java.time.LocalDate; 

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
@Entity
@Data
public class Feedback 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;

	@Min(value = 1, message = "Rating should be between 1 to 10")
	@Max(value = 10, message = "Rating should be between 1 to 10")
	private Integer rating;
	private String comments;
	private LocalDate date;
	@OneToOne
	@JsonIgnore
	private User user;
	@OneToOne
	@JsonIgnore
	private Pg pg;
	
}
