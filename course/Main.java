package OOP.course;

import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {
        Road r1 = new Road(40, Road.Direction.RIGHT, "r1");
        Road r2 = new Road(30, Road.Direction.RIGHT, "r2");
        Road r3 = new Road(40, Road.Direction.DOWN, "r3");
        Road r4 = new Road(20, Road.Direction.DOWN, "r4");
        Road r5 = new Road(20, Road.Direction.LEFT,  "r5");
        Road r6 = new Road(20, Road.Direction.LEFT, "r6");
        Road r7 = new Road(20, Road.Direction.UP, "r7");
        CrossRoad crossRoad1 = new CrossRoad("cross1");
        CrossRoad crossRoad2 = new CrossRoad("cross2");
        r1.setNextRoad(crossRoad1);
        crossRoad1.setNextRoads(r3);
        crossRoad1.setNextRoads(r2);
        r2.setNextRoad(r4);
        r4.setNextRoad(r5);
        r5.setNextRoad(crossRoad2);
        r3.setNextRoad(crossRoad2);
        crossRoad2.setNextRoads(r6);
        r6.setNextRoad(r7);
        r7.setNextRoad(r1);

        TrafficLight trafficLight = TrafficLight.createOrGetTrafficLight(1000);


        Car car1 = new Car(6, r7, trafficLight,"car1");
        Car car2 = new Car(4, r7, trafficLight,"car2");
        Car car3 = new Car(6, r6, trafficLight,"car3");
        Car car4 = new Car(7, r2, trafficLight,"car4");
        Car car5 = new Car(2, r4, trafficLight,"car5");

        long time = 20;

        trafficLight.startChangingColor(time);
       car1.startCarMooving(time);
       car2.startCarMooving(time);
       car3.startCarMooving(time);
       car4.startCarMooving(time);
       car5.startCarMooving(time);
       long currentTimeMillis = System.currentTimeMillis();
       while (currentTimeMillis + time * 1000 >= System.currentTimeMillis() )
            if (currentTimeMillis + time * 1000 <= System.currentTimeMillis())
                System.out.println("Average time of waiting:" + ((
                        car1.getTimeWhenCarDontMove() + car2.getTimeWhenCarDontMove()+ car3.getTimeWhenCarDontMove()
                                + car4.getTimeWhenCarDontMove()+ car5.getTimeWhenCarDontMove())/5
                ));
       System.out.println("Time of waiting for car " + car1.getName() + " is " + car1.getTimeWhenCarDontMove());
       System.out.println("Time of waiting for car " + car2.getName() + " is " + car2.getTimeWhenCarDontMove());
       System.out.println("Time of waiting for car " + car3.getName() + " is " + car3.getTimeWhenCarDontMove());
       System.out.println("Time of waiting for car " + car4.getName() + " is " + car4.getTimeWhenCarDontMove());
       System.out.println("Time of waiting for car " + car5.getName() + " is " + car5.getTimeWhenCarDontMove());


    }


}

