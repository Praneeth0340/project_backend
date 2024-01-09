package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>
{

}
