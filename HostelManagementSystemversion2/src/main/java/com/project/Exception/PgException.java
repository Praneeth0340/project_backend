package com.project.Exception;

public class PgException extends RuntimeException{
    public PgException(){

    }
    public PgException(String msg){
        super(msg);
    }
}
