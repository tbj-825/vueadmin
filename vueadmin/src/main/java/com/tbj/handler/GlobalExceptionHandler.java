package com.tbj.handler;

import com.tbj.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Result GlobalException(Exception e){
        Result result = new Result();
        result.setCode("400");
        result.setMsg(e.getMessage());
        return result;
    }
}
