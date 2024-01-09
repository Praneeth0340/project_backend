package com.project.Service;

import com.project.Dao.CurrentUserSessionRepo;
import com.project.Dao.PgRepo;
import com.project.Entity.CurrentUserSession;
import com.project.Entity.Pg;
import com.project.Entity.Room;
import com.project.Exception.PgException;
import com.project.Exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PgService implements PgServiceInterface{

    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;
    @Autowired
    private PgRepo pgRepo;
    @Override
    public Pg addPg(Pg pg, String key) {
        CurrentUserSession loggedUser=currentUserSessionRepo.findByUuid(key);
        if(loggedUser==null)
            throw new UserException("Invalid key");
        if(loggedUser.getType().equalsIgnoreCase("Admin")){
            pgRepo.save(pg);
            return pg;
        }
        else throw new UserException("Access Denied");
    }

    @Override
    public Pg update(Pg pg, String key) {
        CurrentUserSession loggedUser=currentUserSessionRepo.findByUuid(key);
        if(loggedUser==null)
            throw new UserException("Invalid key");
        if(loggedUser.getType().equalsIgnoreCase("Admin")){
            Pg cur=pgRepo.findByPgname(pg.getPgname());
            cur.setPgname(pg.getPgname());
            cur.setLocation(pg.getLocation());
            cur.setPgType(pg.getPgType());
            cur.setAvailability(pg.getAvailability());
            cur.setAddress(pg.getAddress());
            cur.setOwnercontactnum(pg.getOwnercontactnum());
            cur.setFacilities(pg.getFacilities());
            cur.setAdvance(pg.getAdvance());
            cur.setTermsandconditions(pg.getTermsandconditions());
            pgRepo.save(pg);
            return pg;
        }
        else throw new UserException("Access Denied");

    }

    @Override
    public Pg delete(Pg pg, String key) {
        CurrentUserSession loggedUser=currentUserSessionRepo.findByUuid(key);
        if(loggedUser==null)
            throw new UserException("Invalid key");
        if(loggedUser.getType().equalsIgnoreCase("Admin")) {
            Pg p=pgRepo.findByPgname(pg.getPgname());
            pgRepo.delete(p);
            return p;
       }else throw new UserException("Access Denied");
    }

    @Override
    public Pg viewPgByName(String name) {
        Pg pg=pgRepo.findByPgname(name);
        if(pg!=null)
        {
            return pg;
        }
        else throw new PgException("Pg not found");
    }

    @Override
    public List<Pg> viewPgByLocation(String location) {
        List<Pg> pgs=pgRepo.findByLocation(location);
        if(!pgs.isEmpty()){
            return pgs;
        }
        else throw new PgException("No Pgs found in that Location");
    }

    @Override
    public List<Pg> viewPgByType(String type) {
        List<Pg> pgs=pgRepo.findByPgType(type);
        if(!pgs.isEmpty()){
            return pgs;
        }
        else throw new PgException("No Pgs found ");
    }
    @Override
    public List<Pg> findAllPgs(){
        List<Pg> pgs=pgRepo.findAll();
        if(!pgs.isEmpty()){
            return pgs;
        }
        else throw new PgException("No Pgs found ");
    }

    @Override
    public void addRoomToPg(int id, Room room) {

        Pg pg = pgRepo.findById(id).orElse(null);

        if (pg != null) {

            room.setPg(pg);

            if (pg.getRoom() == null) {
                pg.setRoom(new ArrayList<>());
            }
            pg.getRoom().add(room);

            pgRepo.save(pg);
        } else {
            // Handle the case where the Pg entity is not found
            throw new PgException("PG with ID " + id + " not found");
        }
    }
}
