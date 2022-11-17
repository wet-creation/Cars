package OOP.course;


public class Road extends AbstractRoad{

    public Road(int distance, AbstractRoad.Direction direction, String name){
        super.distance = distance;
        this.direction = direction;
        this.name = name;
    }


    public Road(int distance, AbstractRoad.Direction direction, AbstractRoad lastRoad, String name) {
        super.distance  = distance;
        this.lastRoad = lastRoad;
        lastRoad.nextRoad = this;
        this.name = name;
        if (isDirectionTrue(lastRoad, direction)){
            this.direction = direction;
        }
        else {
            incorrectDirection.addSuppressed(incorrectDirection);
        }

    }
    public Road(int distance, AbstractRoad.Direction direction, AbstractRoad lastRoad, AbstractRoad nextRoad, String name) {
        super.distance = distance;
        this.lastRoad = lastRoad;
        lastRoad.nextRoad = this;
        setNextRoad(nextRoad);
        this.name = name;
        if (isDirectionTrue(lastRoad, direction)){
            this.direction = direction;
        }
        else {
            incorrectDirection.getCause();
        }

    }

    @Override
    public void setNextRoad(AbstractRoad nextRoad) {
        if (nextRoad instanceof CrossRoad || isDirectionTrue(this,nextRoad.direction))
            this.nextRoad = nextRoad;
        else incorrectDirection.addSuppressed(incorrectDirection);
    }

}
