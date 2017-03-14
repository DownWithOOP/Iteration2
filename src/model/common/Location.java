package model.common;

import controller.commands.Direction;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * Created by cduica on 2/25/17.
 */
public class Location extends Point {

    public Location(int x, int y) {
        super(x, y);
    }

    public Location move(Direction direction) {
        switch (direction) {
            case NORTH_EAST:
                moveNorth();
                moveEast();
                break;
            case NORTH:
                moveNorth();
                break;
            case NORTH_WEST:
                moveNorth();
                moveWest();
                break;
            case SOUTH_EAST:
                moveSouth();
                moveEast();
                break;
            case SOUTH:
                moveSouth();
                break;
            case SOUTH_WEST:
                moveSouth();
                moveWest();
                break;
        }
        return this;
    }

    public void moveNorth(){ super.setLocation(this.getX(), this.getY()-1); }
    public void moveEast(){ super.setLocation(this.getX()+1, this.getY()); }
    public void moveWest(){ super.setLocation(this.getX()-1, this.getY()); }
    public void moveSouth(){ super.setLocation(this.getX(), this.getY()+1); }

    public int getXCoord() { return x;}
    public int getYCoord() { return y;}


//    public boolean equals(Location location){
//        int xCoord=location.getX();
//        int yCoord=location.getY();
//
//        return (x==xCoord && y==yCoord);
//
//    }

}
