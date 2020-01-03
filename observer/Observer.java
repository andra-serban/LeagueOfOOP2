package observer;

import fileio.FileSystem;

import java.io.IOException;

public interface Observer {
    void update(FileSystem fs, String mess) throws IOException;
    void setSubject(Subject s);
}
