package org.example.StrategyImplementations;

import org.example.StrategyInterfaces.DriverMatchingStrategy;
import org.example.pojo.Driver;
import org.example.pojo.Road;
import org.example.Trip.TripMetaData;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeastTimeBasedMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(TripMetaData metadata) {


        int v= findLeastTimetoDriver(metadata,metadata.RiderLoc,metadata.DriverLoc);
        //System.out.print(v);
        return null;
    }

    public int findLeastTimetoDriver(TripMetaData metadata, int riderLoc,int driverLoc) {

        boolean vis[] = new boolean[metadata.routes.size()];
        vis[riderLoc] = true;
        PriorityQueue<Road> heap = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o1.time-o2.time;
            }
        });
        heap.add(new Road(riderLoc,0,0));

        while(heap.size()!=0) {

            Road dl = heap.poll();
            //System.out.println(dl.v);
            for(Road r : metadata.routes.get(dl.v)) {
                    if(!vis[r.v]) {
                        if(r.v ==driverLoc) {
                            return dl.time+r.time;
                        }
                        heap.add(new Road(r.v,dl.dist+r.dist,dl.time+r.time));
                        vis[r.v] =true;
                    }
            }
        }
        return 0;
    }
}
