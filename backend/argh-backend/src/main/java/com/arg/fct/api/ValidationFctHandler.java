package com.arg.fct.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationFctHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException e ){
		return ResponseEntity.badRequest().body(e.getFieldError().getField() + " : " + e.getFieldError().getDefaultMessage());
	}
}
