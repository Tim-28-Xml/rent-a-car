package com.tim26.demo.service;

import com.tim26.demo.dto.CreateAdDto;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class AdService {

    public boolean createAd(CreateAdDto createAdDto) throws SQLException {
        Connection connection = null;
        String dbUrl = "jdbc:postgresql://localhost:5432/RentACar";
        String dbUsername = "postgres";
        String dbPassword = "root";

        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            preparedStatement = connection.prepareStatement("INSERT INTO car (brand, car_class, model, fuel, transmission) values (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, createAdDto.getBrand());
            preparedStatement.setString(2, createAdDto.getCarClass());
            preparedStatement.setString(3, createAdDto.getModel());
            preparedStatement.setString(4, createAdDto.getFuel());
            preparedStatement.setString(5, createAdDto.getTransmission());
            preparedStatement.executeUpdate();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    return true;
                }
            }
            catch (Exception e) {
                return false;
            }
            try {
                if (connection != null) {
                    connection.close();
                    return true;
                }
            }
            catch (Exception e) {
                return  false;
            }
        }
        return false;
    }
}
