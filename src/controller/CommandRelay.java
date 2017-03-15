package controller;

import controller.commands.Direction;
import model.GameModel;
import model.RallyPoint;
import model.common.Location;
import model.entities.EntityId;
import model.entities.structure.Structure;
import model.entities.unit.Army;
import model.entities.unit.FighterUnit;
import model.entities.unit.Unit;
import utilities.id.CustomID;

/**
 * Created by Jonathen on 3/14/2017.
 *
 * Used by Commandables to talk to GameModel
 */
public class CommandRelay {

    private GameModel model;

    public CommandRelay(GameModel model) {
        this.model = model;
    }

    public void notifyModelOfAttack(Location location, int damage) {
        System.out.println("apply damage of " + damage);
        model.applyDamageToEntitiesByLocation(location, damage);
    }

    public void notifyModelOfUnitUpdate() {

    }

    public void notifyModelOfUnitJoiningArmy(FighterUnit fighterUnit, int armyNumber) {
        model.addFighterUnitToArmy(fighterUnit, armyNumber);
    }

    public void notifyModelOfRallyPointCreation(RallyPoint rallyPoint, int armyNumber) {
        model.addRallyPoint(rallyPoint, armyNumber);
    }

    public void notifyModelOfUnitCreation(Unit unit) {
        model.addUnit(unit);
    }

    public void notifyModelOfUnitRemoval(Unit unit) {
        model.removeUnit(unit);
    }

    public void notifyModelOfStructureCreation(Structure structure) {
        model.addStructure(structure);
    }

    public void notifyModelOfStructureRemoval(Structure structure) {
        model.removeStructure(structure);
    }

    public void updateTilePlayerId(CustomID playerId, Location location) {
        model.updateTilePlayerId(playerId, location);
    }

    public void addWorkersToArmy(Location location, EntityId armyId) {
        model.addWorkersToArmy(location, armyId);
    }
}
