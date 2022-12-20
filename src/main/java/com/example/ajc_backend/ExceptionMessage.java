package com.example.ajc_backend;

public class ExceptionMessage  extends Exception{

	private String message;
	
    public ExceptionMessage( String message) {
		// TODO Auto-generated constructor stub
    	this.message = message;
	}
    
    public String getMessage() {
        return message;
    }
    
    

}
