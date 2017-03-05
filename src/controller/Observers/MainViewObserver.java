package controller.Observers;

import controller.AbstractObserver;
import model.map.Map;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MainViewObserver  extends AbstractObserver {
    private Map map;

    @Override
    public void update(Map map) {
        this.map = map;
    }

    @Override
    public Map share() {
        return this.map;
    }


}
