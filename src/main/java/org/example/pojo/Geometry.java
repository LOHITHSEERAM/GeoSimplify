package org.example.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Geometry{

    @JsonProperty("type")
    public String getType() {
		 return this.type; }
    public void setType(String type) {
		 this.type = type; }
    String type;
    public abstract Coordinate getCoordinate();

    public abstract Coordinate[] getCoordinates();
}
