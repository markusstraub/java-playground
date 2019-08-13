package com.github.mstraub.devbrc.java7;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demonstration of new language features related to syntactic sugar. See
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/enhancements.html#javase7
 * 
 * @author markus
 */
public class SyntacticSugar {
	
	public static void main(String[] args) {
		
		// ---------------
		// fancy new binary notation for byte, short, int, long (0B or 0b)
		// ---------------
		
		// An 8-bit 'byte' value:
		byte aByte = (byte)0b00100001;

		// A 16-bit 'short' value:
		short aShort = (short)0b1010000101000101;

		// Some 32-bit 'int' values:
		int anInt1 = 0b10100001010001011010000101000101;
		int anInt2 = 0b101;
		int anInt3 = 0B101; // The B can be upper or lower case.

		// A 64-bit 'long' value. Note the "L" suffix:
		long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;
		
		short[] HAPPY_FACE = {
			(short)0b0000011111100000,
			(short)0b0000100000010000,
			(short)0b0001000000001000,
			(short)0b0010000000000100,
			(short)0b0100000000000010,
			(short)0b1000011001100001,
			(short)0b1000011001100001,
			(short)0b1000000000000001,
			(short)0b1000000000000001,
			(short)0b1001000000001001,
			(short)0b1000100000010001,
			(short)0b0100011111100010,
			(short)0b0010000000000100,
			(short)0b0001000000001000,
			(short)0b0000100000010000,
			(short)0b0000011111100000,
		};

		
		// ---------------
		// underscores in numeric litarals
		// ---------------
		
		int million = 1_000_000;
		double pi   = 3.14_15;
		long hex    = 0xDEAD_BEEF;
		byte b      = (byte)0b0010_0001;
		
		// underscores not allowed in these places:
		// at the beginning or end of a number
//		million     = _1_000_000;
		
		// adjacent to a decimal point in a floating point literal
//		pi          = 3._14_15;
		
		// prior to an F or L suffix
//		million     = 1_000_000_L;
		
		// in positions where a string of digits is expected
		million     = Integer.parseInt("1_000_000"); // throws NumberFormatException
	}

	// Strings in switch statements
	public String getTypeOfDayWithSwitchStatement(String dayOfWeekArg) {
	     String typeOfDay;
	     switch (dayOfWeekArg) {
	         case "Monday":
	             typeOfDay = "Start of work week";
	             break;
	         case "Tuesday":
	         case "Wednesday":
	         case "Thursday":
	             typeOfDay = "Midweek";
	             break;
	         case "Friday":
	             typeOfDay = "End of work week";
	             break;
	         case "Saturday":
	         case "Sunday":
	             typeOfDay = "Weekend";
	             break;
	         default:
	             throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
	     }
	     return typeOfDay;
	}

	public void typeInference() {
		// Type Inference for Generic Instance Creation
		// -> only as long as the compiler can infer type arguments from the context 
		
		Map<String, Integer> map;
		
		// old pre-Java5-way, not recommended (raw type)
		map = new HashMap();

		// old and unnecessarily verbose Java5&6-way
		map = new HashMap<String, Integer>();
		
		// new Java7-way - with the so-called diamond operator
		// uses generics but is short.
		map = new HashMap<>();
		
		
		
		List<String> list = new ArrayList<>();
		list.add("A");
		// The following statement should fail since addAll expects
		// Collection<? extends String>
//		list.addAll(new ArrayList<>());
		
		// The following statements compile:
		List<? extends String> list2 = new ArrayList<>();
		list.addAll(list2);
		
		// TODO and more:
		// Type Inference and Generic Constructors of Generic and Non-Generic Classes
		// see http://docs.oracle.com/javase/7/docs/technotes/guides/language/type-inference-generic-instance-creation.html
	}
	
	public void exceptionHandling() {
		
		try {
			if(System.currentTimeMillis() > 1_000_000)
				throw new IOException();
			else if(System.currentTimeMillis() > 1_000_000_000)
				throw new IllegalArgumentException();
			else
				throw new InvalidAlgorithmParameterException();
		} catch(IOException | IllegalArgumentException ex) {
			// look, ma! we just caught two different exceptions!
			ex.printStackTrace();
			// which automatically makes ex final:
//			ex = new IOException();
		} catch(InvalidAlgorithmParameterException ex) {
			ex.printStackTrace();
			// normally ex is not final:
			ex = new InvalidAlgorithmParameterException();
		}

		
		// TODO and more:
		// Rethrowing Exceptions with More Inclusive Type Checking
		// see http://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html
		
	}
	
}
