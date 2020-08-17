package com.pjh.restfulwebservice.user.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
