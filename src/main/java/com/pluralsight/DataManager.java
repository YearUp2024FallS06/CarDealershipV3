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

    public Dealership getDealership(int dealershipId){

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement query = connection.prepareStatement(
                    """
                    select
                    dealership_id, name, address, phone
                    from dealerships
                    where dealership_id = ?
                    """);)
            {
                query.setInt(1, dealershipId);
                try(ResultSet results = query.executeQuery())
                {
                    Dealership result = null;
                    while(results.next()){

                        result = new Dealership(
                                this,
                                results.getInt("dealership_id"),
                                results.getString("name"),
                                results.getString("address"),
                                results.getString("phone")
                        );

                    }
                    return result;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addVehicleToDealership(int dealershipId, Vehicle vehicle){


        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement query = connection.prepareStatement(
                    """
                    INSERT INTO vehicles
                    (`vin`,`year`,`make`,`model`,`vehicleType`,`color`,`odometer`,`price`)
                    VALUES
                    ( ?, -- <{vin: }>,
                      ?, -- <{year: }>,
                      ?, -- <{make: }>,
                      ?, -- <{model: }>,
                      ?, -- <{vehicleType: }>,
                      ?, -- <{color: }>,
                      ?, -- <{odometer: }>,
                      ? -- <{price: }>
                      );
                     """);)
            {
                query.setInt(1, vehicle.getVin());
                query.setInt(2, vehicle.getYear());
                query.setString(3, vehicle.getMake());
                query.setString(4, vehicle.getModel());
                query.setString(5, vehicle.getVehicleType());
                query.setString(6, vehicle.getColor());
                query.setInt(7, vehicle.getOdometer());
                query.setDouble(8, vehicle.getPrice());

                int rows = query.executeUpdate();

            }

            try(PreparedStatement query = connection.prepareStatement(
                    """
                            INSERT INTO `inventory` (`dealership_id`, `vin`)
                             VALUES (?, ?);
                     """);)
            {
                query.setInt(1,dealershipId);
                query.setInt(2, vehicle.getVin());

                int rows = query.executeUpdate();

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Vehicle> getVehiclesByPrice(int dealershipId, double min, double max){
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement query = connection.prepareStatement(
                    """
                            SELECT\s
                            vehicles.vin, year, make, model, vehicleType, color, odometer, price
                            FROM vehicles\s
                            join inventory on vehicles.vin = inventory.vin
                            WHERE inventory.dealership_id = ?
                            AND vehicles.price >= ? AND vehicles.price <= ?;
                            """);)
            {
                query.setInt(1, dealershipId);
                query.setDouble(2, min);
                query.setDouble(3, max);

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

            return vehicles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Vehicle getVehicleByVIN(int VIN){
      Vehicle vehicle = null;

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement query = connection.prepareStatement(
                    """
                            SELECT\s
                            vehicles.vin, year, make, model, vehicleType, color, odometer, price
                            FROM vehicles\s
                            join inventory on vehicles.vin = inventory.vin
                            WHERE vehicles.vin = ?
                            """);)
            {
                query.setInt(1, VIN);

                try(ResultSet results = query.executeQuery())
                {
                    while(results.next()){
                        vehicle = new Vehicle(
                                results.getInt("vin"),
                                results.getInt("year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicleType"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price")
                        );
                    }
                }
            }

            return vehicle;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

            return vehicles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void addVehicle(int dealershipId, Vehicle vehicle){
        //to the work to insert into database.
    }

}
