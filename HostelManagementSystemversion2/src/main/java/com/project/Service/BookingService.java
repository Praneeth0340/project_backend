package com.project.Service;

import com.project.Dao.*;
import com.project.Entity.*;
import com.project.Exception.BookingException;
import com.project.Exception.PgException;
import com.project.Exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService implements BookingServiceInterface{

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PgRepo pgRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;

    @Override
    public Booking newBooking(int pgId, Booking booking, String key, int roomId){
        CurrentUserSession loggedInUser=currentUserSessionRepo.findByUuid(key);
        User user=userRepo.findById(loggedInUser.getUserId()).orElseThrow(()-> new UserException("User with User Id "+ loggedInUser.getUserId()+" does not exist"));
        Pg pg =pgRepo.findById(pgId).orElseThrow(()-> new PgException("User with Pg Id "+ pgId+" does not exist"));
        if(pg.getAvailability().equalsIgnoreCase("available"))
        {
            Room room=roomRepo.findById(roomId).orElse(null);
            room.setBooking(booking);
            booking.setPg(pg);
            booking.setRoom(room);
            booking.setBookingStatus("pending");
            user.setBooking(booking);
            return bookingRepo.save(booking);
        }else throw new BookingException("booking failed");
    }
    @Override
    public Booking updateBooking(int bookingId, String key,String status){
        CurrentUserSession currentUserSession=currentUserSessionRepo.findByUuid(key);
        User user=userRepo.findById(currentUserSession.getUserId()).orElseThrow();
            Booking book=bookingRepo.findById(bookingId).orElse(null);
            if(book!=null)
            {
                book.setBookingStatus(status);
                Booking updated=bookingRepo.save(book);
                user.setBooking(updated);
                return updated;
            }else throw new BookingException("update failed");
    }
    @Override
    public Booking deleteBooking(int bookingId, String key){
        CurrentUserSession currentUserSession=currentUserSessionRepo.findByUuid(key);
        User user=userRepo.findById(currentUserSession.getUserId()).orElseThrow();
        Booking book=bookingRepo.findById(bookingId).orElse(null);
        if(book!=null)
        {
            Room room=book.getRoom();
            room.setBooking(null);
            bookingRepo.delete(book);
           user.setBooking(null);
           return book;
        }else throw  new BookingException("booking not found");
    }
    @Override
    public Booking getBookingById(int bookingId){
        Booking book=bookingRepo.findById(bookingId).orElseThrow(()->new BookingException("no booking found"));
        return book;
    }
    @Override
    public List<Booking> getAllBookings(String key){
        CurrentUserSession currentUserSession=currentUserSessionRepo.findByUuid(key);
        if(currentUserSession.getType().equalsIgnoreCase("Admin")){
            List<Booking> bookings=bookingRepo.findAll();
            if(bookings.size()!=0)
                return bookings;
            else
                throw new BookingException("no bookings found");
        }throw new BookingException("Access Denied");
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, String key){
        CurrentUserSession currentUserSession=currentUserSessionRepo.findByUuid(key);
        if(currentUserSession.getType().equalsIgnoreCase("Admin")){
            List<Booking> bookings=bookingRepo.findAll();
            List<Booking> bookingsByDate=new ArrayList<>();
            if(bookings.size()!=0){
                for(Booking b:bookings){
                    if(date.equals(b.getDate()))
                        bookingsByDate.add(b);
                }
                if(bookingsByDate.size()!=0){
                    return bookingsByDate;
                }
                else throw new BookingException("No bookings found with the specified date");

            }
            else throw new BookingException("No bookings found");
        }
        else throw new BookingException("Access Denied");
    }



}
