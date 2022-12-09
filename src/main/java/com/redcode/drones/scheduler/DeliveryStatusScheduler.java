package com.redcode.drones.scheduler;

import com.redcode.drones.config.ImportDBConfig;
import com.redcode.drones.entities.Drone;
import com.redcode.drones.enums.State;
import com.redcode.drones.services.DroneAuditService;
import com.redcode.drones.services.DroneService;
import com.redcode.drones.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryStatusScheduler {
    private static final Logger log = LoggerFactory.getLogger(DeliveryStatusScheduler.class);
    final DroneService droneService;

    final DroneAuditService droneAuditService;

    @Scheduled(fixedRate = 10000)
    @Async
    public void refreshDronesStatus() {

        try {
            List<Drone> drones = droneService.getAllDronesThatNotIdle();
            for (Drone drone : drones) {
                switch (drone.getState()) {
                    case LOADING:
                        drone.setState(State.LOADED);
                        drone.setBatteryCapacity(60);
                        droneAuditService.saveDroneAction(drone.getSerialNumber(), Constant.CHANGE_DRONE_STATUS + drone.getState());
                        break;
                    case LOADED:
                        drone.setState(State.DELIVERING);
                        drone.setBatteryCapacity(40);
                        droneAuditService.saveDroneAction(drone.getSerialNumber(), Constant.CHANGE_DRONE_STATUS + drone.getState());
                        break;
                    case DELIVERING:
                        drone.setState(State.DELIVERED);
                        drone.setBatteryCapacity(20);
                        droneAuditService.saveDroneAction(drone.getSerialNumber(), Constant.CHANGE_DRONE_STATUS + drone.getState());
                        break;
                    case DELIVERED:
                        drone.setState(State.RETURNING);
                        drone.setBatteryCapacity(10);
                        droneAuditService.saveDroneAction(drone.getSerialNumber(), Constant.CHANGE_DRONE_STATUS + drone.getState());
                        break;
                    case RETURNING:
                        drone.setState(State.IDLE);
                        droneAuditService.saveDroneAction(drone.getSerialNumber(), Constant.CHANGE_DRONE_STATUS + drone.getState());
                        break;
                }
                droneService.save(drone);
            }
        } catch (Exception e) {
            log.error("Error while change drone status", e);
        }
    }


    @Scheduled(fixedRate = 40000)
    @Async
    public void rechargeBatteryForIdle() {

        try {
            List<Drone> drones = droneService.getAvailableDronesForBatteryCharge();
            for (Drone drone : drones) {
                drone.setBatteryCapacity(100);
                droneAuditService.saveDroneAction(drone.getSerialNumber(), Constant.CHARGE_BATTERY_LEVEL);
                droneService.save(drone);
            }
        } catch (Exception e) {
            log.error("Error while charge drone", e);
        }
    }
}
