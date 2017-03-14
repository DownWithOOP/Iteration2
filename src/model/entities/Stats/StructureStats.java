package model.entities.Stats;

/**
 * Created by LesliesLaptop on 2/25/17.
 */
public class StructureStats extends Stats {

    public StructureStats(int defensiveDamage, int armor, int health,
                          int upkeep, int visionRadius) {
        super(upkeep, visionRadius);
        this.statsMap.put(StatsType.DEFENSIVE_DAMAGE, defensiveDamage);
        this.statsMap.put(StatsType.ARMOR, armor);
        this.statsMap.put(StatsType.HEALTH, health);
    }

    public void setDefensiveDamage(int defensiveDamage) {
        this.statsMap.put(StatsType.DEFENSIVE_DAMAGE, defensiveDamage);
    }
    public int getDefensiveDamage() {
        return this.statsMap.get(StatsType.DEFENSIVE_DAMAGE);
    }

    public void setArmor(int armor) {
        this.statsMap.put(StatsType.ARMOR, armor);
    }
    public int getArmor() {
        return this.statsMap.get(StatsType.ARMOR);
    }

    public void setHealth(int health) {
        this.statsMap.put(StatsType.HEALTH, health);
    }
    public int getHealth() {
        return this.statsMap.get(StatsType.HEALTH);
    }

    public StructureStats clone(){
        return new StructureStats(getDefensiveDamage(), getArmor(), getHealth(),
                                                getUpkeep(), getVisionRadius());
    }
}
