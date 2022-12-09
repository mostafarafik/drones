package com.redcode.drones.config;

import com.redcode.drones.entities.Drone;
import com.redcode.drones.entities.Medication;
import com.redcode.drones.enums.Model;
import com.redcode.drones.enums.State;
import com.redcode.drones.services.DroneService;
import com.redcode.drones.services.MedicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ImportDBConfig {

    @Autowired
    DroneService droneService;

    @Autowired
    MedicationService medicationService;

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            addDrones();
            addMedications();
        };
    }

    private void addDrones() {
        List<Drone> drones = new ArrayList<>();
        Drone drone1 = new Drone();
        drone1.setState(State.IDLE);
        drone1.setBatteryCapacity(100);
        drone1.setModel(Model.Lightweight);
        drone1.setWeightLimit(100.00);
        drone1.setSerialNumber("Test1");
        drones.add(drone1);
        Drone drone2 = new Drone();
        drone2.setState(State.IDLE);
        drone2.setBatteryCapacity(100);
        drone2.setModel(Model.Middleweight);
        drone2.setWeightLimit(200.00);
        drone2.setSerialNumber("Test2");
        drones.add(drone2);
        Drone drone3 = new Drone();
        drone3.setState(State.IDLE);
        drone3.setBatteryCapacity(100);
        drone3.setModel(Model.Heavyweight);
        drone3.setWeightLimit(300.00);
        drone3.setSerialNumber("Test3");
        drones.add(drone3);
        Drone drone4 = new Drone();
        drone4.setState(State.IDLE);
        drone4.setBatteryCapacity(100);
        drone4.setModel(Model.Cruiserweight);
        drone4.setWeightLimit(500.00);
        drone4.setSerialNumber("Test4");
        drones.add(drone4);
        Drone drone5 = new Drone();
        drone5.setState(State.IDLE);
        drone5.setBatteryCapacity(100);
        drone5.setModel(Model.Lightweight);
        drone5.setWeightLimit(100.00);
        drone5.setSerialNumber("Test5");
        drones.add(drone5);
        Drone drone6 = new Drone();
        drone6.setState(State.IDLE);
        drone6.setBatteryCapacity(100);
        drone6.setModel(Model.Middleweight);
        drone6.setWeightLimit(200.00);
        drone6.setSerialNumber("Test6");
        drones.add(drone6);
        Drone drone7 = new Drone();
        drone7.setState(State.IDLE);
        drone7.setBatteryCapacity(100);
        drone7.setModel(Model.Heavyweight);
        drone7.setWeightLimit(300.00);
        drone7.setSerialNumber("Test7");
        drones.add(drone7);
        Drone drone8 = new Drone();
        drone8.setState(State.IDLE);
        drone8.setBatteryCapacity(100);
        drone8.setModel(Model.Cruiserweight);
        drone8.setWeightLimit(500.00);
        drone8.setSerialNumber("Test8");
        drones.add(drone8);
        Drone drone9 = new Drone();
        drone9.setState(State.IDLE);
        drone9.setBatteryCapacity(100);
        drone9.setModel(Model.Lightweight);
        drone9.setWeightLimit(100.00);
        drone9.setSerialNumber("Test9");
        drones.add(drone9);
        Drone drone10 = new Drone();
        drone10.setState(State.IDLE);
        drone10.setBatteryCapacity(100);
        drone10.setModel(Model.Middleweight);
        drone10.setWeightLimit(200.00);
        drone10.setSerialNumber("Test10");
        drones.add(drone10);

        for (Drone drone : drones)
            droneService.save(drone);
    }

    private void addMedications() {
        List<Medication> medications = new ArrayList<>();
        Medication medication1 = new Medication();
        medication1.setWeight(20.00);
        medication1.setCode("000_1");
        medication1.setName("Medication1");
        medications.add(medication1);
        Medication medication2 = new Medication();
        medication2.setWeight(30.00);
        medication2.setCode("000_2");
        medication2.setName("Medication2");
        medications.add(medication2);
        Medication medication3 = new Medication();
        medication3.setWeight(40.00);
        medication3.setCode("000_3");
        medication3.setName("Medication3");
        medications.add(medication3);
        Medication medication4 = new Medication();
        medication4.setWeight(50.00);
        medication4.setCode("000_4");
        medication4.setName("Medication4");
        medications.add(medication4);
        Medication medication5 = new Medication();
        medication5.setWeight(60.00);
        medication5.setCode("000_5");
        medication5.setName("Medication5");
        medications.add(medication5);
        Medication medication6 = new Medication();
        medication6.setWeight(70.00);
        medication6.setCode("000_6");
        medication6.setName("Medication6");
        medications.add(medication6);


        for (Medication medication : medications)
            medicationService.save(medication);
    }

}
