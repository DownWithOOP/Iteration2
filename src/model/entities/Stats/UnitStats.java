package model.entities.Stats;

/**
 * Created by LesliesLaptop on 2/25/17.
 */
public class UnitStats extends Stats {

    public UnitStats(int offensiveDamage, int defensiveDamage,
                     int armor, int movement, int health,
                     int upkeep, int visionRadius, int range) {
        super(armor, health, upkeep, visionRadius);
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
        this.statsMap.put(StatsType.DEFENSIVE_DAMAGE, defensiveDamage);
        this.statsMap.put(StatsType.MOVEMENT, movement);
        this.statsMap.put(StatsType.RANGE, range);
    }

    public int getOffensiveDamage() {
        return statsMap.get(StatsType.OFFENSIVE_DAMAGE);
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
    }

    public int getDefensiveDamage() {
        return statsMap.get(StatsType.DEFENSIVE_DAMAGE);
    }

    public void setDefensiveDamage (int defensiveDamage) {
        this.statsMap.put(StatsType.DEFENSIVE_DAMAGE, defensiveDamage);
    }

    public int getRange(){
        return statsMap.get(StatsType.RANGE);
    }

    public void setRange(int range){
        this.statsMap.put(StatsType.RANGE, range);
    }

    public void setMovement(int movement) {
        this.statsMap.put(StatsType.MOVEMENT, movement);
    }

    public int getMovement() {
        return statsMap.get(StatsType.MOVEMENT);
    }

    public UnitStats clone(){
        UnitStats temp= new UnitStats(getOffensiveDamage(),getDefensiveDamage(),getArmor(),getMovement(),getHealth(),getUpkeep(),getVisionRadius(),getRange());
        return temp;
    }
}
