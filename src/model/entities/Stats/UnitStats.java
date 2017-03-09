package model.entities.Stats;

/**
 * Created by LesliesLaptop on 2/25/17.
 */
public class UnitStats extends Stats {

    public UnitStats(int movement, int upkeep, int visionRadius) {
        super(upkeep, visionRadius);
        this.statsMap.put(StatsType.MOVEMENT, movement);
    }

    public void setMovement(int movement) {
        this.statsMap.put(StatsType.MOVEMENT, movement);
    }

    public int getMovement() {
        return statsMap.get(StatsType.MOVEMENT);
    }

    public UnitStats clone(){
        UnitStats temp= new UnitStats(getMovement(), getUpkeep(), getVisionRadius());
        return temp;
    }
}
