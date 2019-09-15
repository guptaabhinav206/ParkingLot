package gondor.parking.service;

import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import gondor.parking.constants.VehicleType;
import gondor.parking.domain.Car;
import gondor.parking.domain.ParkingFloor;
import gondor.parking.domain.Slot;
import gondor.parking.domain.Vehicle;
import gondor.parking.exceptions.ParkingNotAvailableException;
import gondor.parking.exceptions.VehicleNotFoundException;

public class ParkingLotImplTest {

    private static final int NUM_FLOORS = 2;
    private static final int NUM_SLOTS = 20;
    private ParkingLotImpl parkingLot;

    ParkingLotImplTest() {
    }

    @Test
    public void shouldCreateParkingLot() {
        parkingLot = new ParkingLotImpl();
        parkingLot.createParkingLot(NUM_SLOTS, NUM_FLOORS);
        Assertions.assertEquals(parkingLot.getParkinglotStatusForAllFloors().size(), NUM_FLOORS);
    }

    @Test
    public void shouldParkVehicleWhenSpaceAvailable() throws ParkingNotAvailableException {
        parkingLot = new ParkingLotImpl();
        parkingLot.createParkingLot(NUM_SLOTS, NUM_FLOORS);
        String a = parkingLot.park(createVehicle());
        Assertions.assertNotEquals(a, -1);
    }

    @Test
    public void shouldNotParkVehicleWhenSpaceNotAvailable() {
        parkingLot = new ParkingLotImpl();
        parkingLot.createParkingLot(NUM_SLOTS, NUM_FLOORS);
        ParkingFloor[] parkingFloors = parkingLot.getParkingFloors();
        for(ParkingFloor parkingFloor : parkingFloors) {
            Queue<Slot> availableSlotsList = parkingFloor.getAvailableSlotsList();
            availableSlotsList.clear();

        }
        try{
            parkingLot.park(createVehicle());
            Assertions.fail("Cannot Park !! No free slots", new ParkingNotAvailableException("Cannot Park !! No free slots"));
        } catch (Exception e) {
            Assertions.assertEquals( "Cannot Park !! No free slots", e.getMessage());
        }

    }

    @Test
    public void shouldUnparkVehicleIfParked() throws VehicleNotFoundException, ParkingNotAvailableException {
        parkingLot = new ParkingLotImpl();
        parkingLot.createParkingLot(NUM_SLOTS, NUM_FLOORS);
        Vehicle vehicle = createVehicle();
        String park = parkingLot.park(vehicle);
        String leave = parkingLot.leave(vehicle.getVehicleNo());
        Assertions.assertEquals(park, leave);
    }

    @Test
    public void shouldThrowExceptionWhenVehicleNotFound() throws ParkingNotAvailableException {
        parkingLot = new ParkingLotImpl();
        parkingLot.createParkingLot(NUM_SLOTS, NUM_FLOORS);
        Vehicle vehicle1 = createVehicle();
        Vehicle vehicle2 = getDummyVehicle();
        parkingLot.park(vehicle1);

        try{
            parkingLot.leave(vehicle2.getVehicleNo());
            Assertions.fail("Cannot unpark !! Vehicle Not Found", new VehicleNotFoundException("Cannot unpark !! Vehicle Not Found"));
        } catch (Exception e) {
            Assertions.assertEquals( "Cannot unpark !! Vehicle Not Found", e.getMessage());
        }

    }

    public Vehicle createVehicle() {
        return new Car(VehicleType.CAR, "101", false, false);
    }

    public Vehicle getDummyVehicle() {
        return new Car(VehicleType.CAR, "102", false, false);
    }
}