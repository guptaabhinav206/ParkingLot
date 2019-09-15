package gondor.parking.domain;

import gondor.parking.constants.VehicleType;

public class Car extends Vehicle {

    public Car(VehicleType type, String vehicleNo, boolean isElderly, boolean isRoyal) {
        super(type, vehicleNo, isElderly, isRoyal);
    }

}
