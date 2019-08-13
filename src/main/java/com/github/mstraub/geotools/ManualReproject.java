package com.github.mstraub.geotools;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class ManualReproject {

	private static final CoordinateReferenceSystem CRS_WGS84; // = DefaultGeographicCRS.WGS84;
	private static final CoordinateReferenceSystem CRS_EPSG31256;
	private static final MathTransform TO_EPSG31256, TO_WGS84;

	static {
		try {
			// what seems to work: DefaultGeographicCRS.WGS84
			// in combination with the WKT from https://epsg.io/31256
			CRS_WGS84 = DefaultGeographicCRS.WGS84;
			CRS_EPSG31256 = CRS.parseWKT("PROJCS[\"MGI / Austria GK East\",\n" + "    GEOGCS[\"MGI\",\n"
					+ "        DATUM[\"Militar_Geographische_Institute\",\n"
					+ "            SPHEROID[\"Bessel 1841\",6377397.155,299.1528128,\n"
					+ "                AUTHORITY[\"EPSG\",\"7004\"]],\n"
					+ "            TOWGS84[577.326,90.129,463.919,5.137,1.474,5.297,2.4232],\n"
					+ "            AUTHORITY[\"EPSG\",\"6312\"]],\n" + "        PRIMEM[\"Greenwich\",0,\n"
					+ "            AUTHORITY[\"EPSG\",\"8901\"]],\n" + "        UNIT[\"degree\",0.0174532925199433,\n"
					+ "            AUTHORITY[\"EPSG\",\"9122\"]],\n" + "        AUTHORITY[\"EPSG\",\"4312\"]],\n"
					+ "    PROJECTION[\"Transverse_Mercator\"],\n" + "    PARAMETER[\"latitude_of_origin\",0],\n"
					+ "    PARAMETER[\"central_meridian\",16.33333333333333],\n"
					+ "    PARAMETER[\"scale_factor\",1],\n" + "    PARAMETER[\"false_easting\",0],\n"
					+ "    PARAMETER[\"false_northing\",-5000000],\n" + "    UNIT[\"metre\",1,\n"
					+ "        AUTHORITY[\"EPSG\",\"9001\"]],\n" + "    AUTHORITY[\"EPSG\",\"31256\"]]");

			// also works in combination (but with switched x/y for EPSG:4326...):
			// had problems with JUnit test.. so let's keep the method above.
//			CRS_WGS84 = CRS.decode("EPSG:4326");
//			CRS_EPSG31256 = CRS.decode("EPSG:31256");

			// the WKT from the offical source can not be parsed:
			// http://epsg-registry.org/export.htm?wkt=urn:ogc:def:crs:EPSG::31256

			TO_EPSG31256 = CRS.findMathTransform(CRS_WGS84, CRS_EPSG31256);
			TO_WGS84 = CRS.findMathTransform(CRS_EPSG31256, CRS_WGS84);
		} catch (FactoryException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws TransformException {
		Coordinate wgs84 = new Coordinate(16, 48);
		Coordinate local = JTS.transform(wgs84, null, TO_EPSG31256);
		Coordinate backToWgs84 = JTS.transform(local, null, TO_WGS84);

		System.out.println(wgs84 + " -> " + local + " -> " + backToWgs84);
	}

}
