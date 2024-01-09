package com.project.Service;

import com.project.Dao.PgRepo;
import com.project.Dao.RoomRepo;
import com.project.Entity.Pg;
import com.project.Entity.Room;
import com.project.Exception.PgException;
import com.project.Exception.RoomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements RoomServiceInterface {

    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private PgRepo pgRepo;
    @Override
    public List<Room> getAllRooms(int pgId) {
        Pg pg=pgRepo.findById(pgId).orElse(null);
        if (pg==null)
            throw new PgException("Pg not Found");
        List<Room> rooms=roomRepo.findByPgId(pgId);
        return rooms;
    }

    @Override
    public Room getRoomByNumber(int pgId, int roomNumber) {
        Pg pg=pgRepo.findById(pgId).orElse(null);
        if (pg==null)
            throw new PgException("Pg not Found");
        Room room=roomRepo.findByPgIdAndRoomNumber(pgId,roomNumber);
        if(room!=null)
        return room;
        else throw new RoomException("Room doesnot exist");
    }

    @Override
    public Room updateRoom(int pgId, Room room) {
        Pg pg=pgRepo.findById(pgId).orElse(null);
        if (pg==null)
            throw new PgException("Pg not Found");
        Room curr=roomRepo.findByRoomNumber(room.getRoomNumber());
        if(room.getRoomNumber()!=0)
            curr.setRoomNumber(room.getRoomNumber());
        if(room.getFloor()!=0)
            curr.setFloor(room.getFloor());
        if(room.getSharing()!=0)
            curr.setSharing(room.getSharing());
        if(room.getRent()!=0)
            curr.setRent(room.getRent());
        return roomRepo.save(curr);

    }

    @Override
    public Room deleteRoom(int pgId, int roomNumber) {
        Pg pg=pgRepo.findById(pgId).orElse(null);
        if (pg==null)
            throw new PgException("Pg not Found");
        Room room=roomRepo.findByPgIdAndRoomNumber(pgId,roomNumber);
        if(room!=null){
            roomRepo.delete(room);
            return room;
        }else throw new RoomException("Room not Found");
    }
}
