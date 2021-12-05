package pl.edu.agh.kis.pz1.util;

/**
 * Class representing configuration of the application.
 */
public class Config {
    public static final int PORT = 8080;
    public static final String HOST = "localhost";
    public static final int ANTE = 1;
    public static final int HAND_SIZE = 5;
    public static final int STARTING_BALANCE = 100;

    private Config() {
        throw new IllegalStateException("Utility class");
    }
}
