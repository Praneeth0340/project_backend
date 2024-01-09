package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>
{

}
