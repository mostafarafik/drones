package com.redcode.drones.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MEDICATION")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicationId;
    private String name;
    private Double weight;
    private String code;
    private byte[] image;

}
