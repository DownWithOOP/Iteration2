package view.utilities;

import javafx.scene.image.Image;

/**
 * Created by cduica on 3/5/17.
 */
public class Assets {

    /**
     * single af
     */
    private static Assets assets = new Assets();

    public static Assets getInstance() {
        return assets;
    }

    private Assets() {
        if(init()){
            System.out.println("Assets loaded successfully");
        } else {
            System.out.println("Failed to load assets");
        }
    }

    /**
     * Image assets
     */

    public Image CATFOOD;
    public Image CRYSTAL;
    public Image RESEARCH;

    public Image DIRT;
    public Image CRATER;
    public Image GRASS;
    public Image WATER;

    public Image RED_CROSS;
    public Image SKULL;

    public Image CAPITAL;

    public Image COLONIST;
    public Image EXPLORER;
    public Image MELEE;
    public Image RANGED;

    public Image SELECT;
    public Image DARKNESS;

    public Image MINIMAPBORDER;
    public Image SMALLPURPLE;

    public boolean init(){

        try {

            //resources
            CATFOOD = new Image("/images/resources/catfood.png");
            CRYSTAL = new Image("/images/resources/crystal.png");
            RESEARCH = new Image("/images/resources/research.png");

            //terrain
            DIRT = new Image("/images/terrain/dirt.png");
            CRATER = new Image("/images/terrain/mountain.png");
            GRASS = new Image("/images/terrain/grass.png");
            WATER = new Image("/images/terrain/water.png");

            //decals
            RED_CROSS = new Image("/images/decals/redcross.png");
            SKULL = new Image("/images/decals/skull.png");

            //structures
            CAPITAL = new Image("/images/entities/capital.png");

            //units
            COLONIST = new Image("/images/entities/colonist.png");
            EXPLORER = new Image("/images/entities/explorer.png");
            MELEE = new Image("/images/entities/melee.png");
            RANGED = new Image("/images/entities/ranged.png");

            //misc
            SELECT = new Image("images/misc/select.png");
            DARKNESS = new Image("images/misc/darkness.png");
            MINIMAPBORDER = new Image("images/misc/minMap.png");
            SMALLPURPLE = new Image("images/misc/smallPurple.png");

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
