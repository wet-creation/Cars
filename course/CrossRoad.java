package OOP.course;

import java.util.ArrayList;

public class CrossRoad extends AbstractRoad{

    protected ArrayList<AbstractRoad> nextRoads = new ArrayList<>(3);
public  CrossRoad(String name) {
        this.name = name;
}
 public  CrossRoad( ArrayList<AbstractRoad> nextRoads, String name) {
     this.name = name;
     this.nextRoads = nextRoads;
     for (int i = 0; i < nextRoads.size(); i++) {
         this.nextRoads.get(i).hasCrossRoads = true;
         this.nextRoads.get(i).lastRoad = this;
     }
 }


    public void setNextRoads(AbstractRoad nextRoad) {
       super.nextRoads.add(nextRoad);
       nextRoad.lastRoad = this;

    }
}
