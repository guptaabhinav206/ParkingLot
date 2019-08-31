package gondor.parking.domain;

import gondor.parking.constants.VehicleType;

public abstract class Vehicle {

    String vehicleNo;
    private VehicleType vehicleType;
    boolean isElderly;

    public Vehicle(VehicleType type, String vehicleNo, boolean isElderly) {

        this.vehicleType = type;
        this.vehicleNo = vehicleNo;
        this.isElderly = isElderly;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType=" + vehicleType +
                ", isElderly=" + isElderly +
                '}';
    }

    public boolean isNormal() {
        return !isElderly;
    }
}
