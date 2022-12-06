package com.redcode.drones.entities;


import com.redcode.drones.enums.Model;
import com.redcode.drones.enums.State;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "DRONE")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer droneId;
    @Column(length = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private Model model;
    private Double weightLimit;
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
    @OneToMany
    private Set<Medication> medications;
}
