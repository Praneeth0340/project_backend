package com.project.Exception;

public class UserException extends RuntimeException{
    public UserException(){};
    public UserException(String msg){
        super(msg);
    }
}
