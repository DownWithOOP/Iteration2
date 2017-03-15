package model.entities.Tech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class TechTree {

    private Map<Tech, ArrayList<Tech> > techTree;
    private Tech researchingTech;
    private int numTechsUnlocked;

    public TechTree()
    {
        techTree = new HashMap<Tech, ArrayList<Tech> >();
        researchingTech = null;
        numTechsUnlocked = 0;
    }

    // This adds a Tech to the tech tree
    // dependentTechs come in as an array
    public void addTech(Tech technology, Tech ... dependentTechs)
    {
        ArrayList<Tech> techs = new ArrayList<Tech>();
        for(Tech t : dependentTechs)
        {
            techs.add(t);
        }
        techTree.put(technology, techs);
    }

    public boolean researchNewTech(String techName)
    {
        Iterator<Entry<Tech, ArrayList<Tech>>> map_it = techTree.entrySet().iterator();
        boolean selectedNewTech = true;
        while(map_it.hasNext())
        {
            Map.Entry<Tech, ArrayList<Tech> > pairs
                    = (Map.Entry<Tech, ArrayList<Tech> >)map_it.next();
            if(pairs.getKey().getTechName().equals(techName))
            {
                if(!pairs.getKey().isLocked())
                {
                    return false;
                }
                for(Tech t : pairs.getValue() )
                {
                    if(t.isLocked())
                    {
                        selectedNewTech = false;
                        return selectedNewTech;
                    }
                }
                researchingTech = pairs.getKey();
                break;
            }
        }
        return selectedNewTech;
    }

    public boolean isTechUnlocked(String techName)
    {
        Iterator<Entry<Tech, ArrayList<Tech>>> map_it = techTree.entrySet().iterator();
        while(map_it.hasNext())
        {
            Map.Entry<Tech, ArrayList<Tech> > pairs
                    = (Map.Entry<Tech, ArrayList<Tech> >)map_it.next();

            if(pairs.getKey().getTechName().equals(techName))
            {
                return !pairs.getKey().isLocked();
            }
        }
        return false;
    }

    public void update()
    {
        if(researchingTech != null)
        {
            researchingTech.updateTech();
            if(!researchingTech.isLocked())
            {
                researchingTech = null;
                ++numTechsUnlocked;
            }
        }
    }

    public int getNumberOfTechs()
    {
        return numTechsUnlocked;
    }

    public void handleTechResearchAction(ActionEvent actionEvent){

    }
}

    public void intitializeTechTree()
    {
        TechTree techTree = new TechTree();//create new tech tree

        Tech science = new Tech("Science", 0);//first tech
        techTree.addTech(science);

        Tech glass = new Tech("Glass", 2); //techs dependent on science
        Tech leatherworking = new Tech("Leatherworking", 2);
        Tech archery = new Tech("Archery", 2);
        Tech agriculture = new Tech("Agriculture", 2);
        Tech animalHusbandry = new Tech("Animal Husbandry",2);
        Tech wheel = new Tech("Wheel", 2);
        techTree.addTech(glass, science); //add them to the tree as dependents
        techTree.addTech(leatherworking, science);
        techTree.addTech(archery, science);
        techTree.addTech(agriculture, science);
        techTree.addTech(animalHusbandry, science);
        techTree.addTech(wheel, science);

        Tech telescopes = new Tech("Telescopes", 3); //stem from glass
        Tech binoculars = new Tech("Bionculars", 4);
        Tech scope = new Tech("Targeting Scope", 5);
        techTree.addTech(telescopes, glass);
        techTree.addTech(binoculars, telescopes);
        techTree.addTech(scope, telescopes);

        Tech metallurgy = new Tech("Metallurgy", 3);//stem from leatherworking
        Tech ironworking = new Tech("Ironworking", 3);
        Tech ironArmor = new Tech("Iron Armor", 4);
        Tech shields = new Tech("Shields", 4);
        Tech shieldArrows = new Tech("Shield Arrows", 5);
        Tech ironSwords = new Tech("Iron Swords", 4);
        Tech mefficiency = new Tech("Melee Efficiency", 3);
        techTree.addTech(metallurgy, leatherworking);
        techTree.addTech(ironworking, metallurgy);
        techTree.addTech(ironArmor, ironworking);
        techTree.addTech(shields, ironworking);
        techTree.addTech(shieldArrows, shields);
        techTree.addTech(ironArmor, ironworking);
        techTree.addTech(ironSwords, ironworking);
        techTree.addTech(mefficiency, ironSwords);

        Tech catapults = new Tech("Catapults", 3);//stem from archery
        Tech crossbows = new Tech("Crossbows", 5);
        Tech refficiency = new Tech("Ranged Efficiency", 3);
        techTree.addTech(catapults, archery);
        techTree.addTech(crossbows, archery);
        techTree.addTech(refficiency, crossbows);

        Tech workerDensity = new Tech("Worker Density", 4);//stems from Agriculture because idk
        techTree.addTech(workerDensity, agriculture);

        Tech gastronomy = new Tech("Gastronomy", 4);//stem from animal husbandry
        Tech aphrodisiac = new Tech("Aphrodisiac", 4);
        Tech superSerum = new Tech("Super Serum", 5);
        techTree.addTech(gastronomy, animalHusbandry);
        techTree.addTech(aphrodisiac, gastronomy);
        techTree.addTech(superSerum, gastronomy);

        Tech chariots = new Tech("Chariots", 3);//stem from wheel
        Tech fortification = new Tech("Fortification", 4);
        Tech walls = new Tech("Walls", 4);
        techTree.addTech(chariots, wheel, animalHusbandry);
        techTree.addTech(fortification, wheel);
        techTree.addTech(walls, fortification);
    }
