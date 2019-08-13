package com.github.mstraub.geotools;

import java.io.IOException;

import org.geotools.factory.Hints;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.simplify.DouglasPeuckerSimplifier;

public class GeometrySimplificationDouglasPeucker {

    public static void main(String[] args) throws NoSuchAuthorityCodeException, FactoryException,
            MismatchedDimensionException, TransformException, ParseException, IOException {
        Hints hints = new Hints(Hints.CRS, DefaultGeographicCRS.WGS84);
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(hints);
        WKTReader2 reader = new WKTReader2(geometryFactory);

        // linestring in Vienna
        Geometry linestring = reader.read(
                "LineString (16.36990959999999973 48.21004649999999714, 16.3698958999999995 48.21009449999999674, 16.36989159999999899 48.21010940000000033, 16.36991370000000146 48.21011579999999697, 16.36988260000000039 48.2101153999999994, 16.36987960000000086 48.21013750000000186, 16.36987549999999914 48.21016780000000068)");
        System.out.println(linestring.getLength() + " - " + linestring);

        // automatically get CRS
        CoordinateReferenceSystem auto = CRS.decode("AUTO:42001,16.368831,48.2");
        MathTransform forwardTransform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, auto);
        MathTransform backwardTransform = CRS.findMathTransform(auto, DefaultGeographicCRS.WGS84);
        
        // transform to local CRS (in meters)
        linestring = JTS.transform(linestring, forwardTransform);
        System.out.println(linestring.getLength() + " - " + linestring);

        // simplify
        double toleranceMeters = 5;
        DouglasPeuckerSimplifier simplifier = new DouglasPeuckerSimplifier(linestring);
        simplifier.setDistanceTolerance(toleranceMeters);
        linestring= simplifier.getResultGeometry();
        System.out.println(linestring.getLength() + " - " + linestring);
        
        // transform back to WGS84
        linestring = JTS.transform(linestring, backwardTransform);
        System.out.println(linestring.getLength() + " - " + linestring);

        new GeometryJSON(10).write(linestring, "/tmp/geometry.json");
    }

}
