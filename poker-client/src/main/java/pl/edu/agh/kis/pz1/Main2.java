package pl.edu.agh.kis.pz1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import pl.edu.agh.kis.pz1.util.TextUtils;
public class Main2 {
    public static void main( String[] args ) throws IOException {
        ClientThread clientThread = new ClientThread(new Socket(TextUtils.HOST, TextUtils.PORT));
        String serverMessageType, serverMessage, clientMessage;
        Scanner scanner = new Scanner(System.in);
        while ((serverMessageType = clientThread.in.readLine()) != null) {
            serverMessage = clientThread.in.readLine();
            System.out.println("Server: " + serverMessageType + ": " + serverMessage);
            if (serverMessageType.equals("1")) {
                System.out.println("Enter your message: ");
                clientMessage = scanner.nextLine();
                if (clientMessage != null) {
                    clientThread.out.println(clientMessage);
                }
            }
        }










//        CompareHands compareHands = new CompareHands();

//        Player testPlayer = new Player(0,100);
//        testPlayer.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
//        testPlayer.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
//        testPlayer.addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
//        testPlayer.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
//        testPlayer.addCard(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN));
//
//        Player testPlayer2 = new Player(1,100);
//        testPlayer2.addCard(new Card(Card.Suit.SPADES, Card.Rank.ACE));
//        testPlayer2.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
//        testPlayer2.addCard(new Card(Card.Suit.SPADES, Card.Rank.KING));
//        testPlayer2.addCard(new Card(Card.Suit.HEARTS, Card.Rank.KING));
//        testPlayer2.addCard(new Card(Card.Suit.SPADES, Card.Rank.QUEEN));
//
//        Player[] playersTest = new Player[2];
//        playersTest[0] = testPlayer;
//        playersTest[1] = testPlayer2;
//        Player[] sortedPlayersTest = compareHands.sortPlayersByHash(playersTest);
//        for(int i=0;i<playersTest.length;i++){
//            System.out.println(sortedPlayersTest[i].getPlayerID()+" "+sortedPlayersTest[i].getHand()+" "+sortedPlayersTest[i].getHandHash());
//        }
//        int[] winnersTest = compareHands.getWinners(sortedPlayersTest);
//        for (int i=0;i<playersTest.length;i++){
//            System.out.println(sortedPlayersTest[i].getPlayerID()+" "+winnersTest[i]);
//        }


//        Player[] players = new Player[4];
//        Deck deck = new Deck();
//        deck.shuffle();
//        players[0] = new Player(0,100);
//        players[1] = new Player(1,100);
//        players[2] = new Player(2,100);
//        players[3] = new Player(3,100);
//
//        for(int i=0;i<5;i++){
//            for(int j=0;j<4;j++){
//                players[j].addCard(deck.draw());
//            }
//        }
//        Player[] sortedPlayers = compareHands.sortPlayersByHash(players);
//        for(int i=0;i<4;i++){
//            System.out.println(sortedPlayers[i].getPlayerID()+" "+sortedPlayers[i].getHand()+" "+sortedPlayers[i].getHandHash());
//        }
//        int[] winners = compareHands.getWinners(sortedPlayers);
//        for (int i=0;i<winners.length;i++){
//            System.out.println(sortedPlayers[i].getPlayerID()+" "+winners[i]);
//        }
//        int[] parsedWinners = compareHands.parseWinners(sortedPlayers, winners);
//        for (int i=0;i<parsedWinners.length;i++){
//            System.out.println(parsedWinners[i]);
//        }

//        float a[] = compareHands.compare(players);
//        for (int i=0;i<a.length;i++){
//            System.out.println(a[i]);
//        }

//        player.addCard(deck.draw());
//        player2.addCard(deck.draw());
//        player.addCard(deck.draw());
//        player2.addCard(deck.draw());
//        player.addCard(deck.draw());
//        player2.addCard(deck.draw());
//        player.addCard(deck.draw());
//        player2.addCard(deck.draw());
//        player.addCard(deck.draw());
//        player2.addCard(deck.draw());


//        players[0] = player;
//        players[1] = player2;

//        Player[] sortedPlayers = compareHands.sortPlayersByHash(players);

//        float a[] = compareHands.compare(players);
//        for (int i=0;i<a.length;i++){
//            System.out.println(a[i]);
//        }




//        for (int i = 0; i < 1000000; i++) {
//            Deck deck = new Deck();
//            deck.shuffle();
//            player.addCard(deck.draw());
//            player.addCard(deck.draw());
//            player.addCard(deck.draw());
//            player.addCard(deck.draw());
//            player.addCard(deck.draw());
//            int number = PokerHands.getHandType(player.getHand());
//            if (number > 7) {
//                System.out.println(player.getHand().toString()+" "+number);
//            }
//            player.clearHand();
//        }
    }
}
