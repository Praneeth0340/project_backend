package com.project.Service;

import com.project.Entity.Login;
import com.project.Exception.LogInException;

public interface AdminServiceInterface 
{
	public String login(Login login) throws LogInException;
	public String logout(String key) throws LogInException;
}
