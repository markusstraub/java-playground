package com.github.mstraub.geotools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

public class GeometryIntersection {

	public static void main(String[] args) throws IOException, ParseException {

		try (InputStream inputStream = new FileInputStream(
				Paths.get("src", "main", "resources", "vienna.geojson").toFile())) {
			GeometryJSON gjson = new GeometryJSON();
			FeatureJSON fjson = new FeatureJSON(gjson);
			FeatureCollection<SimpleFeatureType, SimpleFeature> collection = fjson.readFeatureCollection(inputStream);
			SimpleFeature feature = collection.features().next();
			Polygon vienna = (Polygon) feature.getDefaultGeometry();
			System.out.println(vienna);

			WKTReader reader = new WKTReader();
			LineString linestringCrossingVienna = (LineString) reader.read("LINESTRING(16.0 48.2, 16.9 48.2)");
			LineString linestringOnlyInVienna = (LineString) reader.read("LINESTRING(16.3 48.2, 16.33 48.22)");
			LineString linestringOutsideVienna = (LineString) reader.read("LINESTRING(16.0 47.9, 16.9 47.9)");

			System.out.println(check(vienna, linestringCrossingVienna));
			System.out.println(check(vienna, linestringOnlyInVienna));
			System.out.println(check(vienna, linestringOutsideVienna));

			Geometry difference = linestringCrossingVienna.difference(vienna);
			System.out.println("total length vs length outside of polygon: " + linestringCrossingVienna.getLength()
					+ " / " + difference.getLength());
		}
	}

	public enum Relation {
		FULLY_OUTSIDE, INTERSECTS, FULLY_INSIDE
	}

	private static Relation check(Polygon polygon, LineString linestring) {
		boolean within = linestring.within(polygon);
		if (within) {
			return Relation.FULLY_INSIDE;
		}

		boolean intersects = linestring.intersects(polygon);
		if (intersects) {
			return Relation.INTERSECTS;
		}
		return Relation.FULLY_OUTSIDE;
	}

}
