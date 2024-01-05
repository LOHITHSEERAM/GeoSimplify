package org.example.StrategyInterfaces;

import org.example.pojo.TripMetaData;

public interface PricingStrategy {

    double calculatePrice(TripMetaData metadata);

}
