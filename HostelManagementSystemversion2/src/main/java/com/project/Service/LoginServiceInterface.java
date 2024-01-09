package com.project.Service;

import com.project.Entity.Login;
import com.project.Exception.LogInException;

public interface LoginServiceInterface {
    public String userLogin(Login login) throws LogInException;
    public String userLogout(String key) throws LogInException;
}
