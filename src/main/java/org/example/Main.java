package org.example;

import org.example.GeoData.GeoDataEateries;
import org.example.GeoData.GeoDataRoads;
import org.example.Geohash.Geohash;
import org.example.Geohash.SpatialKeyAlgo;
import org.example.StrategyImplementations.LeastTimeBasedMatchingStrategy;
import org.example.StrategyInterfaces.DriverMatchingStrategy;
import org.example.util.Shapes.BBox;
import org.example.Trip.TripMetaData;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Ctrl+D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Cmd+F8.
            System.out.println("i = " + i);
        }

        TripMetaData t = new TripMetaData();
        t.loadCoordinates();
        t.createMap();
        DriverMatchingStrategy dms = new LeastTimeBasedMatchingStrategy();
        dms.matchDriver(t);
        Geohash<Long> ska = new SpatialKeyAlgo(31,new BBox(-180,180,-90,90));

        //System.out.print(ska.encodeLatLon(89,179));

        GeoDataRoads r = new GeoDataRoads();
        r.loadEateries();
        //GeoDataEateries g = new GeoDataEateries();
       // g.loadEateries();
    }
}