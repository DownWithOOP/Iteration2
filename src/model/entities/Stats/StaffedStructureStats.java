package model.entities.Stats;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class StaffedStructureStats extends StructureStats {

    public StaffedStructureStats(int productionRates,
                                 int defensiveDamage, int armor, int health, int upkeep, int visionRadius) {
        super(defensiveDamage, armor, health, upkeep, visionRadius);
        this.statsMap.put(StatsType.PRODUCTION_RATES, productionRates);
    }

    public void setProductionRates(int productionRates) {
        this.statsMap.put(StatsType.PRODUCTION_RATES, productionRates);
    }
    public int getProductionRates() {
        return this.statsMap.get(StatsType.PRODUCTION_RATES);
    }

    public StaffedStructureStats clone() {
        StaffedStructureStats temp= new StaffedStructureStats(getProductionRates(), getDefensiveDamage(),
                                                              getArmor(), getHealth(), getUpkeep(), getVisionRadius());

        return temp;
    }
}
