package com.spittr.exception;

import com.spittr.exception.spitter.SpitterHasExistException;
import com.spittr.exception.spitter.SpitterNotFoundException;
import com.spittr.exception.transfer.TransferPartErrorException;
import com.spittr.pojo.BaseResponse;
import com.spittr.pojo.Spitter;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class AppWideExceptionHandler {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(SpitterNotFoundException.class)
    public BaseResponse<Spitter> spitterNotFound(SpitterNotFoundException e) {
        return new BaseResponse<Spitter>(e.getResponseCode().getCode(), "spitter not found", null);
    }

    @ExceptionHandler(SpitterHasExistException.class)
    public BaseResponse<Spitter> spitterHasExist(SpitterHasExistException e) {
        return new BaseResponse<Spitter>(e.getResponseCode().getCode(), "spitter has exists", null);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public BaseResponse<Object> invalidParameter(InvalidParameterException e) {
        return new BaseResponse<Object>(e.getResponseCode().getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(TransferPartErrorException.class)
    public BaseResponse<Object> transferPartError(TransferPartErrorException e) {
        return new BaseResponse<Object>(e.getResponseCode().getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(javax.naming.NoPermissionException.class)
    public BaseResponse<Object> noPermission(NoPermissionException e) {
        return new BaseResponse<Object>(e.getResponseCode().getCode(), e.getMessage(), null);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public BaseResponse<Object> databaseError(Exception e) {
        logger.error("Exception is: ", e);
        return null;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleError(Exception e) {
        logger.error("Exception is: ", e);
        return null;
    }
}
