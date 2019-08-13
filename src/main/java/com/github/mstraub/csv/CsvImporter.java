package com.github.mstraub.csv;
//package csv;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.supercsv.cellprocessor.ift.CellProcessor;
//import org.supercsv.io.CsvBeanReader;
//import org.supercsv.io.ICsvBeanReader;
//import org.supercsv.prefs.CsvPreference;
//
///**
// * Generic helper method to load emission data from CSV files.
// * 
// * @author mstraub
// *
// */
//public class CsvImporter {
//	
//	public static <T> int loadFromCSV( CellProcessor[] processors, String inputFile,
//							Class<T> clazz, ArrayList<T> list) {
//	   	int count = -1;
//
//	   	FileReader fr = null;
//	   	ICsvBeanReader inFile = null;
//	   	
//	   	try {
//	    	fr = new FileReader(inputFile);
//	    	inFile = new CsvBeanReader(fr, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
//	        String[] header = inFile.getCSVHeader(true);
//	        
//			T item;
//			try {
//				item = clazz.newInstance();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//			
//			count = 0;
//			while( (item = inFile.read(clazz, header, processors)) != null) {
//				list.add(item);
//				count++;
//			}
//	   	} catch(IOException ioe) {
//	   		ioe.printStackTrace();
//		} finally {
//			try {
//				if (inFile != null)
//					inFile.close();
//				if (fr != null)
//					fr.close();
//			} catch (IOException e) {}
//		}
//        return count; 
//    }
//}
