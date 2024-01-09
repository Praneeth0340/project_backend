package com.project.Controller;

import com.project.Entity.Room;
import com.project.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @PutMapping("/updateroom")
    public ResponseEntity<Room> update(@RequestParam int id,@RequestBody Room room){
        Room r=roomService.updateRoom(id,room);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
    @GetMapping("/getbynumber")
    public ResponseEntity<Room> getRoomByNumber(@RequestParam int id,@RequestParam int number){
        Room room=roomService.getRoomByNumber(id,number);
        return new ResponseEntity<>(room,HttpStatus.OK);
    }
    @DeleteMapping("/deleteroom")
    public ResponseEntity<Room> deleteRoom(@RequestParam int id,@RequestParam int number){
        Room room=roomService.deleteRoom(id,number);
        return new ResponseEntity<>(room,HttpStatus.OK);
    }
    @GetMapping("/getallrooms")
    public ResponseEntity<List<Room>> getAllRooms(@RequestParam int id){
        List<Room> rooms= roomService.getAllRooms(id);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


}
