package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class Program {


    public static void main(String[] args) {

        String username = args[0];
        String password = args[1];
        String sqlServerUrl = args[2];

        try(BasicDataSource dataSource = getConfiguredDataSource(username, password, sqlServerUrl);){

            DataManager dm = new DataManager(dataSource);

            UserInterface ui = new UserInterface(dm);
            ui.display();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static BasicDataSource getConfiguredDataSource(String username, String password, String sqlServerUrl){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(sqlServerUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;

    }

}