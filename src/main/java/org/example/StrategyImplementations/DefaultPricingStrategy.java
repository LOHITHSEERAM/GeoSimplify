package org.example.StrategyImplementations;

import org.example.StrategyInterfaces.PricingStrategy;
import org.example.Trip.TripMetaData;

public class DefaultPricingStrategy implements PricingStrategy{

    @Override
    public double calculatePrice(TripMetaData metadata) {
        return 0;
    }
}
