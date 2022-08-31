package OOP.course;


import java.util.ArrayList;

public class AbstractRoad {
    protected int distance = 5;

    protected boolean hasCrossRoads;
    protected String name;
    protected AbstractRoad nextRoad;
    protected ArrayList<AbstractRoad> nextRoads = new ArrayList<>(3);

    protected AbstractRoad lastRoad;
    protected boolean hasCar;
    protected ArrayList<Car> cars = new ArrayList<>(3);
    protected final Throwable incorrectDirection = new Throwable("The direction was set incorrect for " + this.getName());
    protected Direction direction;

    public enum Direction{
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

   protected boolean isDirectionTrue(AbstractRoad lastRoad, Direction direction){
        if (lastRoad.direction.equals(Direction.UP) && direction.equals(Direction.DOWN))
            return false;
        else if (lastRoad.direction.equals(Direction.DOWN) && direction.equals(Direction.UP) ) {
            return false;
        } else if (lastRoad.direction.equals(Direction.RIGHT) && direction.equals(Direction.LEFT)) {
            return false;
        } else return !lastRoad.direction.equals(Direction.LEFT) || !direction.equals(Direction.RIGHT);
    }
    void addCar(Car car){
        this.cars.add(car);
        hasCar = true;
    }
    void removeCar(Car car){
        this.cars.remove(cars.indexOf(car));
        hasCar = false;
    }

    public Direction getDirection() {
        return direction;
    }

    public AbstractRoad getNextRoad() {
        return nextRoad;
    }

    public String getName() {
        return name;
    }


    public boolean isHasCar() {
        return !cars.isEmpty();
    }



    public ArrayList<AbstractRoad> getNextRoads() {
        return nextRoads;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setNextRoad(AbstractRoad nextRoad) {

        this.nextRoad = nextRoad;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getDistance() {
        return distance;
    }
}
