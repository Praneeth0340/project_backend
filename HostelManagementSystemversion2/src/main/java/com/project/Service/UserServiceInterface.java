package com.project.Service;

import com.project.Entity.User;

import java.util.List;

public interface UserServiceInterface {


    public User addUser(User user);
    public User updateUser(User user,String key);
    public User deleteUser(String mail);
    public User viewUser(String mail);
    public List<User> findAllUsers();

}
