package model.entities.Stats;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by LesliesLaptop on 2/25/17.
 */
public class Stats {
    // create a map storing all the stats by their stats type and numerical value
    protected java.util.Map<StatsType, Integer> statsMap = new HashMap<>();

    public Stats(int upkeep, int visionRadius) {
        statsMap.put(StatsType.DEFAULT_UPKEEP, upkeep);
        statsMap.put(StatsType.UPKEEP, upkeep);
        statsMap.put(StatsType.VISION_RADIUS, visionRadius);
    }

    public void setUpkeep(int upkeep) {
        statsMap.put(StatsType.UPKEEP, upkeep);
    }

    public int getUpkeep() {
        return statsMap.get(StatsType.UPKEEP);
    }

    public int getDefaultUpkeep() {
        return statsMap.get(StatsType.DEFAULT_UPKEEP);
    }

    public int getVisionRadius() {
        return statsMap.get(StatsType.VISION_RADIUS);
    }

    public void setVisionRadius(int visionRadius) {
        statsMap.put(StatsType.VISION_RADIUS, visionRadius);
    }

    public int getSize() {return statsMap.size();}

    public java.util.Map<StatsType, Integer> getStatsMap() { return statsMap; }

    public String getStatsString() {
        Iterator it = statsMap.entrySet().iterator();
        String statsString = "";
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            statsString += "\n" + pair.getKey().toString() + ": " + pair.getValue();
        }
        return statsString;
    }

}
