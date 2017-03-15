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

    public void moveNorth(){ super.setLocation(this.getX(), this.getY()+1); }
    public void moveEast(){ super.setLocation(this.getX()+1, this.getY()); }
    public void moveWest(){ super.setLocation(this.getX()-1, this.getY()); }
    public void moveSouth(){ super.setLocation(this.getX(), this.getY()-1); }

    public int getXCoord() { return x;}
    public int getYCoord() { return y;}

    public void jumpLocation(int locationX, int locationY){
        super.setLocation(locationX, locationY);
    }




//    public boolean equals(Location location){
//        int xCoord=location.getX();
//        int yCoord=location.getY();
//
//        return (x==xCoord && y==yCoord);
//
//    }

}
