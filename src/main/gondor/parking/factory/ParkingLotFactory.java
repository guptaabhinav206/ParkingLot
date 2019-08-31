package gondor.parking.factory;

import gondor.parking.service.ParkingLot;
import gondor.parking.service.ParkingLotImpl;

public class ParkingLotFactory {

    public static ParkingLot getParkingLot() {
        return new ParkingLotImpl();
    }

}
