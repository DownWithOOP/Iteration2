package model.entities.Stats;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class FighterUnitStats extends UnitStats {
    private int maxHealth = 100;

    public FighterUnitStats(int offensiveDamage, int defensiveDamage,
                            int armor, int health, int range,
                            int movement, int upkeep, int visionRadius) {
        super(movement, upkeep, visionRadius);
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
        this.statsMap.put(StatsType.DEFENSIVE_DAMAGE, defensiveDamage);
        this.statsMap.put(StatsType.ARMOR, armor);
        this.statsMap.put(StatsType.HEALTH, health);
        this.statsMap.put(StatsType.RANGE, range);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.statsMap.put(StatsType.OFFENSIVE_DAMAGE, offensiveDamage);
    }
    public int getOffensiveDamage() {
        return this.statsMap.get(StatsType.OFFENSIVE_DAMAGE);
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

    public void setRange(int range) {
        this.statsMap.put(StatsType.RANGE, range);
    }
    public int getRange() {
        return this.statsMap.get(StatsType.RANGE);
    }

    public FighterUnitStats clone(){
        FighterUnitStats temp= new FighterUnitStats(getOffensiveDamage(), getDefensiveDamage(),
                                                    getArmor(), getHealth(), getRange(),
                                                    getMovement(), getUpkeep(), getVisionRadius());
        return temp;
    }

}
