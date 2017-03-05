package utilities;

import model.Player;
import model.entities.structure.*;
import model.entities.unit.*;

/**
 * Created by Jonathen on 2/24/2017.
 */
public abstract class Renderer {

    abstract void render(final Player player);

    abstract void render(final Army army);

    abstract void render(final Colonist colonist);

    abstract void render(final Explorer explorer);

    abstract void render(final Melee melee);

    abstract void render(final Ranged ranged);

    abstract void render(final Worker worker);

    abstract void render(final Capital capital);

    abstract void render(final Farm farm);

    abstract void render(final Fort fort);

    abstract void render(final Mine mine);

    abstract void render(final ObservationTower observationTower);

    abstract void render(final PowerPlant powerPlant);

    abstract void render(final University university);
}
