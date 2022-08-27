package OOP.course;

public interface Subject {
     void registryObserver(Observer observer);
     void removeObserver(Observer observer);
     void notifyObserver();
}
