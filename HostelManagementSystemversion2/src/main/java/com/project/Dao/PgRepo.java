package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Pg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgRepo extends JpaRepository<Pg, Integer>
{

    Pg findByPgname(String pgname);

    List<Pg> findByLocation(String location);

    List<Pg> findByPgType(String type);
}
