package com.github.mstraub.graphhopper.reader.copy;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.google.common.base.Joiner;
import com.graphhopper.reader.OSMElement;
import com.graphhopper.reader.OSMInputFile;
import com.graphhopper.reader.OSMWay;
import com.graphhopper.util.Helper;

import gnu.trove.list.TLongList;

public class ReadTagsWithGraphHopper {
	
	private static final Logger logger = Logger.getLogger(ReadTagsWithGraphHopper.class.getName());
	
	private static final int nrWorkerThreads = 2;
	private static List<String> tagsToBeExtracted = Arrays.asList("highway", "name", "cycleway", "cycleway:left", "cycleway:right", "width", "lanes", "oneway", "oneway:bicycle");
	private static final String file = "/opt/ariadne-data2/maps/OSM_vienna_2015-01-30/map.pbf";
	private static final String outputFile = "/opt/ariadne-data2/maps/OSM_vienna_2015-01-30/osmAdditionalAttributes.csv";
	
	static {
		Collections.sort(tagsToBeExtracted);
	}
	
	
	public static void main(String[] args) {
		preProcess(Paths.get(file).toFile());
	}
	
	/**
     * Preprocessing of OSM file to select nodes which are used for highways. This allows a more
     * compact graph data structure.
     */
	static void preProcess( File osmFile )
    {
        OSMInputFile in = null;
        try
        {
            in = new OSMInputFile(osmFile).setWorkerThreads(nrWorkerThreads).open();

            long tmpWayCounter = 1;
            OSMElement item;
            
            try (BufferedWriter csvWriter = Files.newBufferedWriter(Paths.get(outputFile), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            	csvWriter.write(OSMTags.getCsvHeader());
            	csvWriter.write("\n");
            	
            	while ((item = in.getNext()) != null)
	            {
	                if (item.isType(OSMElement.WAY))
	                {
	                    final OSMWay way = (OSMWay) item;
	                    TLongList wayNodes = way.getNodes();
	                    int s = wayNodes.size();
	                    
	                    OSMTags tags = new OSMTags(way);
	                    csvWriter.write(tags.toCsvRow());
	                    csvWriter.write("\n");
	                    
//	                    System.out.println("way with id: " + way.getId() + " " + Joiner.on(';').withKeyValueSeparator("=").join(way.getTags()));
	
	                    if (++tmpWayCounter % 5000 == 0) {
	                    	System.out.println(".");
	                    }
	//                    {
	//                        logger.info(nf(tmpWayCounter) + " (preprocess), osmIdMap:" + nf(getNodeMap().getSize()) + " ("
	//                                + getNodeMap().getMemoryUsage() + "MB) " + Helper.getMemInfo());
	//                    }
	                }
	            }
            }
        } catch (Exception ex)
        {
            throw new RuntimeException("Problem while parsing file", ex);
        } finally
        {
            Helper.close(in);
        }
    }
	
	static class OSMTags {
		private long osmId;
		private Map<String, String> tags = new TreeMap<>();
		private static final String SEPARATOR = "|";
		
		public OSMTags(OSMWay way) {
			this.osmId = way.getId();
			for(String key : tagsToBeExtracted) {
				tags.put(key, way.getTag(key, ""));
			}
		}
		
		public static String getCsvHeader() {
			return "osmId" + SEPARATOR + Joiner.on(SEPARATOR).join(tagsToBeExtracted);
		}
		
		public String toCsvRow() {
			return osmId + SEPARATOR + Joiner.on(SEPARATOR).join(tags.values());
		}
		
	}

}
