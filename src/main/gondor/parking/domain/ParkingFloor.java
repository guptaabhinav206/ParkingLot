package gondor.parking.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ParkingFloor {

    private Slot[][] slots;
    private final int SLOT_PER_ROW = 5;
    private int floorNumber;
    private Queue<Slot> availableSlotsList;
    private Map<Integer, Queue<Slot>> availableSlotsMap = new HashMap<>();
    private static Queue<Slot> availableSlotsElderly = new LinkedList<>();

    public ParkingFloor(int floorNumber, int numSlots) {

        this.floorNumber = floorNumber;
        this.availableSlotsList = new LinkedList<>();
        this.slots = new Slot[numSlots/SLOT_PER_ROW][SLOT_PER_ROW];

        for(int i=0;i<numSlots/SLOT_PER_ROW;i++) {
            for(int j=0;j<SLOT_PER_ROW;j++) {
                slots[i][j] = new Slot(i+1, j+1, this);
                if(floorNumber == 1 && i==(numSlots/SLOT_PER_ROW)-1){
                    availableSlotsElderly.add(slots[i][j]);
                } else
                    availableSlotsList.add(slots[i][j]);
            }
        }
        availableSlotsMap.put(floorNumber, availableSlotsList);
    }

    public Slot[][] getAllSlots() {
        return slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    private boolean isSlotAvailable() {
        return availableSlotsList.size() > 0;
    }

    public Slot getFreeSlot() {
        return availableSlotsList.poll();
    }

    public Slot getElderlySlot() {
        return availableSlotsElderly.poll();
    }

    public boolean canPark() {
        return isSlotAvailable();
    }

    public boolean canParkElderly() {
        return isSlotAvailableforElderly();
    }

    public void freeSlot(Slot slot) {
        availableSlotsList.add(slot);
    }

    public boolean isSlotAvailableforElderly() {
        return availableSlotsElderly.size() > 0;
    }

    public Queue<Slot> getAvailableSlotsList() {
        return availableSlotsList;
    }

    public Queue<Slot> getAvailableSlotsElderly() {
        return availableSlotsElderly;
    }

    public boolean isSlotAvailableForRoyalFamilty() {
        return availableSlotsList.size() > 2;
    }
}
