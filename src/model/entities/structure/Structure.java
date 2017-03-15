package model.entities.structure;

import controller.CommandRelay;
import model.entities.Entity;
import model.entities.EntityType;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import utilities.id.CustomID;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Structure extends Entity {

    /**
     * @param playerId
     */

    public Structure(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        //TODO: addAllCommands to be placed here after all the structure actions are defined
    }

    protected Stats setEntityStats() {
        return new StructureStats(0,0,0,0,0);
    }

    public StructureStats getStructureStats() { return ((StructureStats)entityStats).clone();}

    public void takeDamage(int offensiveDamage) {
        System.out.println("taking damage");
        int currentHealth = getStructureStats().getHealth();
        int damageTaken = offensiveDamage - getStructureStats().getArmor();
        if (currentHealth - damageTaken <= 0) {
            //TODO: unit is dead - notify command relay
        }
        else {
            getStructureStats().setHealth(currentHealth - damageTaken);
        }
    }

}
