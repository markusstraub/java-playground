package com.github.mstraub.geotools;

import org.geotools.factory.Hints;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class AutoReproject {

    public static void main(String[] args) throws ParseException, NoSuchAuthorityCodeException, FactoryException,
            MismatchedDimensionException, TransformException {
        Hints hints = new Hints(Hints.CRS, DefaultGeographicCRS.WGS84);
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(hints);
        WKTReader2 reader = new WKTReader2(geometryFactory);

        // two lineStrings in Vienna, both around 200m long
        Geometry linestringA = reader.read("LINESTRING(16.368831 48.201796,16.368831 48.2)");
        Geometry linestringB = reader.read("LINESTRING(16.368831 48.2,16.371526 48.2)");
        // automatically get CRS
        CoordinateReferenceSystem auto = CRS.decode("AUTO:42001,16.368831,48.2");
        // CoordinateReferenceSystem auto = CRS.decode("EPSG:31256");
        System.out.println(auto);
        
        MathTransform transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, auto);
        System.out.println(JTS.transform(linestringA, transform).getLength());
        System.out.println(JTS.transform(linestringB, transform).getLength());
    }

}
