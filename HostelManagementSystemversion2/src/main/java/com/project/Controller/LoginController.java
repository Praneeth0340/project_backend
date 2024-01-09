package com.project.Controller;

import com.project.Entity.Login;
import com.project.Exception.LogInException;
import com.project.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userlogin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody Login login)throws LogInException {
        String msg=loginService.userLogin(login);
        return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> userLogout(@RequestParam String key)throws LogInException{
        String msg=loginService.userLogout(key);
        return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
    }
}
