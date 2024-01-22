package org.example.util;

public class BBox {

    public double minLon;
    public double maxLon;
    public double minLat;

    public BBox(double minLon, double maxLon, double minLat, double maxLat) {
        this.minLon = minLon;
        this.maxLon = maxLon;
        this.minLat = minLat;
        this.maxLat = maxLat;
    }

    public double maxLat;
}
