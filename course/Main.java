package OOP.course;

import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {

        Road r2 = new Road(30, Road.Direction.RIGHT, "r2");

        Road r4 = new Road(20, Road.Direction.DOWN, r2, "r4");

        Road r6 = new Road(20, Road.Direction.LEFT, "r6");

        ArrayList<Road> crossroad2 = new ArrayList<>();
        crossroad2.add(r6);
        Road r5 = new Road(20, Road.Direction.LEFT, crossroad2, "r5");
        Road r3 = new Road(40, Road.Direction.DOWN, crossroad2, "r3");
        ArrayList<Road> crossroad1 = new ArrayList<>();
        crossroad1.add(r2);
        crossroad1.add(r3);
        Road r1 = new Road(40, Road.Direction.RIGHT, crossroad1, "r1");
        Road r7 = new Road(20, Road.Direction.UP, r6, r1, "r7");
        r4.setNextRoad(r5);

        CrossRoads crossRoads = new CrossRoads(1000);


        Car car1 = new Car(2, r1, crossRoads,"car1");
        Car car2 = new Car(10, r7, crossRoads,"car2");
        Car car3 = new Car(3, r7, crossRoads,"car3");
        Car car4 = new Car(7, r2, crossRoads,"car4");
        Car car5 = new Car(10, r3, crossRoads,"car5");

        long time = 10;

        crossRoads.startChangingColor(time);
        car1.startCarMooving(time);
        car2.startCarMooving(time);
//        car3.startCarMooving(time);
//        car4.startCarMooving(time);
//        car5.startCarMooving(time);
        long currentTimeMillis = System.currentTimeMillis();
        while (currentTimeMillis + time * 1000 >= System.currentTimeMillis() )
            if (currentTimeMillis + time * 1000 <= System.currentTimeMillis())
                System.out.println("Average time of waiting:" + ((
                        car1.getTimeWhenCarDontMove() + car2.getTimeWhenCarDontMove()+ car3.getTimeWhenCarDontMove()
                                + car4.getTimeWhenCarDontMove()+ car5.getTimeWhenCarDontMove())/5
                ));
        System.out.println("Time of waiting for car " + car1.getName() + " is " + car1.getTimeWhenCarDontMove());
        System.out.println("Time of waiting for car " + car2.getName() + " is " + car2.getTimeWhenCarDontMove());
//        System.out.println("Time of waiting for car " + car3.getName() + " is " + car3.getTimeWhenCarDontMove());
//        System.out.println("Time of waiting for car " + car4.getName() + " is " + car4.getTimeWhenCarDontMove());
//        System.out.println("Time of waiting for car " + car5.getName() + " is " + car5.getTimeWhenCarDontMove());


    }


}

