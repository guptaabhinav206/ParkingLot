package gondor.parking;

import java.util.Map;
import java.util.Scanner;

import gondor.parking.constants.VehicleType;
import gondor.parking.domain.ParkingFloor;
import gondor.parking.domain.Slot;
import gondor.parking.domain.Vehicle;
import gondor.parking.exceptions.ParkingNotAvailableException;
import gondor.parking.exceptions.VehicleNotFoundException;
import gondor.parking.factory.ParkingLotFactory;
import gondor.parking.factory.VehicleFactory;
import gondor.parking.service.ParkingLot;

public class Main {

    private static final int NUM_SLOTS = 10;

    public static void main(String[] args) {

        String type;
        String vehicleNum;
        boolean isElderly;
        int choice = 0;
        int slotNum = 0;
        VehicleType vehicleType = VehicleType.CAR;
        Scanner sc = new Scanner(System.in);
        System.out.println("--------- Enter number of floors in parking lot --------");
        int n = sc.nextInt();
        ParkingLot parkingLot = ParkingLotFactory.getParkingLot();
        VehicleFactory vehicleFactory = new VehicleFactory();
        parkingLot.createParkingLot(NUM_SLOTS, n);

        System.out.println("Press 1 to Park");
        System.out.println("Press 2 to Unpark");
        System.out.println("Press 3 to View status of all parking floors");
        System.out.println("Press 4 to View status of a given parking floor");
        System.out.println("Press 5 to Exit the menu");
        while(choice!=5){
            System.out.println("------- Enter your choice -------");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("-------- Enter vehicle details ------- ");
                    System.out.println("Enter vehicle type");
                    type = sc.next();
                    System.out.println("Enter vehicle number");
                    vehicleNum = sc.next();
                    System.out.println("Is the driver an elder person - enter T/F");
                    isElderly = sc.next().equals("T");
                    if (!checkInputValidity(type)) {
                        System.out.println("Wrong input vehicle type, Please try again");
                        break;
                    }
                    if(type.equals("CAR")){
                        vehicleType = VehicleType.CAR;
                    }
                    if(type.equals("BIKE")){
                        vehicleType = VehicleType.BIKE;
                    }
                    Vehicle vehicle = vehicleFactory.createVehicle(vehicleType, vehicleNum, isElderly);
                    try {
                        slotNum = parkingLot.park(vehicle);
                        System.out.println("Vehicle +"+vehicle.getVehicleType()+ " with vehicleNo "+vehicle.getVehicleNo()+ " parked at slotNumber "+slotNum);
                    } catch (ParkingNotAvailableException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter vehicle number");
                    vehicleNum = sc.nextLine();
                    try {
                        slotNum = parkingLot.leave(vehicleNum);
                        System.out.println("Vehicle with vehicleNo "+vehicleNum+ " removed at slotNumber "+slotNum);
                    } catch (VehicleNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    Map<Integer, ParkingFloor> parkinglotStatusForAllFloors = parkingLot.getParkinglotStatusForAllFloors();
                    displayAllParkingFloor(parkinglotStatusForAllFloors);
                    break;
                case 4:
                    System.out.println("Enter floor number to view parking floor status");
                    int floorNum = sc.nextInt();
                    ParkingFloor parkingFloor = parkingLot.getParkinglotStatusByFloor(floorNum);
                    displayParkingFloor(parkingFloor);
                    break;
                case 5:
                    System.out.println("Exiting from parking lot menu");
                    break;
            }
        }

    }

    private static void displayAllParkingFloor(Map<Integer, ParkingFloor> parkinglotStatusForAllFloors) {
        for(Map.Entry<Integer, ParkingFloor> entry : parkinglotStatusForAllFloors.entrySet()) {
            ParkingFloor parkingFloor = entry.getValue();
            Slot[][] slots = parkingFloor.getAllSlots();
            System.out.println("Parking Floor "+ entry.getKey() + " status :");
            displaySlots(slots);
        }
    }

    private static void displayParkingFloor(ParkingFloor parkingFloor) {
        Slot[][] slots = parkingFloor.getAllSlots();
        displaySlots(slots);
    }


    /***
     * Displays slots on each floor with status - O for occupied and F for free
     * @param slots
     */
    private static void displaySlots(Slot[][] slots) {
        for (Slot[] slot : slots) {
            for (int j = 0; j < slots[0].length; j++) {
                if (slot[j].getVehicle() != null) {
                    System.out.print("O ");
                } else {
                    System.out.print("F ");
                }
            }
            System.out.println();
        }
    }

    private static boolean checkInputValidity(String type) {
        return type.equals("CAR") || type.equals("BIKE");
    }
}
