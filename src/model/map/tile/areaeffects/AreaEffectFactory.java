package model.map.tile.areaeffects;

/**
 * Created by cduica on 2/25/17.
 */
public class AreaEffectFactory {

    public AreaEffect createAreaEffect(AreaEffectType type){
        switch(type){
            case HEAL_DAMAGE:
                return new HealDamage();
            case TAKE_DAMAGE:
                return new TakeDamage();
            case INSTANT_DEATH:
                return new InstantDeath();
            default:
                return null;
        }
    }

}
