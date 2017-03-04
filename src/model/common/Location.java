package model.common;

/**
 * Created by cduica on 2/25/17.
 */
public class Location {

    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void moveNorth(){
        y--;
    }
    public void moveSouth(){
        y++;
    }
    public void moveWest(){
        x--;
    }
    public void moveEast(){
        x++;
    }

    public boolean equals(Location location){
        int xCoord=location.getX();
        int yCoord=location.getY();

        return (x==xCoord && y==yCoord);

    }

}
