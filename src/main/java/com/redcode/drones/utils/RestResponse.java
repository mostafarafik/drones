package com.redcode.drones.utils;

import com.redcode.drones.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class RestResponse {

    private static final Logger logger = LoggerFactory.getLogger(RestResponse.class);

    public static ResponseEntity<?> error(String errorMessage) {

        logger.warn(errorMessage);
        Response response = new Response();
        response.setErrorMessage("Request Failed With An Exception");
        response.setMessageDetail(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static ResponseEntity<?> badRequest(String errorMessage) {

        logger.error("Bad request error {}", errorMessage);
        Response response = new Response();
        response.setErrorMessage("Request Failed With An Exception");
        response.setMessageDetail(errorMessage);
        return ResponseEntity.badRequest().body(response);
    }

}