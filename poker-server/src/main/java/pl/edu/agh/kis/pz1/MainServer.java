package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Server class.
 */
public class MainServer {
    /**
     * Main method.
     * asks for a number of players and starts a game.
     *
     * @param args command line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main( String[] args ) throws IOException {
        Scanner scanner = new Scanner( System.in );
        System.out.println( "How many players:");
        int howManyPlayers = scanner.nextInt();
        Game game = new Game( howManyPlayers );
        game.gameRound();
    }
}
