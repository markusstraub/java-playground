package com.github.mstraub.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Simple Server - try running this class and visit
 * http://localhost:8000/applications/myapp
 * 
 * --- ECLIPSE --- The problem is that the HTTP server is part of the Sun JRE6
 * but not part of standardized Java. Eclipse therefore blocks access to it.
 * This can be fixed by going to the Window->Preferences window, selecting
 * Java->Compiler->Errors/Warnings, selecting Deprecated and Restricted API and
 * then changing the setting for Forbidden Reference and Discouraged Reference
 * to Warning instead of Error.
 * 
 * source:
 * http://brunovernay.blogspot.com/2009/05/suns-jvm-has-http-server-embedded
 * .html
 * 
 * also of interest:
 * http://stackoverflow.com/questions/4765599/java-http-server-library
 * 
 * @author mstraub
 * 
 */
public class SimpleJavaHttpServer implements HttpHandler {
	@Override
    public void handle(HttpExchange t) throws IOException {
		InputStream is = t.getRequestBody();
		// read(is); // .. read the request body
		String response = "This is the response";
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	public static void main(String args[]) throws IOException {
		@SuppressWarnings("restriction")
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/applications/myapp", new SimpleJavaHttpServer());
		server.setExecutor(null); // creates a default executor
		server.start();
	}
}
