package org.example.StrategyInterfaces;

import org.example.pojo.Driver;
import org.example.pojo.TripMetaData;

public interface DriverMatchingStrategy {

    Driver matchDriver(TripMetaData metadata);
}
