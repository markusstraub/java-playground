package com.github.mstraub.geotools;

import org.geotools.factory.Hints;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.referencing.CRS;
import org.geotools.referencing.ReferencingFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

/**
 * gis.stackexchange.com/questions/219555/geotools-unexpected-difference-between-crs-decodeepsg4326-and-defaultgeogr
 */
public class LatLonVsLonLatExperiment {

    public static void main(String[] args) throws Exception {
        CoordinateReferenceSystem sourceCRS;

        boolean aorb = true;
        if (aorb) {
            // lat/lon expected! but we can change that with a hint!
            Hints crsHints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, true);
            CRSAuthorityFactory factory = ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", crsHints);
            sourceCRS = factory.createCoordinateReferenceSystem("EPSG:4326");
        } else {
            // lon/lat expected!
            sourceCRS = DefaultGeographicCRS.WGS84;
        }

        Hints hints = new Hints(Hints.CRS, sourceCRS);
        CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:31256");
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(hints);
        WKTReader2 reader = new WKTReader2(geometryFactory);

        // two lineStrings in Vienna, both around 200m long
        Geometry linestringNorthSouth = reader.read("LINESTRING(16.368831 48.201796,16.368831 48.2)");
        Geometry linestringEastWest = reader.read("LINESTRING(16.368831 48.2,16.371526 48.2)");

        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
        System.out.println(sourceCRS);
        System.out.println("northSouth: " + JTS.transform(linestringNorthSouth, transform).getLength() + "m");
        System.out.println("eastWest: " + JTS.transform(linestringEastWest, transform).getLength() + "m");
    }

}
