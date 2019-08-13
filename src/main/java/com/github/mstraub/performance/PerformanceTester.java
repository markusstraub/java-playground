package com.github.mstraub.performance;
//package performance;
//
//import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Vector;
//
//public class PerformanceTester {
//	
//	private void runTests(Vector<TestCase> testCases, int iterations) {
//		long start, duration;
//		for(TestCase tc : testCases) {
//			System.out.println("========================");
//			System.out.println(tc.getClass().getName() + ": START");
//			start = System.currentTimeMillis();
//			tc.prepareTest();
//			duration = System.currentTimeMillis() - start;
//			System.out.println(tc.getClass().getName()
//					+ ": preparation finished in " + duration + "ms");
//
//			start = System.currentTimeMillis();
//			tc.conductTest(iterations);
//			duration = System.currentTimeMillis() - start;
//			System.out.println(tc.getClass().getName() + ": " + iterations
//					+ " iterations of Test 1 finished in " + duration  + "ms");
//			
//			start = System.currentTimeMillis();
//			tc.conductTest2(iterations);
//			duration = System.currentTimeMillis() - start;
//			System.out.println(tc.getClass().getName() + ": " + iterations
//					+ " iterations of Test 2 finished in " + duration  + "ms");
//			
//			start = System.currentTimeMillis();
//			tc.conductTest3(iterations);
//			duration = System.currentTimeMillis() - start;
//			System.out.println(tc.getClass().getName() + ": " + iterations
//					+ " iterations of Test 3 finished in " + duration  + "ms");
//			
//			start = System.currentTimeMillis();
//			tc.conductTest4(iterations);
//			duration = System.currentTimeMillis() - start;
//			System.out.println(tc.getClass().getName() + ": " + iterations
//					+ " iterations of Test 4 finished in " + duration  + "ms");
//		}
//	}
//	
//	private String[] getTrafficStates(File sqliteFile) {
//		Vector<String> trafficSits = new Vector<String>();
//		
//		try {
//			Class.forName("org.sqlite.JDBC");
//
//
//			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+sqliteFile.getAbsolutePath());
//			Statement stmt = conn.createStatement();
//			ResultSet rs = null;
//
//			// copy data
//			String trafficSitQuery = "SELECT DISTINCT(TrafficSit) FROM emissions";
//			rs = stmt.executeQuery(trafficSitQuery);
//			
//			while(rs.next())
//				trafficSits.add(rs.getString(1));
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return trafficSits.toArray(new String[1]);
//	}
//	
//	
//	public static void main(String[] args) {
//		PerformanceTester pt = new PerformanceTester();
//		
//		String path = "/home/mstraub/projects/ilos/svn/trunk/java/indicator2/resources/emissions/";
//		
//		String[] trafficStates = pt.getTrafficStates(new File(path + "emissions.sqlite"));
//		
//		EmissionDataSqliteTestCase sql =
//			new EmissionDataSqliteTestCase(path + "emissions.sqlite", trafficStates);	
//		EmissionDataSqliteInMemoryTestCase sqlInMemory =
//			new EmissionDataSqliteInMemoryTestCase(path + "emissions.sqlite", trafficStates);	
//		EmissionDataCSVTestCase csvHashmap =
//			new EmissionDataCSVTestCase(path + "emissions.csv", true);
//		EmissionDataCSVTestCase csv =
//			new EmissionDataCSVTestCase(path + "emissions.csv", false);
//		
//		Vector<TestCase> testCases = new Vector<TestCase>();
//		testCases.add(sql); // db must be indexed on trafficSit, subsegment (otherwise it's superslow)
////		testCases.add(sqlInMemory);
////		testCases.add(csv);
////		testCases.add(csvHashmap);
//		pt.runTests(testCases, 50);
//	}
//}
