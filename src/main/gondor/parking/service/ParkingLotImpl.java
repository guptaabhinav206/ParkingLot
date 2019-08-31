package gondor.parking.service;

import java.util.HashMap;
import java.util.Map;

import gondor.parking.domain.ParkingFloor;
import gondor.parking.exceptions.VehicleNotFoundException;
import gondor.parking.domain.Slot;
import gondor.parking.domain.Vehicle;
import gondor.parking.exceptions.ParkingNotAvailableException;

public class ParkingLotImpl implements ParkingLot {

    private Map<Slot, Vehicle> slotVehicleMap;
    private Map<String, Slot> vehicleNoSlotMap;
    private ParkingFloor[] parkingFloors;
    private Map<Integer, ParkingFloor> parkingFloorMap = new HashMap<>();
    private int numFloors;

    public ParkingLotImpl() {
        slotVehicleMap = new HashMap<>();
        vehicleNoSlotMap = new HashMap<>();
    }

    @Override
    public void createParkingLot(int numOfSlots, int numFloors) {
        this.numFloors = numFloors;
        this.parkingFloors = new ParkingFloor[numFloors];
        for(int i=0;i<numFloors;i++) {
            parkingFloors[i] = new ParkingFloor(i+1, numOfSlots);
        }
    }

    @Override
    public int park(Vehicle vehicle) throws ParkingNotAvailableException {
        Slot slot;

        if(!vehicle.isNormal()) {
            if(parkingFloors[0].canParkElderly()) {
                slot = parkingFloors[0].getElderlySlot();
                slot.park(vehicle);
                slotVehicleMap.put(slot, vehicle);
                vehicleNoSlotMap.put(vehicle.getVehicleNo(), slot);
                return slot.getSlotNumber();
            }
        }

        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.canPark()) {
                slot = parkingFloor.getFreeSlot();
                slot.park(vehicle);
                slotVehicleMap.put(slot, vehicle);
                vehicleNoSlotMap.put(vehicle.getVehicleNo(), slot);
                return slot.getSlotNumber();
            }
        }
        throw new ParkingNotAvailableException("Cannot Park !! No free slots");
    }

    @Override
    public int leave(String vehicleNo) throws VehicleNotFoundException {
        if (!vehicleNoSlotMap.containsKey(vehicleNo)) {
            throw new VehicleNotFoundException("Cannot unpark !! Vehicle Not Found");
        } else {
            Slot slot = vehicleNoSlotMap.get(vehicleNo);
            vehicleNoSlotMap.remove(vehicleNo);
            slotVehicleMap.remove(slot);
            ParkingFloor parkingFloor = slot.getParkingFloor();
            parkingFloor.freeSlot(slot);
            slot.removeVehicle();
            return slot.getSlotNumber();
        }
    }

    @Override
    public Map<Integer, ParkingFloor> getParkinglotStatusForAllFloors() {
        for(ParkingFloor parkingFloor : parkingFloors) {
            int floorNum = parkingFloor.getFloorNumber();
            parkingFloorMap.put(floorNum, parkingFloor);
        }
        return parkingFloorMap;
    }

    @Override
    public ParkingFloor getParkinglotStatusByFloor(int floorNum) {
        return parkingFloorMap.get(floorNum);
    }

    @Override
    public Slot getSlotForCar(String vehicleNo) {
        return vehicleNoSlotMap.get(vehicleNo);
    }

    @Override
    public ParkingFloor[] getParkingFloors() {
        return parkingFloors;
    }

}
