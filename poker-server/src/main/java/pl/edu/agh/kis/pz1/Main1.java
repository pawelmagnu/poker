package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.util.Scanner;

public class Main1 {
    public static void main( String[] args ) throws IOException {
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Podaj ilosc graczy:");
        int players = scanner.nextInt();
        Game game = new Game( players );
        game.gameRound();
    }
}
