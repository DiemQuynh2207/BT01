package vn.iostar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionMySQL {
		private static String USERNAME ="root";
		private static String PASSWORD = "Diemquynhankieng0274";
		private static String DRIVER = "com.mysql.cj.jdbc.Driver";
		private static String URL = "jdbc:mysql://127.0.0.1:3306/bt01";
		
		public static Connection getDatabaseConnection() throws  ClassNotFoundException, SQLException {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		
		public static void main (String[] args)
		{
			try {
				new DBConnectionMySQL();
					System.out.println(DBConnectionMySQL.getDatabaseConnection());
			
			} catch (Exception e) {
				e.printStackTrace();	
			}
			
		}
}
