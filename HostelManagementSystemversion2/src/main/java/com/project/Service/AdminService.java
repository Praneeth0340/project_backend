package com.project.Service;

import java.time.LocalDateTime;

import net.bytebuddy.utility.RandomString;
//import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Dao.AdminRepo;
import com.project.Dao.CurrentUserSessionRepo;
import com.project.Entity.Admin;
import com.project.Entity.CurrentUserSession;
import com.project.Entity.Login;
import com.project.Exception.LogInException;

@Service
public class AdminService implements AdminServiceInterface
{
	@Autowired
	public AdminRepo adminRepo;

	 Admin admin=new Admin();
	@Autowired
	public CurrentUserSessionRepo repo;
	
	@Override
	public String login(Login login) throws LogInException 
	{
		if(!admin.getUserName().equalsIgnoreCase(login.getUserName()))
		{
			throw new LogInException("Please Enter a valid Username");
		}
		if(admin.getPassword().equals(login.getPassword())) {
			String key= RandomString.make(6);
			CurrentUserSession currentUserSession=new CurrentUserSession(admin.getId(),"admin",key,LocalDateTime.now());
			repo.save(currentUserSession);
			return currentUserSession.toString();
		}
		else {
			throw new LogInException("Please Enter a valid Password");
		}
		
	}

	@Override
	public String logout(String key) throws LogInException {
		CurrentUserSession validateUser=repo.findByUuid(key);
		if(validateUser==null)
		{
			throw new LogInException("admin not logged in with this username");
		}
		repo.delete(validateUser);
		return "Logged out Successfully";
	}

	

	

}
