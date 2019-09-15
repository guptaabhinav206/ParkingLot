package gondor.parking.domain;

import gondor.parking.constants.VehicleType;

public abstract class Vehicle {

    String vehicleNo;
    private VehicleType vehicleType;
    boolean isElderly;
    boolean isRoyal;

    public Vehicle(VehicleType type, String vehicleNo, boolean isElderly, boolean isRoyal) {

        this.vehicleType = type;
        this.vehicleNo = vehicleNo;
        this.isElderly = isElderly;
        this.isRoyal = isRoyal;
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
                ", isRoyal=" + isRoyal +
                '}';
    }

    public boolean isNormal() {
        return !isElderly;
    }

    public boolean isRoyal() {
        return isRoyal;
    }
}
