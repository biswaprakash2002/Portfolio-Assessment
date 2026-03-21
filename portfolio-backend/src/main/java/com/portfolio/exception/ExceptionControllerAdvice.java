package com.portfolio.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@Autowired
	Environment environment;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorPortfolioMessage> exceptionHandler(Exception exception) {
		ErrorPortfolioMessage error = new ErrorPortfolioMessage();
		error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorPortfolioMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(PortfolioException.class) //
	public ResponseEntity<ErrorPortfolioMessage> portfoliokexceptionHandler(PortfolioException exception) {
		ErrorPortfolioMessage error = new ErrorPortfolioMessage();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorPortfolioMessage>(error, HttpStatus.NOT_FOUND);
	}
}
/////////////////////////////////////////////
//	Client Request
//		↓
//	Controller
//		↓
//	Service
//		↓
//	❌ Exception occurs (not handled)
//		↓
//	Spring looks for @ExceptionHandler(Exception.class)
//		↓
//	exceptionHandler() is called
//
/////////////////////////////////////////////
//	Client Request
//		↓
//	Controller
//		↓
//	Service
//		↓
//	throw new PortfolioException(...)
//		↓
//	Spring matches exception type
//		↓
//	portfoliokexceptionHandler() is called
/////////////////////////////////////////////