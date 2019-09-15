package gondor.parking.factory;

import gondor.parking.constants.VehicleType;
import gondor.parking.domain.Bike;
import gondor.parking.domain.Car;
import gondor.parking.domain.Vehicle;

public class VehicleFactory {

    private Vehicle vehicle = null;

    public Vehicle createVehicle(VehicleType type, String vehicleNo, boolean isElderly, boolean isRoyal) {
        if(type.equals(VehicleType.CAR)) {
            vehicle = new Car(type, vehicleNo, isElderly, isRoyal);
        }
        if(type.equals(VehicleType.BIKE)) {
            vehicle = new Bike(type, vehicleNo, isElderly, isRoyal);
        }
        return vehicle;
    }
}
