package com.project.Entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentUserSession {
	
	@Id
	@Column(unique = true)
	private Integer userId;
	private String type;
	private String uuid;
	private LocalDateTime localDateTime;

}
