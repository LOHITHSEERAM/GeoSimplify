package org.example.GeoData;


import org.example.pojo.Attractions;
import org.example.util.BBox;
import org.example.util.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class Node {

    Node NW;
    Node NE;

    Node SW;

    Node SE;

    boolean isLeaf;

    BBox bbox = new BBox(0,0,0,0);

    List<Attractions> list;

    public Node(boolean isLeaf, BBox bbo) {
        this.isLeaf = isLeaf;
        this.bbox.minLat = bbo.minLat;
        this.bbox.maxLat = bbo.maxLat;
        this.bbox.minLon = bbo.minLon;
        this.bbox.maxLon = bbo.maxLon;
        this.list = new ArrayList<>();
    }
}
public class QuadTree {


    int capacity;

    int parts;

    double deltaX;

    double deltaY;

    Node root;
    QuadTree(int capacity, BBox bbo, int parts) {

        this.capacity = capacity;
        root = new Node(true,bbo);
        deltaY = (root.bbox.maxLat - root.bbox.minLat) / parts;
        deltaX = (root.bbox.maxLon - root.bbox.minLon) / parts;
    }

    public Node insert(Node node, Attractions at) {
        if(node.isLeaf) {
            if(node.list.size()<capacity)
                node.list.add(at);
            else {
                node.isLeaf = false;
                Node child = getNode(node,at);
                child.list.add(at);
                for(int i = 0; i< node.list.size(); i++) {
                    Node c = getNode(node, node.list.get(i));
                    c.list.add(node.list.get(i));
                }
            }
        }
        else {
            Node child = getNode(node,at);
            insert(child,at);
        }
        return null;
    }

    public Node getNode(Node parent, Attractions at) {

        double latMid = (parent.bbox.minLat + parent.bbox.maxLat)/2;
        double lonMid = (parent.bbox.maxLon + parent.bbox.minLon)/2;


        if(at.getLongitude()>=lonMid && at.getLatitude()>=latMid) {

            if(parent.NE==null)
                parent.NE = new Node(true, new BBox(lonMid, parent.bbox.maxLon,latMid, parent.bbox.maxLat));

            return parent.NE;
        }
        else if(at.getLongitude()>=lonMid && at.getLatitude()<latMid) {

            if(parent.SE==null)
                parent.SE  = new Node(true,new BBox(lonMid, parent.bbox.maxLon, parent.bbox.minLat,latMid));

            return parent.SE;
        }
        else if(at.getLongitude()<lonMid && at.getLatitude()>=latMid) {

            if(parent.NW==null)
                parent.NW = new Node(true,new BBox(parent.bbox.minLon,lonMid,latMid, parent.bbox.maxLat));

            return parent.NW;
        }
        else {

            if(parent.SW==null)
                parent.SW = new Node(true, new BBox(parent.bbox.minLon,lonMid, parent.bbox.minLat,latMid));

            return parent.SW;
        }
    }

    public Node insertAttraction(@NotNull Attractions at) {
        return insert(root,at);
    }

    public List<Attractions> getNearByAttraction(Node node,Attractions at, int R) {

       if(node.isLeaf)
           return node.list;

       return getNearByAttraction(getNode(node,at),at,R);
    }

    public List<Attractions> getNearByAttractions(Attractions at, int R) {

        return getNearByAttraction(root,at,R);
    }
    public static double getHaversineDistance(double fromLat, double fromLon, double toLat, double toLon) {
            double dLat = Math.toRadians(toLat - fromLat);
            double dLon = Math.toRadians(toLon - fromLon);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(fromLat)) * Math.cos(Math.toRadians(toLat)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
            return Constants.EARTH_RADIUS * 2 * Math.asin(Math.sqrt(a));
        }
    public void nearestNeighbourSearch(Attractions at, Node node){


    }

    public boolean intersect(Attractions at , Node node, int R) {

        double x1 = node.bbox.minLat;
        double y1 = node.bbox.minLon;

        double x2 = node.bbox.maxLat;
        double y2 = node.bbox.maxLon;

        double xCenter = at.getLatitude();
        double yCenter = at.getLongitude();

        //find nearest point from the Node and the Point

        double Xn = Math.max(x1, Math.min(xCenter, x2));
        double Yn = Math.max(y1, Math.min(yCenter, y2));

        // Find the distance between the
        // nearest point and the center
        // of the circle
        // Distance between 2 points,
        // (x1, y1) & (x2, y2) in
        // 2D Euclidean space is
        // ((x1-x2)**2 + (y1-y2)**2)**0.5

        if(getHaversineDistance(Xn,Yn,at.getLatitude(),at.getLongitude())<=R)
            return true;

        return false;
    }
}
