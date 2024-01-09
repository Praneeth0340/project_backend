package com.project.Service;

import com.project.Dao.CurrentUserSessionRepo;
import com.project.Dao.UserRepo;
import com.project.Entity.CurrentUserSession;
import com.project.Entity.User;
import com.project.Exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;
    @Override
    public User addUser(User user) {
        User u=userRepo.findByEmailId(user.getEmailId());
        if(u!=null){
            throw new UserException("User already exist with this user name");
        }
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user, String key) {
        CurrentUserSession loggedinuser=currentUserSessionRepo.findByUuid(key);
        if (loggedinuser==null)
        {
            throw new UserException("Enter valid key to update user");
        }
        User curr=userRepo.findByEmailId(user.getEmailId());
        if(curr!=null){
            if(user.getEmailId()!=null)
                curr.setEmailId(user.getEmailId());
            if(user.getPassword()!=null)
                curr.setPassword(user.getPassword());
            if(user.getAddress()!=null)
                curr.setAddress(user.getAddress());
            if (user.getFirstName() != null)
                curr.setFirstName(user.getFirstName());
            if (user.getLastName() != null)
                curr.setLastName(user.getLastName());
            if (user.getGender() != null)
                curr.setGender(user.getGender());
            if(user.getMobileNum()!=null)
                curr.setMobileNum(user.getMobileNum());
            if(user.getOccupation()!=null)
                curr.setOccupation(user.getOccupation());
            User saved=userRepo.save(curr);
            return saved;
        }
        else throw new UserException("User not found");
    }

    @Override
    public User deleteUser(String mail) {
        User user=userRepo.findByEmailId(mail);
        if(user!=null) {
            userRepo.delete(user);
            return user;
        }
        else throw new UserException("User not found to delete");
    }

    @Override
    public User viewUser(String mail) {
        User user=userRepo.findByEmailId(mail);
        if(user!=null)
        {
            return user;
        }
        else throw new UserException("User Not Found");
    }

    @Override
    public List<User> findAllUsers(){
        List<User> users=userRepo.findAll();
        return users;
    }
}
