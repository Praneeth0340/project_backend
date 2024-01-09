package com.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore; 

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int roomNumber;
	private int floor;
	private int sharing;
	private int rent;
	@ManyToOne
	@JsonIgnore
	private Pg pg;
	@OneToOne
	@JsonIgnore
	private Booking booking;
}
