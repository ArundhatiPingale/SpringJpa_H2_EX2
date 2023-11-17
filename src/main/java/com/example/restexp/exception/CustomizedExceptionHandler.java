package com.example.restexp.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restexp.UserNotFoundException;


@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		
		ErrorDetails er=new ErrorDetails(LocalDate.now(), ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(er, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> UserNotFoundExceptionHandler(Exception ex, WebRequest request) throws Exception {
		
		ErrorDetails er=new ErrorDetails(LocalDate.now(), ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(er, HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	
		ErrorDetails er=new ErrorDetails(LocalDate.now(), ex.getFieldError().getDefaultMessage(),request.getDescription(false));
		
		return new ResponseEntity(er, HttpStatus.BAD_REQUEST);
		
	}
}
