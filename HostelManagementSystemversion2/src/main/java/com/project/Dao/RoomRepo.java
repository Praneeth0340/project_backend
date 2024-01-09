package com.project.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer>
{

    List<Room> findByPgId(int pgId);

    Room findByPgIdAndRoomNumber(int pgId, int roomNumber);

    Room findByRoomNumber(int roomNumber);

    Room findByBookingId(int bookingId);
}
