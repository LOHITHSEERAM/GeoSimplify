package org.example.GeoData;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pojo.*;
import org.example.util.BBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GeoDataEateries {

    ObjectMapper objectMapper = new ObjectMapper();

    QuadTree quadTree = new QuadTree(3,new BBox(-180,180,-90,90),6);
    Root root;
    public void loadEateries() throws IOException {

        File Eateries = new File("/Users/lohithseeram/Downloads/Untitled123.json");
        {
            try {
                root = objectMapper.readValue(Eateries, Root.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<Feature> features = root.getFeatures();
        for(int i=0;i< features.size();i++) {
            Attractions eatery = new Attractions();
            eatery.setId(features.get(i).id);
            eatery.setLongitude(features.get(i).geometry.getCoordinates().get(0));
            eatery.setLatitude(features.get(i).geometry.getCoordinates().get(1));
            quadTree.insertAttraction(eatery);
        }

        Attractions eat = new Attractions();
        eat.setLongitude(features.get(0).geometry.getCoordinates().get(0));
        eat.setLongitude(features.get(0).geometry.getCoordinates().get(1));
        System.out.println(quadTree.getNearByAttractions(eat,2));


    }
}
