package gondor.parking.domain;

import gondor.parking.constants.VehicleType;

public class Bike extends Vehicle {

    public Bike(VehicleType type, String vehicleNo, boolean isElderly) {
        super(type, vehicleNo, isElderly);
    }
}
