package org.example.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Point.class, name = "Point"),
        @JsonSubTypes.Type(value = Polygon.class, name = "Polygon"),
        @JsonSubTypes.Type(value = LineString.class, name = "LineString")

})
public abstract class Geometry{



}
