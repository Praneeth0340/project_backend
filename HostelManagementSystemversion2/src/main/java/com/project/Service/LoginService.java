package com.project.Service;

import com.project.Dao.CurrentUserSessionRepo;
import com.project.Dao.UserRepo;
import com.project.Entity.CurrentUserSession;
import com.project.Entity.Login;
import com.project.Entity.User;
import com.project.Exception.LogInException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService implements LoginServiceInterface {
    @Autowired
    public UserRepo userRepo;

    @Autowired
    public CurrentUserSessionRepo repo;

    @Override
    public String userLogin(Login login) throws LogInException
    {
        User user=userRepo.findByEmailId(login.getUserName());

        if(user==null)
        {
            throw new LogInException("Please Enter a valid Username");
        }
        Optional<CurrentUserSession> cur=repo.findById(user.getId());
        if(cur.isPresent()) {

            if (user.getPassword().equals(login.getPassword())) {
                return cur.toString();
            }
        }
            String key = RandomString.make(6);
            CurrentUserSession currentUserSession=new CurrentUserSession(user.getId(),"user",key, LocalDateTime.now());
            repo.save(currentUserSession);

            return currentUserSession.toString();

    }

    @Override
    public String userLogout(String key) throws LogInException {
        CurrentUserSession validateUser=repo.findByUuid(key);
        if(validateUser==null)
        {
            throw new LogInException("admin not logged in with this username");
        }
        repo.delete(validateUser);
        return "Logged out Successfully";
    }
}
