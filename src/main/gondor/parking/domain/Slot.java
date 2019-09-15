package gondor.parking.domain;

import gondor.parking.constants.ParkingSlotStatus;

public class Slot {

    private Vehicle vehicle;
    private int laneNumber;
    private int slotNumber;
    private ParkingFloor parkingFloor;
    private ParkingSlotStatus slotStatus;


    Slot(int laneNumber, int slotNumber, ParkingFloor parkingFloor) {
        this.laneNumber = laneNumber;
        this.slotNumber = slotNumber;
        this.parkingFloor = parkingFloor;
    }

    public ParkingSlotStatus getParkingSlotStatus() {
        return slotStatus;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public String getSlotNumber() {
        return "lane number "+laneNumber+" slot number "+slotNumber;
    }

    public void removeVehicle() {
        slotStatus = ParkingSlotStatus.FREE;
        vehicle = null;
    }

    public void park(Vehicle vehicle) {
        slotStatus = ParkingSlotStatus.OCCUPIED;
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "vehicle=" + vehicle +
                ", laneNumber=" + laneNumber +
                ", slotNumber=" + slotNumber +
                ", parkingFloor=" + parkingFloor +
                ", slotStatus=" + slotStatus +
                '}';
    }
}
