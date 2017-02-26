package model.entities.Stats;

/**
 * Created by LesliesLaptop on 2/25/17.
 */
public class StructureStats extends Stats {

    public StructureStats(int armor, int health, int upkeep,
                          int productionRates, int visionRadius) {
        super(armor, health, upkeep, visionRadius);
        this.statsMap.put(StatsType.PRODUCTION_RATES, productionRates);
    }

    public void setProductionRates(int productionRates) {
        this.statsMap.put(StatsType.PRODUCTION_RATES, productionRates);
    }

    public int getProductionRates() {
        return this.statsMap.get(StatsType.PRODUCTION_RATES);
    }
}
