package com.github.mstraub.gis;

/**
 * for f in *.gml; do ogr2ogr -update -append merge.gml $f -f GML -nln merge; done
 *
 * @author mstraub
 *
 */
public class GPSCoordinateRing {

	
	public static void printNCircleCoordinates( double lat, double lon, double radius, int n ) {
		double alpha = Math.PI * 2 / n;
		double[] lats = new double[n];
		double[] lons = new double[n];
		
		double viennaCorrection = 0.6623;
		int i = -1;
		while( ++i < n) {
			double theta = alpha*i;
			lats[i] = lat + (Math.sin( theta ) * radius * viennaCorrection);
			lons[i] = lon + Math.cos(theta) * radius;
			System.out.println(String.format("wget \"http://localhost:8080/routingservice_restful/rest/route?from=%.4f,%.4f&to=%.4f,%.4f&mots=BIKE&edgeWeighter=CycleInfrastructureEdgeWeighter(0.5)&routeWeighters=DefaultRouteWeighter(TravelTimeEdgeWeighter)\" -O %d.gml", lat,lon, lats[i], lons[i],i));
		}
	}
	
	public static void main(String[] args) {
		System.out.println("rm *.gfs *.gml");
		printNCircleCoordinates(48.20271,16.369239, 0.05, 100);
	}
}
