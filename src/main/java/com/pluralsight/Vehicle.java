package com.pluralsight;

import java.util.regex.Pattern;

public class Vehicle implements ITextEncodable {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public Vehicle(String encodedData){
        String[] data = encodedData.split(Pattern.quote("|"));
        int vin = Integer.parseInt(data[0]);
        int year = Integer.parseInt(data[1]);
        String make = data[2];
        String model = data[3];
        String vehicleType = data[4];
        String color = data[5];
        int odometer = Integer.parseInt(data[6]);
        double price = Double.parseDouble(data[7]);
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }
    // Getters for all fields
    public int getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        String colorString;
        if (color.equalsIgnoreCase("Red")){
            colorString = ColorCodes.RED + color + ColorCodes.RESET;
        }
        else if (color.equalsIgnoreCase("White")){
            colorString = ColorCodes.WHITE + color + ColorCodes.RESET;
        }
        else if (color.equalsIgnoreCase("Blue")){
            colorString = ColorCodes.BLUE + color + ColorCodes.RESET;
        }

        else{
            colorString = color;
        }
        return (this.getYear() + " " + this.getMake() + " " + this.getModel() + " [" + colorString + "]" + "VIN: " + this.getVin());
    }

    @Override
    public String encode() {
        return new StringBuilder()
                .append(this.getVin()).append("|")
                .append(this.getYear()).append("|")
                .append(this.getMake()).append("|")
                .append(this.getModel()).append("|")
                .append(this.getVehicleType()).append("|")
                .append(this.getColor()).append("|")
                .append(this.getOdometer()).append("|")
                .append(this.getPrice()).toString();
    }
}