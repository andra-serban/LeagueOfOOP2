package observer;

import fileio.FileSystem;

import java.io.IOException;

public interface Subject {
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObservers(FileSystem fs, String message) throws IOException;
}
