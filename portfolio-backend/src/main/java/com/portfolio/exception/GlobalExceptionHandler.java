//package com.portfolio.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(PortfolioException.class)
//    public ResponseEntity<String> handlePortfolioException(PortfolioException ex) {
//    	System.out.println(ex);
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//    	System.out.println(ex);
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Something went wrong : "+ex.getLocalizedMessage());
//    }
//}




//               ExceptionControllerAdvice  is present