package org.example.pojo;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.*;

import java.io.IOException;


public class CustomDeserializer extends StdDeserializer<Geometry> {



    public CustomDeserializer() {
        super(Geometry.class);
    }

        @Override
        public Geometry deserialize (JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.has("type")) {
            String type = node.get("type").asText();
            if ("TypeA".equals(type)) {
                return jp.getCodec().treeToValue(node, Point.class);
            } else if ("TypeB".equals(type)) {
                return jp.getCodec().treeToValue(node, Polygon.class);
            }
        }
        throw new RuntimeException("Unrecognized type");
    }
    }
