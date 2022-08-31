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

    public Car(int speed, AbstractRoad currentRoad, Subject crossRoads, String name){
        this.SPEED = speed;
        this.currentRoad = currentRoad;
        this.name = name;

        carDirection = currentRoad.getDirection();
        crossRoads.registryObserver(this);
    }
    public int getTimeWhenCarDontMove() {
        return timeWhenCarDontMove;
    }

    public String getName() {
        return name;
    }

    public int getPassingRoadTime() {
        return passingRoadTime;
    }

    @Override
    public void update(boolean isLigtIsGreen) {
        this.isLigtIsGreen = isLigtIsGreen;
    }

    private void carMooving() {
         if (!currentRoad.isHasCar()) {
            currentRoad.addCar(this);
            if (!isLigtIsGreen && currentRoad instanceof CrossRoad) {
                timeWhenCarDontMove += 1;
                System.out.println("Машина с названием " + this.name + " стоит на перекрестке " + currentRoad.getName());
            } else {
                passingRoadTime += SPEED;
                System.out.println("Машина с названием " + this.name + " едет по дороге " + currentRoad.getName());
                if (passingRoadTime >= currentRoad.getDistance()) {
                    if (currentRoad instanceof CrossRoad)
                        System.out.println("Машина с названием " + this.name + " проехала перекресток " + currentRoad.getName());
                    else
                     System.out.println("Машина с названием " + this.name + " проехала дорогу " + currentRoad.getName());
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
                System.out.println("Машина с названием " + this.name + " стоит на перекрестке " + currentRoad.getName());
            } else {
                if (currentRoad.getCars().size() > 1 && currentRoad.getCars().indexOf(this) != 0) {
                    Car anotherCar = currentRoad.getCars().get(currentRoad.getCars().indexOf(this) - 1);
                    if (currentRoad.getCars().get(currentRoad.getCars().indexOf(this)).SPEED > currentRoad.getCars().get(currentRoad.getCars().indexOf(anotherCar)).SPEED) {
                        if (passingRoadTime + SPEED >= anotherCar.passingRoadTime + anotherCar.SPEED || (anotherCar.passingRoadTime + anotherCar.SPEED) >= currentRoad.getDistance() ) {
                            System.out.println("Машина " + this.name + " пытается перегнать " + anotherCar.name + " на дороге " + currentRoad.name);

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
                System.out.println("Машина с названием " + this.name + " едет по дороге " + currentRoad.getName());
                if (passingRoadTime >= currentRoad.getDistance()) {
                    if (currentRoad instanceof CrossRoad)
                        System.out.println("Машина с названием " + this.name + " проехала перекресток " + currentRoad.getName());
                    else
                        System.out.println("Машина с названием " + this.name + " проехала дорогу " + currentRoad.getName());
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


    @SuppressWarnings("deprecation")
    public void startCarMooving(long timeOfExecutingInSeconds){
        long currentTimeMillis = System.currentTimeMillis();
        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
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
            }
        });
        run.start();


    }
}
