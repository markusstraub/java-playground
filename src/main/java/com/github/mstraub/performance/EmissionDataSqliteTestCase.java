package com.github.mstraub.performance;
//package performance;
//
//import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Random;
//
//import com.github.ariadne.util.io.emission.EmissionFactorHot;
//import com.github.ariadne.util.io.emission.EmissionFactorHot.Emission;
//import com.github.ariadne.util.io.emission.EmissionFactorHot.TrafficState;
//
//public class EmissionDataSqliteTestCase extends TestCase {
//
//	protected String fileName;
//	protected Connection conn;
//	protected String[] trafficStates;
//
//	public EmissionDataSqliteTestCase(String fileName, String[] trafficStates) {
//		conn = null;
//		this.fileName = fileName;
//		this.trafficStates = trafficStates;
//	}
//
//	@Override
//	public void prepareTest() {
//		openConnection(new File(fileName));
//	}
//
//	@Override
//	public void conductTest(int iterations) {
//		boolean queryOk = true;
//		Random generator = new Random(34087);
//		int randomIndex;
//		try {
//			Statement stmt = conn.createStatement();
//
//			for(int i=0; i<iterations; i++) {
//				randomIndex = generator.nextInt() % trafficStates.length;
//				if(randomIndex < 0)
//					randomIndex *= -1;
//				
//				String query = "SELECT * FROM emissions " + 
//				"WHERE subsegment=\"SoloLkw >14-20t Euro-V SCR\" AND " + 
//				"component = \"CO2(rep.)\" AND " +
//				"trafficSit = \"" + trafficStates[randomIndex] + "\";";
//				ResultSet rs = stmt.executeQuery(query);
//
//				int count = 0;
//				while (rs.next()) {
//					EmissionFactorHot factor = new EmissionFactorHot(
//							rs.getString("vehCat"),
//							rs.getString("component"),
//							rs.getString("trafficSit"),
//							rs.getString("subsegment"),
//							rs.getDouble("v"),
//							rs.getDouble("efa"),
//							rs.getDouble("efa0"),
//							rs.getDouble("efa100"));
//					count++;
//				}
//				if(count != 1)
//					queryOk = false;
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if(!queryOk)
//			System.err.println("SQL resultset problems. Either a set was empty or contained too many results!");
//	}
//
//	@Override
//	public void conductTest2(int iterations) {
//		boolean queryOk = true;
//		Random generator = new Random(34087);
//		int randomIndex, randomIndex2;
//		try {
//			Statement stmt = conn.createStatement();
//
//			for(int i=0; i<iterations; i++) {
//				randomIndex = generator.nextInt() % trafficStates.length;
//				if(randomIndex < 0)
//					randomIndex *= -1;
//				randomIndex2 = generator.nextInt() % trafficStates.length;
//				if(randomIndex2 < 0)
//					randomIndex2 *= -1;
//				
//				String vehicleType = "SoloLkw >14-20t Euro-V SCR";
//				Emission emission = Emission.CO2;
//				
//				String query = 
//				"SELECT trafficSit, efa, efa0, efa100 " + 
//				"FROM emissions " + 
//				"WHERE subsegment='" + vehicleType + "' " +
//				"AND component='" + emission.getCsvField() + "' "+
//				"AND (trafficSit='" + trafficStates[randomIndex] + "' " +
//				"     OR trafficSit='" + trafficStates[randomIndex2] + "')";
//				ResultSet rs = stmt.executeQuery(query);
//
//				int count = 0;
//				while (rs.next()) {
////					EmissionFactorHot factor = new EmissionFactorHot(
////							rs.getString("vehCat"),
////							rs.getString("component"),
////							rs.getString("trafficSit"),
////							rs.getString("subsegment"),
////							rs.getDouble("v"),
////							rs.getDouble("efa"),
////							rs.getDouble("efa0"),
////							rs.getDouble("efa100"));
//					count++;
//				}
//				if(count != 2)
//					queryOk = false;
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if(!queryOk)
//			System.err.println("SQL resultset problems. Either a set was empty or contained too many results!");
//	}
//	
//	public void conductTest3(int iterations) {
//		boolean queryOk = true;
//		Random generator = new Random(34087);
//		int randomIndex, randomIndex2;
//		try {
//			Statement stmt = conn.createStatement();
//
//			for(int i=0; i<iterations; i++) {
//				randomIndex = generator.nextInt() % trafficStates.length;
//				if(randomIndex < 0)
//					randomIndex *= -1;
//				randomIndex2 = generator.nextInt() % trafficStates.length;
//				if(randomIndex2 < 0)
//					randomIndex2 *= -1;
//				
//				String vehicleType = "SoloLkw >14-20t Euro-V SCR";
//				Emission emission = Emission.CO2;
//				
//				String query = 
//				"SELECT trafficSit, efa, efa0, efa100 " + 
//				"FROM emissions " + 
//				"WHERE subsegment='" + vehicleType + "' " +
//				"AND component='" + emission.getCsvField() + "' "+
//				"AND trafficSit LIKE 'Agglo/Sammel/50/%'";
//				ResultSet rs = stmt.executeQuery(query);
//
//				int count = 0;
//				while (rs.next()) {
////					EmissionFactorHot factor = new EmissionFactorHot(
////							rs.getString("vehCat"),
////							rs.getString("component"),
////							rs.getString("trafficSit"),
////							rs.getString("subsegment"),
////							rs.getDouble("v"),
////							rs.getDouble("efa"),
////							rs.getDouble("efa0"),
////							rs.getDouble("efa100"));
//					count++;
//				}
//				if(count != 4)
//					queryOk = false;
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if(!queryOk)
//			System.err.println("SQL resultset problems. Either a set was empty or contained too many results!");
//	}
//	
//	public void conductTest4(int iterations) {
//		boolean queryOk = true;
//		Random generator = new Random(34087);
//		int randomIndex, randomIndex2;
//		try {
//			Statement stmt = conn.createStatement();
//
//			for(int i=0; i<iterations; i++) {
//				randomIndex = generator.nextInt() % trafficStates.length;
//				if(randomIndex < 0)
//					randomIndex *= -1;
//				randomIndex2 = generator.nextInt() % trafficStates.length;
//				if(randomIndex2 < 0)
//					randomIndex2 *= -1;
//				
//				String vehicleType = "SoloLkw >14-20t Euro-V SCR";
//				Emission emission = Emission.CO2;
//				
//				String query = 
//				"SELECT trafficSit, efa, efa0, efa100 " + 
//				"FROM emissions " + 
//				"WHERE subsegment='" + vehicleType + "' " +
//				"AND component='" + emission.getCsvField() + "' "+
//				"AND (trafficSit = 'Agglo/Sammel/50/" + TrafficState.STOPGO + "' OR " +
//				"     trafficSit = 'Agglo/Sammel/50/" + TrafficState.LOW + "' OR " +
//				"     trafficSit = 'Agglo/Sammel/50/" + TrafficState.MEDIUM + "' OR " +
//				"     trafficSit = 'Agglo/Sammel/50/" + TrafficState.HIGH + "')";
//				ResultSet rs = stmt.executeQuery(query);
//
//				int count = 0;
//				while (rs.next()) {
////					EmissionFactorHot factor = new EmissionFactorHot(
////							rs.getString("vehCat"),
////							rs.getString("component"),
////							rs.getString("trafficSit"),
////							rs.getString("subsegment"),
////							rs.getDouble("v"),
////							rs.getDouble("efa"),
////							rs.getDouble("efa0"),
////							rs.getDouble("efa100"));
//					count++;
//				}
//				if(count != 4)
//					queryOk = false;
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if(!queryOk)
//			System.err.println("SQL resultset problems. Either a set was empty or contained too many results!");
//	}
//	
//	public void openConnection(File sqliteFile) {
//		System.out.println("Loading " + sqliteFile.getAbsolutePath());
//		try {
//			Class.forName("org.sqlite.JDBC");
//			conn = DriverManager.getConnection("jdbc:sqlite:"+sqliteFile.getAbsolutePath());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
//	}
//
//}
