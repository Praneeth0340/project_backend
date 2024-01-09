package com.project.Entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Login 
{
	@Id
	private Integer id;
	private String userName;
	private String password;
}
