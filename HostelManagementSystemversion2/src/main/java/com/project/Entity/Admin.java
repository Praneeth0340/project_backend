package com.project.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Admin 
{
	@Id
	private Integer id=1;
	private String userName="admin@gmail.com";
	private String password="admin@123";
}
