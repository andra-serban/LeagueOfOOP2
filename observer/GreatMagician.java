package observer;
import java.io.IOException;
import fileio.FileSystem;

public final class GreatMagician implements Observer {
    private static GreatMagician instance = null;
    private String message;
    private Subject subject;

    private GreatMagician() {
    }

    @Override
    public void update(final FileSystem fs, final String mess) throws IOException {
        this.message = mess;
        fs.writeWord(mess);
        fs.writeNewLine();
    }

    @Override
    public void setSubject(final Subject s) {
        this.subject = s;
    }

    public static GreatMagician getInstance() {
        if (instance == null) {
            instance = new GreatMagician();
        }
        return instance;
    }
}
