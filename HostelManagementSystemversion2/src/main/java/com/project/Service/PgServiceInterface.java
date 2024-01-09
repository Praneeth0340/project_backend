package com.project.Service;

import com.project.Entity.Pg;
import com.project.Entity.Room;

import java.util.List;

public interface PgServiceInterface {

    public Pg addPg(Pg pg,String key);
    public Pg update(Pg pg,String key);
    public Pg delete(Pg pg,String key);
    public Pg viewPgByName(String name);
    public List<Pg> viewPgByLocation(String location);
    public List<Pg> viewPgByType(String type);
    public List<Pg> findAllPgs();
    public void addRoomToPg(int id, Room room);



}
