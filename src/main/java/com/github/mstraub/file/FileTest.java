/**
 * 
 */
package com.github.mstraub.file;

import java.io.File;

/**
 * @author mstraub
 *
 */
public class FileTest {

	public static void main(String[] args) {
		File f = new File("/home/mstraub/projects/ariadne-data/maps/vienna_ta2008.04/*_nw.shp");
		if(f.exists())
			System.out.println(f.getAbsolutePath());
		else
			System.err.println("neeeee");
	}
}
