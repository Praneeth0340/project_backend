package com.project.Controller;

import com.project.Entity.User;
import com.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User u=userService.addUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user,@RequestParam String key){
        User u=userService.updateUser(user, key);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam String mail){
        User user=userService.deleteUser(mail);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/viewUser")
    public ResponseEntity<User> viewUser(@RequestParam String mail){
        User user=userService.viewUser(mail);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.findAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
