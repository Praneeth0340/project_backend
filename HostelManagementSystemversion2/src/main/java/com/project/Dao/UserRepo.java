package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{

    User findByEmailId(String userName);
}
