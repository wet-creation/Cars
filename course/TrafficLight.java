package OOP.course;

import java.util.ArrayList;


public class TrafficLight implements Subject{
    private volatile static TrafficLight trafficLight;
    private final int timeToChangeColorMills;
    private boolean isColorGreen = true;
    private ArrayList<Observer> observers = new ArrayList<>();

    private TrafficLight(int timeToChangeColorMills){
        this.timeToChangeColorMills = timeToChangeColorMills;
    }

    public static TrafficLight createOrGetTrafficLight(int timeToChangeColorMills){
        if (trafficLight == null) {
            synchronized (TrafficLight.class) {
                if (trafficLight == null) {
                    trafficLight = new TrafficLight(timeToChangeColorMills);
                }
            }
        }
        return trafficLight;
    }

    public void registryObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0)
            observers.remove(i);
    }


    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++){
            Observer observer = observers.get(i);
            observer.update(isColorGreen);
        }
    }

    private void changingColor(){

        if (isColorGreen) {
            isColorGreen = false;
            notifyObserver();
            System.out.println("Now is red");

            }


            else {
                isColorGreen = true;
                notifyObserver();
                System.out.println("Now is green");

            }
    }
    @SuppressWarnings("deprecation")
    public void startChangingColor(long timeOfExecutingInSeconds){
        long currentTimeMillis = System.currentTimeMillis();
        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){
                    try {
                        changingColor();
                        Thread.sleep(timeToChangeColorMills); //1000 - 1 сек
                    } catch (InterruptedException ex) {
                    }
                    if (currentTimeMillis + timeOfExecutingInSeconds * 1000 <= System.currentTimeMillis())
                        Thread.currentThread().stop();
                }
            }
        });
        run.start();

    }


}

