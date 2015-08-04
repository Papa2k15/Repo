package ludum.vita.dbtools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import ludum.vita.dao.DatabaseFactory;

public class AdminDataGenerator {
	public static void main(String[] args) throws IOException, SQLException {
		AdminDataGenerator gen = new AdminDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}

	private String DIR = "sql/data";
	private DatabaseFactory factory;

	public AdminDataGenerator() {
		this.factory = DatabaseFactory.getProductionInstance();
	}

	public AdminDataGenerator(String projectHome, DatabaseFactory factory) {
		this.DIR = projectHome + "/sql/data";
		this.factory = factory;
	}
	
	public void clearAllTables() throws SQLException, FileNotFoundException, IOException {
		new DBBuilder(factory).executeSQLFile(DIR + "/deleteFromAllTables.sql");
	}

	public void standardData() throws FileNotFoundException, IOException, SQLException {
		generateUsers();
		System.out.println("Operation completed.");
	}
	
	// TODO
	public void generateUsers() throws SQLException, FileNotFoundException, IOException{
		new DBBuilder(factory).executeSQLFile(DIR+"/users.sql");
	}
}
