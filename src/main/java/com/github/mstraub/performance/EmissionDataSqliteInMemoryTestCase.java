package com.github.mstraub.performance;
//package performance;
//
//import java.io.File;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class EmissionDataSqliteInMemoryTestCase extends
//		EmissionDataSqliteTestCase {
//
//	
//	
//	public EmissionDataSqliteInMemoryTestCase(String fileName,
//			String[] trafficStates) {
//		super(fileName, trafficStates);
//	}
//
//	@Override
//	public void openConnection(File sqliteFile) {
//		System.out.println("Loading " + sqliteFile.getAbsolutePath());
//		try {
//			// load the sqlite driver
//			Class.forName("org.sqlite.JDBC");
//			
//			conn = DriverManager.getConnection("jdbc:sqlite::memory:");
//			Statement stmt = conn.createStatement();
//			ResultSet rs = null;
//			
//			// attach local database to in-memory database
//			String attachStmt = "ATTACH '" + sqliteFile.getAbsolutePath() + "' AS src";
//			stmt.execute(attachStmt);
//			
//			// copy table definition
//			String tableQuery = "SELECT sql FROM src.sqlite_master WHERE type='table';";
//			rs = stmt.executeQuery(tableQuery);
//			while(rs.next()) {
//				stmt.execute(rs.getString(1));
//			}
//			
//			// copy data
//			String tableNameQuery = "SELECT name FROM sqlite_master WHERE type='table'";
//			rs = stmt.executeQuery(tableNameQuery);
//			while(rs.next()) {
//				String copyDataQuery = 
//					"INSERT INTO " + rs.getString(1) + " SELECT * FROM src." + rs.getString(1);
//				stmt.execute(copyDataQuery);
//			}
//			
//			// copy other definitions
//			String nonTableQuery = "SELECT sql FROM src.sqlite_master WHERE type!='table';";
//			rs = stmt.executeQuery(nonTableQuery);
//			while(rs.next()) {
//				stmt.execute(rs.getString(1));
//			}
//			
//			// detach local db
//			String detachStmt = "DETACH src";
//			stmt.execute(detachStmt);
//			
//			// test if everything went well
////			String sqlQuery = "SELECT sql FROM sqlite_master";
////			String recordsQuery = "SELECT COUNT(*) FROM emissions";
////			rs = stmt.executeQuery(sqlQuery);
////			System.out.println("Database in memory has the following definition:");
////			while(rs.next())
////				System.out.println(rs.getString(1));
////			rs = stmt.executeQuery(recordsQuery);
////			while(rs.next())
////				System.out.println("Database contains " + rs.getInt(1) + " records");
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
//	}
//
//}
