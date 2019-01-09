package com.mbicycle.imarket.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    public static ResponseEntity entityWithStatus(boolean flag) {
        return flag ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity entityWithStatus(int status) {
        return new ResponseEntity(HttpStatus.valueOf(302));
    }

    public static <T> ResponseEntity<T> entityWithContent(T t) {
        return t != null ? new ResponseEntity<>(t, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static <T> ResponseEntity<T> entityWithContent(T t, int status) {
        return t != null ? new ResponseEntity<>(t, HttpStatus.valueOf(status)) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
