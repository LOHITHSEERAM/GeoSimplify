package org.example.GeoData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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

        File Eateries = new File("/Users/lohithseeram/Downloads/Banglore_cafe_restaurants.geojson");
        {
            try {
                SimpleModule module = new SimpleModule();
                module.addDeserializer(Geometry.class, new CustomDeserializer());
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(module);
                root = mapper.readValue(Eateries, Root.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<Feature> features = root.getFeatures();
        for(int i=0;i< features.size();i++) {
            Attractions eatery = new Attractions();
            eatery.setId(features.get(i).id);

            if (features.get(i).getGeometry() instanceof Point) {
                Point pointGeometry = (Point) features.get(i).getGeometry();
                eatery.setLongitude(pointGeometry.getCoordinates().get(0));
                eatery.setLatitude(pointGeometry.getCoordinates().get(1));
            }
            else if(features.get(i).getGeometry() instanceof Polygon){
                Polygon polygonGeometry = (Polygon) features.get(i).getGeometry();
                eatery.setLongitude(polygonGeometry.getCoordinates().get(0).get(0).get(0));
                eatery.setLatitude(polygonGeometry.getCoordinates().get(0).get(0).get(1));
            }
            quadTree.insertAttraction(eatery);
        }

        Attractions eat = new Attractions();
        eat.setLongitude(77.6021393);
        eat.setLatitude(12.9752988);
       System.out.println(quadTree.getNearByAttractions(eat,2).get(2).getId());


    }
}
