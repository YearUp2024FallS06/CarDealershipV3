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
                                        newLine[8], //type
                                        newLine[9], //color
                                        Integer.parseInt(newLine[10]), //miles
                                        Double.parseDouble(newLine[11]) //price
                                ),
                                Double.parseDouble(newLine[12]),
                                Double.parseDouble(newLine[13]),
                                Double.parseDouble(newLine[14]),
                                Boolean.parseBoolean(newLine[16])
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
                                        newLine[8], //type
                                        newLine[9], //color
                                        Integer.parseInt(newLine[10]), //miles
                                        Double.parseDouble(newLine[11]) //price
                                ),
                                Double.parseDouble(newLine[12]), //expended end value
                                Double.parseDouble(newLine[13])  //lease fee
                        );
                        results.add(contract);
                    }
                    else{
                        //invalid contract type, how to handle?
                    }

                }
            }

            bufferedReader.close();

            return results;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void saveToCSV(ArrayList<Contract> contracts, String filename){

    }




}
