package com.testspring.employeemanagerapp.exception;


public class  UserAlreadyExistException extends RuntimeException{
    
    
    public UserAlreadyExistException(String message){
        super(message);
    }

}

