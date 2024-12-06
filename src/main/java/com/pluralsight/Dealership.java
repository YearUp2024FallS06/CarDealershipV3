package com.pluralsight;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Dealership implements ITextEncodable {

    private int dealershipId;
    private String name;
    private String address;
    private String phone;

    private DataManager dataManager;

    public Dealership(DataManager dataManager, int dealershipId, String name, String address, String phone) {
        this.dealershipId = dealershipId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dataManager = dataManager;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getInventory() {
        return dataManager.getVehiclesAll(dealershipId);
    }

    public void addVehicleToInventory(Vehicle vehicleToAdd){
        dataManager.addVehicleToDealership(this.dealershipId, vehicleToAdd);
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max){
        return dataManager.getVehiclesByPrice(this.dealershipId, min, max);
    }

    public Vehicle getVehicleByVIN(int vin){
      return dataManager.getVehicleByVIN(vin);
    }



    public List<Vehicle> getAllVehicles() {
        return this.getInventory();
    }

    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getName()).append("|")
                .append(this.getAddress()).append("|")
                .append(this.getPhone()).append("\n");

        for(Vehicle v :this.getAllVehicles()){
            sb.append(v.encode()).append("\n");
        }

        return sb.toString();

    }
}
