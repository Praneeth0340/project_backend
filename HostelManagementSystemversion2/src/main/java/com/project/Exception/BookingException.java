package com.project.Exception;

public class BookingException extends RuntimeException{
    public BookingException(){}
    public BookingException(String msg){
        super(msg);
    }
}
