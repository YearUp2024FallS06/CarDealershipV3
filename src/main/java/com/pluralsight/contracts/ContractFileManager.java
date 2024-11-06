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
                        results.add(SalesContract.buildFromEncodedData(line));
                    }
                    else if (newLine[0].equalsIgnoreCase("LEASE")){

                        results.add(LeaseContract.buildFromEncodedData(line));
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

        try {
            //Creating a file writer and assigning the file writer to the buffered writer.
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Contract c : contracts){
                bw.write(c.encode() + "\n");
            }

            bw.close(); // Close the BufferedWriter

        } catch (IOException e){
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }
    }

    public static void appendToCSV(Contract contract, String filename){
        try {
            //Creating a file writer and assigning the file writer to the buffered writer.
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contract.encode() + "\n");
            bw.close(); // Close the BufferedWriter
        } catch (IOException e){
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }
    }


}
