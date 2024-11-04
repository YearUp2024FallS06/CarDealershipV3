package com.pluralsight.contracts;

import com.pluralsight.Dealership;
import com.pluralsight.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class ContractFileManager {

    public static ArrayList<Contract> getFromCSV(String filename){

        ArrayList<Contract> results = new ArrayList<>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String line;

            while((line = bufferedReader.readLine()) != null){
                String[] newLine = line.split("\\|");
                if(newLine.length >= 16){
                    if(newLine[0].equalsIgnoreCase("SALE")){
                        
                        SalesContract contract = new SalesContract(
                                newLine[1], // date
                                newLine[2], // name
                                newLine[3], //email
                                new Vehicle(
                                        Integer.parseInt(newLine[4]), //vin
                                        Integer.parseInt(newLine[5]), //year
                                        newLine[6], //make
                                        newLine[7], //model
                                        newLine[8], //model
                                        newLine[9], //color
                                        Integer.parseInt(newLine[10]), //miles
                                        Double.parseDouble(newLine[11]) //price
                                ),
                                Boolean.parseBoolean(newLine[12])
                        );
                        results.add(contract);
                    }
                    else if (newLine[0].equalsIgnoreCase("LEASE")){
                        LeaseContract contract = new LeaseContract(
                                newLine[1], // date
                                newLine[2], // name
                                newLine[3], //email
                                new Vehicle(
                                        Integer.parseInt(newLine[4]), //vin
                                        Integer.parseInt(newLine[5]), //year
                                        newLine[6], //make
                                        newLine[7], //model
                                        newLine[8], //color
                                        Integer.parseInt(newLine[9]), //miles
                                        Double.parseDouble(newLine[10]) //price
                                )
                        );
                    }
                    else{
                        //invalid contract type, how to handle?
                    }
                    int vinNumber = ;
                    int makeYear = ;
                    String make = ;
                    String model = newLine[3];
                    String vehicleType = newLine[4];
                    String color = newLine[5];
                    int odometer = ;
                    double price = ;
                    Vehicle v = new Vehicle(vinNumber, makeYear, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicleToInventory(v);
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void saveToCSV(ArrayList<Contract> contracts, String filename){

    }




}
