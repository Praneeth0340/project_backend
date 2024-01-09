package com.project.Entity;

import java.util.List ;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pg 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String pgname;
	private String location;
	private String pgType;
	private String availability;
	private String address;
	private Long ownercontactnum;
	@ElementCollection
	private List<String> facilities;
	private int advance;
	private String termsandconditions;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "pg")
	@JsonIgnore
	private List<Room> room;

	
}
