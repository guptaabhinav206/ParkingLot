package gondor.parking.domain;

import gondor.parking.constants.VehicleType;

public class Bike extends Vehicle {

    public Bike(VehicleType type, String vehicleNo, boolean isElderly, boolean isRoyal) {
        super(type, vehicleNo, isElderly, isRoyal);
    }
}
