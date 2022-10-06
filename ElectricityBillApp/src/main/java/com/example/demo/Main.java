package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws SQLException {
		ElectricityBoard electricityBoard = new ElectricityBoard();
		List<ElectricityBill> billList = electricityBoard.generateBill("src/main/resources/ElectrictBill.txt");
		System.out.println("Bills parsed from the file....");
		electricityBoard.addBill(billList);
		{
			for (ElectricityBill bill : billList) {
				System.out.println(String.format("id: %s, name: %s, address: %s, units: %d,bill: %f",
						bill.getConsumerNumber(), bill.getConsumerName(), bill.getConsumeraddress(),
						bill.getUnitsConsumed(), bill.getBillAmount()));
			}
			Connection connection = new DBHandler().establishConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from ElectricityBill");
			System.out.println("bills recieved from Database ElectricityBill...");
			while (resultSet.next()) {
				String consumerNumber = resultSet.getString(1);
				String consumerName = resultSet.getString(2);
				String consumerAddress = resultSet.getString(3);
				int unitsConsumed = resultSet.getInt(4);
				float billAmount = resultSet.getFloat(5);
				System.out.println(String.format("id: %s, name: %s, address: %s, units: %d,bill: %f", consumerNumber,
						consumerName, consumerAddress, unitsConsumed, billAmount));
			}

		}
	}
}
