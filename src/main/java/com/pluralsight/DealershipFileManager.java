package com.pluralsight;

import java.io.*;

public class DealershipFileManager {


    public static Dealership getFromCSV(String filename){

        Dealership dealership = null;

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String line;

            String[] firstLineData = bufferedReader.readLine().split("\\|");
            String name = firstLineData[0];
            String address = firstLineData[1];
            String phone = firstLineData[2];
            dealership = new Dealership(name, address, phone);

            while((line = bufferedReader.readLine()) != null){
                String[] newLine = line.split("\\|");
                if(newLine.length == 8){
                    int vinNumber = Integer.parseInt(newLine[0]);
                    int makeYear = Integer.parseInt(newLine[1]);
                    String make = newLine[2];
                    String model = newLine[3];
                    String vehicleType = newLine[4];
                    String color = newLine[5];
                    int odometer = Integer.parseInt(newLine[6]);
                    double price = Double.parseDouble(newLine[7]);
                    Vehicle v = new Vehicle(vinNumber, makeYear, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicleToInventory(v);
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return dealership;
    }

    public static void saveToCSV(Dealership dealership, String filename){
        try {
            //Creating a file writer and assigning the file writer to the buffered writer.
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(getEncodedDealershipHeader(dealership));

            // Loop through transactions and write each one to the file
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bw.write(getEncodedVehicle(vehicle));
            }
            bw.close(); // Close the BufferedWriter

        } catch (IOException e){
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }
    }

    private static String getEncodedDealershipHeader(Dealership dealership){
       return dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n";
    }

    private static String getEncodedVehicle(Vehicle vehicle){
        return new StringBuilder()
                .append(vehicle.getVin()).append("|")
                .append(vehicle.getYear()).append("|")
                .append(vehicle.getMake()).append("|")
                .append(vehicle.getModel()).append("|")
                .append(vehicle.getVehicleType()).append("|")
                .append(vehicle.getColor()).append("|")
                .append(vehicle.getOdometer()).append("|")
                .append(vehicle.getPrice()).append("\n").toString();
    }


}
