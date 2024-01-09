package com.project.Controller;

import com.project.Entity.Booking;
import com.project.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingContoller {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Booking> newBooking(@RequestParam int pgId, @RequestBody Booking booking,@RequestParam String key,@RequestParam int roomId){
        Booking book=bookingService.newBooking(pgId, booking, key, roomId);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Booking> updateBooking(@RequestParam int bookingId,@RequestParam String key, @RequestParam String status){
        Booking booking=bookingService.updateBooking(bookingId, key, status);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Booking> deleteBooking(@RequestParam int bookingId,@RequestParam String key){
        Booking booking=bookingService.deleteBooking(bookingId, key);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }
    @GetMapping("/getbooking")
    public ResponseEntity<Booking> getBookingById(@RequestParam int bookingId){
        Booking booking=bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }
    @GetMapping("/getbydate")
    public ResponseEntity<List<Booking>> getBookingByDate(@RequestParam String date,@RequestParam String key){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<Booking> booking=bookingService.getBookingsByDate(localDate, key);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Booking>> getAllBookings(@RequestParam String key){
        List<Booking> booking=bookingService.getAllBookings(key);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }
}
