package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	private Connection connection;

	public DBConnection(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(dbURL, user, pwd);
		System.out.println("Connection established...");
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() throws SQLException {
		if (this.connection != null) {
			this.connection.close();
		}
	}

	public static void seedDatabase() {
		String clearSeedsSQL = "Drop Database project";
		String createSchmaSQL = "Create Database project";
		String useSchemaSQL = "use project";
		String createTableSQL = "Create Table Product (id Integer auto_increment primary key, name varchar(255), category varchar(255), price decimal(10,2))";

		try {
			InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("config.properties");
			Properties props = new Properties();
			props.load(in);
			
			DBConnection conn = new DBConnection(props.getProperty("dbURL"), props.getProperty("user"), props.getProperty("pwd"));

			Statement statement = conn.getConnection().createStatement();
			try {
				statement.executeUpdate(clearSeedsSQL);
				System.out.println("dropping existing databases...");

			} catch (SQLException e) {
				System.out.println("Does not exist");
			}
			
			statement.executeUpdate(createSchmaSQL);
			System.out.println("creating schema...");
			statement.executeUpdate(useSchemaSQL);
			System.out.println("selecting schema...");
			statement.executeUpdate(createTableSQL);
			System.out.println("creating table...");

			String product1SQL = "insert into product(name, category, price) values ('Laptop', 'Electronics', 1800)";
			String product2SQL = "insert into product(name, category, price) values ('Washing Machine', 'Appliances', 2800)";
			String product3SQL = "insert into product(name, category, price) values ('Smart Phone', 'Electronics', 800)";
			String product4SQL = "insert into product(name, category, price) values ('Phone USB Charger', 'Accessories', 20)";
			String product5SQL = "insert into product(name, category, price) values ('Smart Watch', 'Electronics', 180)";
			String product6SQL = "insert into product(name, category, price) values ('Head Phones', 'Electronics', 100)";
			String product7SQL = "insert into product(name, category, price) values ('Microwave', 'Appliances', 450)";
			String product8SQL = "insert into product(name, category, price) values ('Shampoo', 'Cosmetics', 1.80)";
			String product9SQL = "insert into product(name, category, price) values ('Conditioner', 'Cosmetics', 1.30)";
			String product10SQL = "insert into product(name, category, price) values ('Shaving Cream', 'Cosmetics', 2.00)";

			statement.executeUpdate(product1SQL);
			statement.executeUpdate(product2SQL);
			statement.executeUpdate(product3SQL);
			statement.executeUpdate(product4SQL);
			statement.executeUpdate(product5SQL);
			statement.executeUpdate(product6SQL);
			statement.executeUpdate(product7SQL);
			statement.executeUpdate(product8SQL);
			statement.executeUpdate(product9SQL);
			statement.executeUpdate(product10SQL);
			System.out.println("updating records...");

			statement.close();
			conn.closeConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		seedDatabase();
	}
}
