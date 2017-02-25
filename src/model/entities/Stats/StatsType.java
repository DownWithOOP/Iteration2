package model.entities.Stats;

/**
 * Created by LesliesLaptop on 2/25/17.
 */
public enum StatsType {
    OFFENSIVE_DAMAGE, // damage dealt when attacking
    DEFENSIVE_DAMAGE, // damage dealt when fending off an attack
    ARMOR,            // absorbs a fixed amount of damage
    HEALTH,           // when reaches zero, the unit/structure is destroyed and all its workers killed
    UPKEEP,           // resources required to keep structure at full health; lack of required resources will adversely affect the health of a structure
    DEFAULT_UPKEEP,   // the initial upkeep when an entity is initialized (used to keep track of original upkeep when power up/down occurs)
    VISION_RADIUS,    // how many tiles ahead that the entity can see on the map
    MOVEMENT,         // the maximum distance a unit may move in one tick
    RANGE,            // the unit/structure's attack radius
    PRODUCTION_RATES; // the # of turns required to produce a unit of a specific type
}
