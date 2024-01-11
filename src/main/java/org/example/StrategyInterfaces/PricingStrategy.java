package org.example.StrategyInterfaces;

import org.example.Trip.TripMetaData;

public interface PricingStrategy {

    double calculatePrice(TripMetaData metadata);

}
