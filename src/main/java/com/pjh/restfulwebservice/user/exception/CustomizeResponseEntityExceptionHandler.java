package com.pjh.restfulwebservice.user.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice //전체 controller 시작될때 advice bean이 실행됨
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //어떠한 exception이라도 이 method에서 detect\
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handlerAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 특정 exception 처리
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handlersUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        "Validation failed", ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
