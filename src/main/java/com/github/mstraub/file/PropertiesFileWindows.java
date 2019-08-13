package com.github.mstraub.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author mstraub
 * 
 */
public class PropertiesFileWindows {
	public static void main(String[] args) {
		File f = new File("/tmp/paths.properties");
		FileInputStream in;
		Properties props = new Properties();
		try {
			in = new FileInputStream(f);
			props.load(in);
			in.close();
		} catch (IOException e) {
		}
		
		String rootdir = props.getProperty("ROOT_DIR");
		System.out.println(rootdir.replace("\\", "\\\\"));
	}

}
