package com.project.Service;

import com.project.Entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingServiceInterface {
    public Booking newBooking(int pgId, Booking booking, String key, int roomId);
    public Booking updateBooking(int bookingId, String key,String status);
    public Booking deleteBooking(int bookingId, String key);
    public Booking getBookingById(int bookingId);
    public List<Booking> getAllBookings(String key);
    public List<Booking> getBookingsByDate(LocalDate date, String key);
}
