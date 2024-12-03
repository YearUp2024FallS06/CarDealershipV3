package com.pluralsight;

import java.io.*;
import java.util.Spliterator;
import java.util.regex.Pattern;

public class DealershipFileManager {

    static private String getStringFromBufferedReader(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null){
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    public static Dealership getFromCSV(String filename){

        Dealership dealership = null;

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String allTheData = getStringFromBufferedReader(bufferedReader);
            bufferedReader.close();

            dealership = new Dealership(allTheData);


        }catch(Exception e){
            e.printStackTrace();
        }

        return dealership;
    }

    public static void saveToCSV(Dealership dealership, String filename){

        try {

            //Creating a file writer and assigning the file writer to the buffered writer.
            FileWriter fw = new FileWriter(filename);
            try ( BufferedWriter bw = new BufferedWriter(fw);)
            {
                bw.write(dealership.encode());
            }

        } catch (IOException e){
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }

    }

}
