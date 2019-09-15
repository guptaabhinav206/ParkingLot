package gondor.parking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gondor.parking.domain.ParkingFloor;
import gondor.parking.exceptions.VehicleNotFoundException;
import gondor.parking.domain.Slot;
import gondor.parking.domain.Vehicle;
import gondor.parking.exceptions.ParkingNotAvailableException;

public class ParkingLotImpl implements ParkingLot {

    private Map<Slot, Vehicle> slotVehicleMap;
    private Map<String, Slot> vehicleNoSlotMap;
    private Map<String, List<Slot>> royalVehicleMap = new HashMap<>();
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
    public String park(Vehicle vehicle) throws ParkingNotAvailableException {
        Slot slot = null;

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
                if(vehicle.isRoyal() && parkingFloor.isSlotAvailableForRoyalFamilty()) {
                    List<Slot> royalSlot = new ArrayList<>();
                    for(int i=0; i<3; i++) {
                        slot = parkingFloor.getFreeSlot();
                        slot.park(vehicle);
                        royalSlot.add(slot);
                        slotVehicleMap.put(slot, vehicle);
                        if(i==1)
                            vehicleNoSlotMap.put(vehicle.getVehicleNo(), slot);
                    }
                    royalVehicleMap.put(vehicle.getVehicleNo(), royalSlot);
                } else {
                    slot = parkingFloor.getFreeSlot();
                    slot.park(vehicle);
                    slotVehicleMap.put(slot, vehicle);
                    vehicleNoSlotMap.put(vehicle.getVehicleNo(), slot);
                }
                return slot.getSlotNumber();
            }
        }
        throw new ParkingNotAvailableException("Cannot Park !! No free slots");
    }

    @Override
    public String leave(String vehicleNo) throws VehicleNotFoundException {
        Slot slot;
        if (!vehicleNoSlotMap.containsKey(vehicleNo)) {
            throw new VehicleNotFoundException("Cannot unpark !! Vehicle Not Found");
        } else {

            if(royalVehicleMap.containsKey(vehicleNo)) {
                List<Slot> royalSlots = royalVehicleMap.get(vehicleNo);
                for(Slot slot1:royalSlots) {
                    slotVehicleMap.remove(slot1);
                    ParkingFloor parkingFloor = slot1.getParkingFloor();
                    parkingFloor.freeSlot(slot1);
                    slot1.removeVehicle();
                }
                Slot slot1 = vehicleNoSlotMap.get(vehicleNo);
                vehicleNoSlotMap.remove(vehicleNo);
                return slot1.getSlotNumber();

            } else {
                slot= vehicleNoSlotMap.get(vehicleNo);
                vehicleNoSlotMap.remove(vehicleNo);
                slotVehicleMap.remove(slot);
                ParkingFloor parkingFloor = slot.getParkingFloor();
                parkingFloor.freeSlot(slot);
                slot.removeVehicle();
                return slot.getSlotNumber();
            }

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
