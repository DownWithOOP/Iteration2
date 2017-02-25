package model.entities.Stats;

import java.util.HashMap;

/**
 * Created by LesliesLaptop on 2/25/17.
 */
public class Stats {
    // create a map storing all the stats by their stats type and numerical value
    protected java.util.Map<StatsType, Integer> statsMap = new HashMap<>();

    public Stats(int armor, int health, int upkeep, int visionRadius) {
        statsMap.put(StatsType.ARMOR, armor);
        statsMap.put(StatsType.HEALTH, health);
        statsMap.put(StatsType.DEFAULT_UPKEEP, upkeep);
        statsMap.put(StatsType.UPKEEP, upkeep);
        statsMap.put(StatsType.VISION_RADIUS, visionRadius);
    }

    public void setArmor(int armor) {
        statsMap.put(StatsType.ARMOR, armor);
    }

    public int getArmor() {
        return statsMap.get(StatsType.ARMOR);
    }

    public void setHealth(int health) {
        statsMap.put(StatsType.HEALTH, health);
    }

    public int getHealth() {
        return statsMap.get(StatsType.HEALTH);
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

}
