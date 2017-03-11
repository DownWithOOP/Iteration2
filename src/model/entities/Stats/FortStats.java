package model.entities.Stats;

/**
 * Created by LesliesLaptop on 2/25/17.
 */

// Forts are the only structure that can attack so they have offensive stats to deal with
public class FortStats extends StaffedStructureStats {

    public FortStats(int offensiveDamage, int range,
                     int productionRates, int defensiveDamage, int armor, int health, int upkeep, int visionRadius) {
        super(productionRates, defensiveDamage, armor, health, upkeep, visionRadius);
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
        this.statsMap.put(StatsType.RANGE, range);
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
    }
    public int getOffensiveDamage() {
        return statsMap.get(StatsType.OFFENSIVE_DAMAGE);
    }

    public void setRange(int range) {
        this.statsMap.put(StatsType.RANGE, range);
    }
    public int getRange() {
        return statsMap.get(StatsType.RANGE);
    }

    public FortStats clone() {
        FortStats temp= new FortStats(getOffensiveDamage(), getRange(), getProductionRates(), getDefensiveDamage(),
                getArmor(), getHealth(), getUpkeep(), getVisionRadius());

        return temp;
    }
}
