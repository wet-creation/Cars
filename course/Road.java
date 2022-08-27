package OOP.course;


import java.util.ArrayList;

public class Road {
    private final int distance;
    private boolean hasCrossRoads;
    private String name;
    private Road nextRoad;
    private ArrayList<Road> nextRoads = new ArrayList<>(3);

    private Road lastRoad;
    private boolean hasCar;
    private ArrayList<Car> cars = new ArrayList<>(3);
    private final Throwable incorrectDirection = new Throwable("The direction was set incorrect for " + this.getName());
    private Direction direction;

    public enum Direction{
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    public Road(int distance, Direction direction, String name){
        this.distance = distance;
        this.direction = direction;
        this.name = name;
    }
   public Road(int distance,Direction direction, ArrayList<Road> nextRoads, String name) {
        this.distance = distance;
        this.name = name;
        this.direction = direction;
        this.nextRoads = nextRoads;
        for (int i = 0; i < nextRoads.size(); i++) {
            this.nextRoads.get(i).hasCrossRoads = true;
            this.nextRoads.get(i).lastRoad = this;
        }


    }
    public Road(int distance, Direction direction, Road lastRoad, String name) {
        this.distance = distance;
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
    public Road(int distance, Direction direction, Road lastRoad, Road nextRoad, String name) {
        this.distance = distance;
        this.lastRoad = lastRoad;
        lastRoad.nextRoad = this;
        this.nextRoad = nextRoad;
        this.name = name;
        if (isDirectionTrue(lastRoad, direction)){
            this.direction = direction;
        }
        else {
            incorrectDirection.getCause();
        }

    }
    private boolean isDirectionTrue(Road lastRoad, Direction direction){
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

    public Road getNextRoad() {
        return nextRoad;
    }

    public String getName() {
        return name;
    }

    public boolean isCrossRoads() {
        return hasCrossRoads;
    }

    public boolean isNextRoadsOrRoad(){
        return !nextRoads.isEmpty();
    }

    public boolean isHasCar() {
        return !cars.isEmpty();
    }

    public int getDistance() {
        return distance;
    }

    public ArrayList<Road> getNextRoads() {
        return nextRoads;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setNextRoad(Road nextRoad) {
        this.nextRoad = nextRoad;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setNextRoads(ArrayList<Road> nextRoads) {
        this.nextRoads = nextRoads;
        for (int i = 0; i < nextRoads.size(); i++) {
            this.nextRoads.get(i).hasCrossRoads = true;
            this.nextRoads.get(i).lastRoad = this;
        }
    }
}
