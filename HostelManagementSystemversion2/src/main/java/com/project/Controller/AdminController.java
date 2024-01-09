package com.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Entity.Login;
import com.project.Exception.LogInException;
import com.project.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestBody Login login)throws LogInException{
		String msg=adminService.login(login);
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}
	@PostMapping("/logout")
	public ResponseEntity<String> adminLogout(@RequestParam String key)throws LogInException{
		String msg=adminService.logout(key);
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}

}
