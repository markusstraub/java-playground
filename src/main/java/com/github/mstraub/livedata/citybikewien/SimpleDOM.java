package com.github.mstraub.livedata.citybikewien;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SimpleDOM {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		SimpleDOM simpledom = new SimpleDOM();
		simpledom.parse();
	}
	
	private XPath xpath;
	
	public SimpleDOM() {
		xpath = XPathFactory.newInstance().newXPath();
	}
	
	public void parse() throws ParserConfigurationException, SAXException, IOException, NumberFormatException, XPathExpressionException {
		// parse the XML as a W3C Document
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new File("/tmp/citybike.xml"));

		// status = ["aktiv", "nicht in Betrieb"]
		String expression = "/stations/station[status='aktiv']";
		NodeList stationNodes = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
		
		System.out.println("active stations: " + stationNodes.getLength());
		for(int i=0; i<stationNodes.getLength(); i++) {
			parseStation(stationNodes.item(i));
		}
	}
	
	public void parseStation(Node station) throws NumberFormatException, XPathExpressionException {
		int id = Integer.parseInt(xpath.evaluate("id", station));
		int internalId = Integer.parseInt(xpath.evaluate("internal_id", station));
		String name = xpath.evaluate("name", station);
		int boxes = Integer.parseInt(xpath.evaluate("boxes", station));
		int freeBoxes = Integer.parseInt(xpath.evaluate("free_boxes", station));
		int freeBikes = Integer.parseInt(xpath.evaluate("free_bikes", station));
		String description = xpath.evaluate("description", station);
		double latitude = Double.parseDouble(xpath.evaluate("free_boxes", station));
		double longitude = Double.parseDouble(xpath.evaluate("free_boxes", station));
	}

}
