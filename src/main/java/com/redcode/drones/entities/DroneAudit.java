package com.redcode.drones.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DRONE_AUDIT")
@Data
public class DroneAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer auditId;

    private String Action;

    private Date actionDate;

    private String serialNumber;

}
