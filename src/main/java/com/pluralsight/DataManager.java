package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private final BasicDataSource dataSource;

    public DataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getVehiclesByPrice(int dealershipId, double min, double max){
        return null;
    }

    public List<Vehicle> getVehiclesByVIN(int dealershipId, int VIN){
        return null;
    }

    public List<Vehicle> getVehiclesAll(int dealershipId){
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement query = connection.prepareStatement(
                    """
                            SELECT\s
                            vehicles.vin, year, make, model, vehicleType, color, odometer, price
                            FROM vehicles\s
                            join inventory on vehicles.vin = inventory.vin
                            WHERE inventory.dealership_id = ?
                            """);)
            {
                query.setInt(1, dealershipId);
                try(ResultSet results = query.executeQuery())
                {
                    while(results.next()){
                        vehicles.add( new Vehicle(
                                results.getInt("vin"),
                                results.getInt("year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicleType"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price")
                        ));

                    }
                }
            }

            return actors;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


    public void addVehicle(int dealershipId, Vehicle vehicle){
        //to the work to insert into database.
    }

}
