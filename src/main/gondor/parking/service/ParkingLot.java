package gondor.parking.service;

import java.util.Map;

import gondor.parking.domain.ParkingFloor;
import gondor.parking.domain.Slot;
import gondor.parking.domain.Vehicle;
import gondor.parking.exceptions.ParkingNotAvailableException;
import gondor.parking.exceptions.VehicleNotFoundException;

public interface ParkingLot {

    /**
     * Creates a parking lot with the given number of slots
     * @param NUM_SLOTS
     */
    void createParkingLot(int NUM_SLOTS, int numFloors);

    /**
     * Calculates the nearest free slot from entrance and returns the slot number. Gives Parking preference to elderly people to ground floor.
     * @param vehicle
     * @return  -1 when the parking slot is full
     */
    String park(Vehicle vehicle) throws ParkingNotAvailableException;

    /**
     * Frees up a slot and returns the slot number
     * @param vehicleNo
     * @return
     */
    String leave(String vehicleNo) throws VehicleNotFoundException;

    /**
     *
     * @return  a Map containing the Slot and corresponding Cars for all floors. For the entries where the value is null is considered
     * as free
     */
    Map<Integer, ParkingFloor> getParkinglotStatusForAllFloors();

    /**
     *
     * @return  a Map containing the Slot and corresponding Cars. For the entries where the value is null is considered
     * as free
     */
    ParkingFloor getParkinglotStatusByFloor(int floorNum);
    /**
     * Returns the Slot where the vehicle is parked
     * @param registrationNumber
     * @return
     */
    Slot getSlotForCar(String registrationNumber);


    ParkingFloor[] getParkingFloors();
}
