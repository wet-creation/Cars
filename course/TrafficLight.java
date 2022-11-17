package OOP.course;

import java.util.ArrayList;


public class TrafficLight implements Subject{
    private volatile static TrafficLight trafficLight;
    private final int timeToChangeColorSeconds;
    private boolean isColorGreen = true;
    private ArrayList<Observer> observers = new ArrayList<>();

    private TrafficLight(int timeToChangeColorSeconds){
        this.timeToChangeColorSeconds = timeToChangeColorSeconds * 1000;
    }

    public static TrafficLight createOrGetTrafficLight(int timeToChangeColorSeconds){
        if (trafficLight == null) {
            synchronized (TrafficLight.class) {
                if (trafficLight == null) {
                    trafficLight = new TrafficLight(timeToChangeColorSeconds);
                }
            }
        }
        return trafficLight;
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
        Thread run = new Thread(() -> {

            while(true){
                try {
                    changingColor();
                    Thread.sleep(timeToChangeColorSeconds); //1000 - 1 сек
                } catch (InterruptedException ex) {
                }
                if (currentTimeMillis + timeOfExecutingInSeconds * 1000 <= System.currentTimeMillis())
                    Thread.currentThread().stop();
            }
        });
        run.start();

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

}

