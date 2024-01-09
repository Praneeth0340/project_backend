package com.project.Service;

import com.project.Entity.Room;

import java.util.List;

public interface RoomServiceInterface {
    public List<Room> getAllRooms(int pgId);
    public Room getRoomByNumber(int pgId,int roomNumber);
    public Room updateRoom(int pgId,Room room);
    public Room deleteRoom(int pgId,int roomNumber);


}
