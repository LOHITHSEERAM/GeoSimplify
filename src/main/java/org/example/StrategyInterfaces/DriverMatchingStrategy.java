package org.example.StrategyInterfaces;

import org.example.pojo.Driver;
import org.example.Trip.TripMetaData;

public interface DriverMatchingStrategy {

    Driver matchDriver(TripMetaData metadata);
}
