package com.project.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.Data;
import lombok.Value;

@Entity
@Data
public class Booking 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private LocalDate date;
	@JsonIgnore
	private String bookingStatus;
	@OneToOne
	@JsonIgnore
	private Pg pg;
	@OneToOne
	@JsonIgnore
	private Room room;
}
