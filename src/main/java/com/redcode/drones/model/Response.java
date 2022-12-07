package com.redcode.drones.model;

import lombok.Data;

import java.util.Date;

@Data
public class Response {

    private static final long serialVersionUID = 1435632290468671374L;

    private String messageDetail;
    private String errorMessage;

}
