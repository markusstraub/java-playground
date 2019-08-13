package com.github.mstraub.basejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetResourceAsStream {

    /**
     * With this example I found out the following problem: When the OSM_test
     * directory is already present in a .jar our project depends on and we want
     * to put some more files into this directory by creating the directory
     * within our project then we can access the file with the full path, but we
     * run into problems when checking where OSM_test resides. Obviously it
     * resides both in the .jar and in the current project, but the
     * {@link Class#getResource(String)} method only returns one of those. So it
     * seems impossible to list and use all files in the combined directory -
     * you need to know which files to expect.
     * 
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(GetResourceAsStream.class.getResource("/ariadne-data2/maps/OSM_test"));

        System.out.println(GetResourceAsStream.class.getResource("/ariadne-data2/maps/maps.qgs"));

        System.out.println(GetResourceAsStream.class.getResource("/ariadne-data2/maps/OSM_test/links.abf.gz"));
        System.out.println(
                GetResourceAsStream.class.getResource("/ariadne-data2/maps/OSM_test/motorized_traffic_volume.csv"));
        System.out.println(getStringFromInputStream(
                GetResourceAsStream.class.getResourceAsStream("/ariadne-data2/maps/maps.qgs")));

        System.out.println(getStringFromInputStream(GetResourceAsStream.class
                .getResourceAsStream("/ariadne-data2/maps/OSM_test/motorized_traffic_volume.csv")));
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
