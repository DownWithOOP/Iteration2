package model.map.tile.item;

import model.common.Location;

/**
 * Created by cduica on 2/25/17.
 */
public abstract class Item {

    private Location location;
    private ItemType itemType;

    public Item(Location location, ItemType itemType) {
        this.location = location;
        this.itemType = itemType;
    }

    public Location getLocation() {
        return location;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
