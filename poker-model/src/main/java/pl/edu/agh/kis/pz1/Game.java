package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

import pl.edu.agh.kis.pz1.util.TextUtils;

public class Game {
    private ClientThread[] clients;
    private int playerCount;
    private int currentPlayer;
    private int[] currentBids;
    private int totalBid;
    private int numberOfPlayers;
    private Deck deck;
    private int[] winnersThisRound;
    ServerSocket serverSocket;
//    private boolean[] playersLeft;

    public Game(int numberOfPlayers) throws IOException {
        serverSocket = new ServerSocket(TextUtils.PORT);

        playerCount = 0;
        this.numberOfPlayers = numberOfPlayers;
        clients = new ClientThread[numberOfPlayers];
        currentBids = new int[numberOfPlayers];
        currentPlayer = 0;
        totalBid = 0;

        for (int i = 0; i < numberOfPlayers; i++) {
//            Socket client = serverSocket.accept();
            clients[i] = new ClientThread(serverSocket.accept());
            clients[i].player.setPlayerID(i);
            System.out.println("nowy gracz: " + clients[i].player.getPlayerID());
            clients[i].sendMessageWOR("Hello player "+i);
        }

    }

    public void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
    }

    public void nextGame() {
        currentPlayer = 0;
        currentBids = new int[numberOfPlayers];
        totalBid = 0;
    }

    public void updateBalance(int totalBid, int[] winners) {
        List asList = Arrays.asList(winners);
        int howManyWinners = Collections.frequency(asList, 1);
        for (int i = 0; i < numberOfPlayers; i++) {
            if (winners[i] == 1) {
                clients[i].player.addToBalance(totalBid / howManyWinners);
            }
        }
    }

    public void startGame() {
        this.deck = new Deck();
        deck.shuffle();
        for (ClientThread player : clients) {
            player.player.clearHand();
            player.sendMessageWOR("NEW ROUND!!!");
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                clients[j].player.addCard(deck.draw());
            }
        }
    }

    public void getWinners(){
        CompareHands compareHands = new CompareHands();
        Player[] playersCopy = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playersCopy[i] = clients[i].player;
        }
        playersCopy = compareHands.sortPlayersByHash(playersCopy);
        int[] winners = compareHands.getWinners(playersCopy);
        winnersThisRound = compareHands.parseWinners(playersCopy, winners);
    }

    public void sendWinners(){
        for (int i = 0; i < numberOfPlayers; i++) {
            if (winnersThisRound[i] == 1) {
                clients[i].sendMessageWOR("You won this round");
                for (int j = 0; j < numberOfPlayers; j++) {
                    if (j != i) {
                        clients[j].sendMessageWOR("Player " + i + " won this round");
                    }
                }
            }
        }
    }

    public void sendPlayerCards(){
        for (ClientThread player : clients) {
            player.showCards();
        }
    }

    public void changePlayerCards(ClientThread player){
        boolean flag = true;
        List<Integer> cardsIndicesToRemove = new ArrayList<>();
        do {
            flag = true;
            String response;
            cardsIndicesToRemove.clear();
            try {
                response = player.sendMessage("Podaj ktore karty chcesz wymienic (jesli nie chcesz wymienic kliknij enter)");
                if (!response.trim().equals("")) {
                    String[] cards = response.split(",");

                    for (String card : cards) {
                        int val=Integer.parseInt(card.trim());
                        if (val < 0 || val > 4) {
                            throw new Exception();
                        }
                        cardsIndicesToRemove.add(val);
                    }

                }
            }
            catch (NumberFormatException e) {
                player.sendMessageWOR("Podano nieprawidłową wartość!");
                flag = false;
            }
            catch (Exception e) {
                player.sendMessageWOR("Nie ma takiej karty!");
                flag = false;
            }
        }
        while (!flag);
        List<Card> cardsToRemove = new ArrayList<>();
        for (int i = 0; i < cardsIndicesToRemove.size(); i++) {
            cardsToRemove.add(player.player.getHand().getCard(cardsIndicesToRemove.get(i)));
        }
        for (Card card : cardsToRemove) {
            player.player.getHand().removeCard(card);
        }
        int howManyCardsToAdd = TextUtils.HAND_SIZE- player.player.getHand().getSize();
        for (int i = 0; i < howManyCardsToAdd; i++) {
            player.player.addCard(deck.draw());
        }
        player.showCards();
    }

    public void changePlayersHands(){
        for (ClientThread player : clients) {
            changePlayerCards(player);
        }
    }

    public void gameRound() throws IOException {
        while (true) {
            startGame();
            sendPlayerCards();
            changePlayersHands();
            getWinners();
            sendWinners();
        }



//        while (true) {
//
//        }
    }


}
