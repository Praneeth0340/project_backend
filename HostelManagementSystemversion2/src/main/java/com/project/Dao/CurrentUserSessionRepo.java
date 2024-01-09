package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.CurrentUserSession;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession,Integer>
{

	CurrentUserSession findByUuid(String key);

}
