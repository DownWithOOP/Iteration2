package model.RenderInformation;

import model.entities.EntityId;

import java.util.ArrayList;

/**
 * Created by Jonathen on 3/14/2017.
 */
public class ArmyRenderObject {

    ArrayList<UnitRenderObject> battleGroup;
    ArrayList<UnitRenderObject> reinforcements;
    private EntityId id;

    public ArmyRenderObject(EntityId armyId) {
        id = armyId;
        battleGroup = new ArrayList<>();
        reinforcements = new ArrayList<>();
    }

    public void addUnitToBattleGroup(UnitRenderObject unit) {
        battleGroup.add(unit);
    }

    public void addUnitToReinforcements(UnitRenderObject unit) {
        reinforcements.add(unit);
    }

    public ArrayList<UnitRenderObject> getBattleGroup() {
        System.out.println("army render object bg " + battleGroup);
        return battleGroup;
    }

    public ArrayList<UnitRenderObject> getReinforcements() {
        System.out.println("army render object rein " + reinforcements);
        return reinforcements;
    }

    public EntityId getId() {
        return id;
    }
}
