package com.github.mstraub.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author mstraub
 * 
 *         the behaviour of File with trailing slashes..
 * 
 */
public class DirTest {

	static String[] dirs = new String[] { "/tmp///", "/tmp/", "/tmp", };

	static String[] createDirs = new String[] { "/tmp////bla/", "/tmp/bla2",
			"/tmp/bla3/bla", };

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < dirs.length; i++) {
			System.out.println(dirs[i]);
			File f = new File(dirs[i]);
			System.out.println(f.getAbsoluteFile());
			if (!f.exists() || !f.canWrite() || !f.isDirectory())
				System.out.println("oha.. problem!");
			System.out.println();
		}
		
		for (int i = 0; i < createDirs.length; i++) {
			System.out.println(createDirs[i]);
			File f = new File(createDirs[i]);
			System.out.println(f.getAbsoluteFile());
			f.mkdirs();
			if (!f.exists() || !f.canWrite() || !f.isDirectory())
				System.out.println("oha.. problem!");
			System.out.println();
		}
		
		File f = new File("/tmp/bla4/blabla/blubb");
		new File(f.getParent()).mkdirs();
		FileOutputStream out = new FileOutputStream(f);
		out.write(0);
		out.close();
		
	}

}
