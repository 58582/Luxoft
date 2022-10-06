package com.luxoft.sb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.luxoft.sb.dao.ErrorDetials;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	//handle specific exception
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<ErrorDetials> handleResourceNotFoundException(ResourceNotFoundException exception,
				WebRequest webRequest){
			ErrorDetials errorDetails = new ErrorDetials(exception.getMessage()); 
			return new ResponseEntity<ErrorDetials>(errorDetails,HttpStatus.NOT_FOUND);
			
		}

}
