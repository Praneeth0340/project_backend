package com.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore ;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private String mobileNum;
	private String occupation;
	private String gender;
	private String address;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Booking booking;
	
}
