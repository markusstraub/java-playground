package com.github.mstraub.persistence;
//package com.github.mstraub.persistence;
//
//import java.io.IOException;
//import java.nio.file.Path;
//
//import net.openhft.chronicle.map.ChronicleMap;
//
//public class ChronicleTest {
//	
//	private ChronicleMap<CharSequence, PostalCodeRange> cityPostalCodes;
//	
//	public ChronicleTest(Path cityPostalCodesFile) throws IOException {
//		cityPostalCodes = ChronicleMap
//	    .of(CharSequence.class, PostalCodeRange.class)
//	    .name("city-postal-codes-map")
//	    .averageKey("Amsterdam")
//	    .entries(50_000) // apparently we can not differentiate between cache entries in memory and on disk :/
//	    .createPersistedTo(cityPostalCodesFile.toFile());
//		
//	}
//
////	ChronicleMapBuilder<CharSequence, PostalCodeRange> cityPostalCodesMapBuilder =
////	    ChronicleMapBuilder.of(CharSequence.class, PostalCodeRange.class)
////	        .name("city-postal-codes-map")
////	        .averageKey("Amsterdam")
////	        .entries(50_000);
////	ChronicleMap<CharSequence, PostalCodeRange> cityPostalCodes =
////	    cityPostalCodesMapBuilder.createPersistedTo(cityPostalCodesFile);
//
//
//
//
//	
//
//	
//	interface PostalCodeRange {
//	    int minCode();
//	    void minCode(int minCode);
//
//	    int maxCode();
//	    void maxCode(int maxCode);
//	}
//}
