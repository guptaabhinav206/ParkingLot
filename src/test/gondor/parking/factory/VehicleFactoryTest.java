package gondor.parking.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import gondor.parking.constants.VehicleType;
import gondor.parking.domain.Car;
import gondor.parking.domain.Vehicle;

public class VehicleFactoryTest {

    private Vehicle vehicle = null;
    private final String VEHICLE_NUM_CAR = "101";
    private final String VEHICLE_NUM_BIKE = "102";
    private final boolean IS_ELDERLY = false;
    private VehicleFactory vehicleFactory;

    VehicleFactoryTest() {

    }

    @Test
    public void shouldCreateVehicleWithCarType() {
        vehicleFactory = new VehicleFactory();
        Assertions.assertEquals(vehicleFactory.createVehicle(VehicleType.CAR, VEHICLE_NUM_CAR, IS_ELDERLY).toString(), createCarVehicle().toString());
    }

    @Test
    public void shouldCreateVehicleWithBikeType() {
        vehicleFactory = new VehicleFactory();
        Assertions.assertEquals(vehicleFactory.createVehicle(VehicleType.BIKE, VEHICLE_NUM_BIKE, IS_ELDERLY).toString(), createBikeVehicle().toString());
    }

    public Vehicle createCarVehicle() {
        return new Car(VehicleType.CAR, "101", false);
    }

    public Vehicle createBikeVehicle() {
        return new Car(VehicleType.BIKE, "102", false);
    }
}
