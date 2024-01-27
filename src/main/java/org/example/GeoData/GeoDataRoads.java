package org.example.GeoData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.pojo.CustomDeserializer;
import org.example.pojo.Geometry;
import org.example.pojo.Root;

import java.io.File;
import java.io.IOException;

public class GeoDataRoads {

    ObjectMapper objectMapper = new ObjectMapper();

    Root root;

    public void loadEateries() throws IOException {

        File Eateries = new File("/Users/lohithseeram/Downloads/Banglore_roads.geojson");
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
    }
}
