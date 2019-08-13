package com.github.mstraub.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * There are subtle differences as to how the fileName you are passing is
 * interpreted. Basically, you have 2 different methods:
 * ClassLoader.getResourceAsStream() and Class.getResourceAsStream(). These two
 * methods will locate the resource differently.
 * 
 * In Class.getResourceAsStream(path), the path is interpreted as a path local
 * to the package of the class you are calling it from. For example calling,
 * String.getResourceAsStream("myfile.txt") will look for a file in your
 * classpath at the following location: "java/lang/myfile.txt". If your path
 * starts with a /, then it will be considered an absolute path, and will start
 * searching from the root of the classpath. So calling
 * String.getResourceAsStream("/myfile.txt") will look at the following location
 * in your class path ./myfile.txt.
 * 
 * ClassLoader.getResourceAsStream(path) will consider all paths to be absolute
 * paths. So calling String.getClassLoader().getResourceAsStream("myfile.txt")
 * and String.getClassLoader().getResourceAsStream("/myfile.txt") will both look
 * for a file in your classpath at the following location: ./myfile.txt.
 * 
 * Everytime I mention a location in this post, it could be a location in your
 * filesystem itself, or inside the corresponding jar file, depending on the
 * Class and/or ClassLoader you are loading the resource from.
 * 
 * In your case, you are loading the class from an Application Server, so your
 * should use
 * Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)
 * instead of this.getClass().getClassLoader().getResourceAsStream(fileName).
 * this.getClass().getResourceAsStream() will also work.
 * <p>
 * source: http://stackoverflow.com/a/676273/1648538
 */
public class ReadResourceFromSrcMainResources {

    public static void main(String[] args) throws IOException {
        fromClass();
        fromClassLoader();
    }

    private static void fromClassLoader() throws IOException {
        InputStream stream = ReadResourceFromSrcMainResources.class.getClassLoader().getResourceAsStream("data.txt");
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(stream))) {
            System.out.println(buffer.lines().collect(Collectors.joining("\n")));
        }
    }

    private static void fromClass() throws IOException {
        InputStream stream = ReadResourceFromSrcMainResources.class.getResourceAsStream("/data.txt");
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(stream))) {
            System.out.println(buffer.lines().collect(Collectors.joining("\n")));
        }
    }

}
