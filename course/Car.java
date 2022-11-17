package OOP.course;
@SuppressWarnings("neverUse")
public class Car  implements Observer{
    private final int SPEED;
    private int timeWhenCarDontMove;
    private int passingRoadTime = 0;

    private  boolean isLigtIsGreen = true;
    private AbstractRoad currentRoad;
    private final String name;
    private AbstractRoad.Direction carDirection;

    public Car(int speed, AbstractRoad currentRoad, Subject trafficLight, String name){
        this.SPEED = speed;
        this.currentRoad = currentRoad;
        this.name = name;

        carDirection = currentRoad.getDirection();
        trafficLight.registryObserver(this);
    }

    private void carMooving() {
         if (!currentRoad.hasCar()) {
            currentRoad.addCar(this);
            if (!isLigtIsGreen && currentRoad instanceof CrossRoad) {
                timeWhenCarDontMove += 1;
                System.out.println("The car " + this.name + " jammed on crossroad " + currentRoad.getName());
            } else {
                passingRoadTime += SPEED;
                System.out.println("The car " + this.name + " driving on the road " + currentRoad.getName());
                if (passingRoadTime >= currentRoad.getDistance()) {
                    if (currentRoad instanceof CrossRoad)
                        System.out.println("The car " + this.name + " passed the crossroad " + currentRoad.getName());
                    else
                     System.out.println("The car " + this.name + " passed the road " + currentRoad.getName());
                    currentRoad.removeCar(this);
                    if (currentRoad instanceof CrossRoad)
                        currentRoad = currentRoad.getNextRoads().get((int) (Math.random() * currentRoad.getNextRoads().size()));

                    else
                        currentRoad = currentRoad.getNextRoad();
                    passingRoadTime = 0;

                }
            }
        } else {
            if (!currentRoad.getCars().contains(this))
                currentRoad.addCar(this);
            if (!isLigtIsGreen && currentRoad instanceof CrossRoad) {
                timeWhenCarDontMove += 1;
                System.out.println("The car " + this.name + " jammed on crossroad " + currentRoad.getName());
            } else {
                if (currentRoad.getCars().size() > 1 && currentRoad.getCars().indexOf(this) != 0) {
                    Car anotherCar = currentRoad.getCars().get(currentRoad.getCars().indexOf(this) - 1);
                    if (currentRoad.getCars().get(currentRoad.getCars().indexOf(this)).SPEED > currentRoad.getCars().get(currentRoad.getCars().indexOf(anotherCar)).SPEED) {
                        if (passingRoadTime + SPEED >= anotherCar.passingRoadTime + anotherCar.SPEED || (anotherCar.passingRoadTime + anotherCar.SPEED) >= currentRoad.getDistance() ) {
                            System.out.println("The car " + this.name + " tries to overtaking " + anotherCar.name + " on the road " + currentRoad.name);

                            if (anotherCar.SPEED < SPEED - anotherCar.SPEED) {
                                passingRoadTime += passingRoadTime + SPEED - (anotherCar.passingRoadTime + anotherCar.SPEED) - 1;
                                if (passingRoadTime > anotherCar.passingRoadTime + anotherCar.SPEED)
                                    passingRoadTime -= anotherCar.passingRoadTime + anotherCar.SPEED;
                            }
                            else
                                passingRoadTime += passingRoadTime + SPEED - anotherCar.passingRoadTime - 1;
                            if (passingRoadTime > anotherCar.passingRoadTime + anotherCar.SPEED)
                                passingRoadTime -= anotherCar.passingRoadTime + anotherCar.SPEED;
                        } else
                            passingRoadTime += SPEED;
                    } else {
                        passingRoadTime += SPEED;
                    }
                }
                else {
                    passingRoadTime += SPEED;
                }
                System.out.println("The car " + this.name + " driving on the road " + currentRoad.getName());
                if (passingRoadTime >= currentRoad.getDistance()) {
                    if (currentRoad instanceof CrossRoad)
                        System.out.println("The car " + this.name + " passed the crossroad " + currentRoad.getName());
                    else
                        System.out.println("The car " + this.name + " passed the road " + currentRoad.getName());
                    currentRoad.removeCar(this);
                    if (currentRoad instanceof CrossRoad)
                        currentRoad = currentRoad.getNextRoads().get((int) (Math.random() * currentRoad.getNextRoads().size()));

                    else
                        currentRoad = currentRoad.getNextRoad();
                    passingRoadTime = 0;

                }

            }
        }
    }


    @SuppressWarnings("all")
    public void startCarMooving(long timeOfExecutingInSeconds){
        long currentTimeMillis = System.currentTimeMillis();
        Thread run = new Thread(() -> {
            while(true){
                try {
                    carMooving();
                    Thread.sleep(100); //1000 - 1 сек

                }
                catch (InterruptedException ex) {
                }
              if (currentTimeMillis + timeOfExecutingInSeconds * 1000 <= System.currentTimeMillis())
                  Thread.currentThread().stop();
            }
        });
        run.start();


    }
    public int getTimeWhenCarDontMove() {
        return timeWhenCarDontMove;
    }

    public String getName() {
        return name;
    }


    @Override
    public void update(boolean isLigtIsGreen) {
        this.isLigtIsGreen = isLigtIsGreen;
    }

}
