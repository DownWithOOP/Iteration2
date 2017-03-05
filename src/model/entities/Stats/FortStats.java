package model.entities.Stats;

/**
 * Created by LesliesLaptop on 2/25/17.
 */

// Forts are the only structure that can attack so they have offensive and defensive stats to deal with
public class FortStats extends StructureStats {

    public FortStats(int offensiveDamage, int defensiveDamage,
                     int armor, int health, int upkeep,
                     int productionRates, int visionRadius) {
        super(armor, health, upkeep, productionRates, visionRadius);
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
        this.statsMap.put(StatsType.DEFENSIVE_DAMAGE, defensiveDamage);
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
    }

    public int getOffensiveDamage() {
        return statsMap.get(StatsType.OFFENSIVE_DAMAGE);
    }

    public void setDefensiveDamage(int defensiveDamage) {
        this.statsMap.put(StatsType.DEFENSIVE_DAMAGE, defensiveDamage);
    }

    public int getDefensiveDamage() {
        return statsMap.get(StatsType.DEFENSIVE_DAMAGE);
    }
}
