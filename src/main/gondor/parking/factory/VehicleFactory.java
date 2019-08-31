package gondor.parking.factory;

import gondor.parking.constants.VehicleType;
import gondor.parking.domain.Car;
import gondor.parking.domain.Vehicle;

public class VehicleFactory {

    private Vehicle vehicle = null;

    public Vehicle createVehicle(VehicleType type, String vehicleNo, boolean isElderly) {
        if(type.equals(VehicleType.CAR)) {
            vehicle = new Car(type, vehicleNo, isElderly);
        }
        if(type.equals(VehicleType.BIKE)) {
            vehicle = new Car(type, vehicleNo, isElderly);
        }
        return vehicle;
    }
}
