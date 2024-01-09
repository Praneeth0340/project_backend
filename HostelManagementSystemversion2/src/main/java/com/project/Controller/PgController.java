package com.project.Controller;

import com.project.Entity.Pg;
import com.project.Entity.Room;
import com.project.Service.PgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg")
public class PgController {

    @Autowired
    private PgService pgService;

    @PostMapping("/addpg")
    public ResponseEntity<Pg> addPg(@RequestBody Pg pg, @RequestParam String key){
        Pg p=pgService.addPg(pg,key);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }
    @PutMapping("/updatepg")
    public ResponseEntity<Pg> updatePg(@RequestBody Pg pg, @RequestParam String key){
        Pg p=pgService.update(pg,key);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @DeleteMapping("/deletepg")
    public ResponseEntity<Pg> deletePg(@RequestBody Pg pg, @RequestParam String key){
        Pg p=pgService.delete(pg,key);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @GetMapping("/viewbylocation")
    public ResponseEntity<List<Pg>> viewByLocation(@RequestParam String location){
        List<Pg> p=pgService.viewPgByLocation(location);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @GetMapping("/viewbytype")
    public ResponseEntity<List<Pg>> viewByType(@RequestParam String type){
        List<Pg> p=pgService.viewPgByType(type);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @GetMapping("/viewall")
    public ResponseEntity<List<Pg>> viewAll(){
        List<Pg> p=pgService.findAllPgs();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @PostMapping("/addroom")
    public ResponseEntity<Room> addRoom(@RequestParam int id, @RequestBody Room room){
        pgService.addRoomToPg(id, room);
        return new ResponseEntity<>(room,HttpStatus.CREATED);
    }
}
