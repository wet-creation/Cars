package OOP.course;

public interface Subject {
    public void registryObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver();
}
