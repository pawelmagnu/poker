package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

import pl.edu.agh.kis.pz1.util.TextUtils;

public class Game {
    private ClientThread[] clients;
    private int[] currentBids;
    private int currentMaxBid;
    private int totalBid;
    private int numberOfPlayers;
    private Deck deck;
    private int[] winnersThisRound;
    ServerSocket serverSocket;

    public Game(int numberOfPlayers) throws IOException {
        serverSocket = new ServerSocket(TextUtils.PORT);
        this.numberOfPlayers = numberOfPlayers;
        clients = new ClientThread[numberOfPlayers];
        currentBids = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            clients[i] = new ClientThread(serverSocket.accept());
            clients[i].player.setPlayerID(i);
            clients[i].player.setBalance(TextUtils.STARTING_BALANCE);
            System.out.println("nowy gracz: " + clients[i].player.getPlayerID());
            clients[i].sendMessageWOR("Hello player "+i);
        }
    }

    private void getBet(ClientThread client) throws IOException {
        int bet=0;
        boolean flag=true;
        do {
            flag = true;
            String response;
            try {
                response = client.sendMessage("Co chcesz zrobic?");
                if (response.trim().equals("fold")) {
                    client.player.setInGame(false);
                }
                else if (response.trim().equals("check")) {
                    if (client.player.getBalance() < currentMaxBid) {
                        client.sendMessageWOR("You don't have enough money to play");
                        client.player.setInGame(false);
                    }
                    else if (currentBids[client.player.getPlayerID()] != currentMaxBid) {
                        int diff = currentMaxBid - currentBids[client.player.getPlayerID()];
                        client.player.addToBalance(-diff);
                        currentBids[client.player.getPlayerID()] = currentMaxBid;
                    }
                }
                else if (response.trim().equals("")) {
                    client.sendMessageWOR("You didn't enter anything");
                    flag = false;
                }
                else {
                    bet = Integer.parseInt(response);
                    int diff = bet - currentBids[client.player.getPlayerID()];
                    if (diff > client.player.getBalance()) {
                        client.sendMessageWOR("You don't have enough money for that");
                        flag = false;
                    }
                    else if (bet < currentMaxBid) {
                        client.sendMessageWOR("You can't bet less than current max");
                        flag = false;
                    }
                    else {
                        client.player.addToBalance(-diff);
                        currentMaxBid = bet;
                        currentBids[client.player.getPlayerID()] = bet;
                        totalBid += diff;
                    }
                }
            }
            catch (NumberFormatException e) {
                client.sendMessageWOR("You didn't enter a number");
                flag = false;
            }
            catch (IOException e) {
                client.sendMessageWOR("Podano zły komunikat");
                flag = false;
            }
        }
        while (!flag);
    }

    private void getBetsFromPlayers() throws IOException {
        boolean flag = true;
        do {
            flag = true;
            for (ClientThread player : clients) {
                if (player.player.isInGame()) {
                    player.sendMessageWOR("Current max bid is " + currentMaxBid);
                    player.sendMessageWOR("Obecny bet to:" + currentBids[player.player.getPlayerID()]);
                    player.sendMessageWOR("Twoj balance to:" + player.player.getBalance());
                    player.sendMessageWOR("Jesli chcesz podbic wpisz do ilu");
                    player.sendMessageWOR("Jesli chcesz wyjsc wpisz fold");
                    player.sendMessageWOR("Jesli chcesz zakonczyc wpisz check");
                    getBet(player);
                }
            }
            for (ClientThread player : clients) {
                if (player.player.isInGame() && currentBids[player.player.getPlayerID()] != currentMaxBid) {
                    flag = false;
                }
            }
        }
        while (!flag);
    }

    public void updateBalance(int totalBid, int[] winners) {
        int howManyWinners=0;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (winners[i] == 1) {
                howManyWinners++;
            }
        }
        if (howManyWinners == 0) {
            howManyWinners = 1;
        }
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
            if (player.player.getBalance() < TextUtils.ANTE) {
                player.sendMessageWOR("You don't have enough money to play");
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                if (clients[j].player.isInGame()){
                    clients[j].player.addCard(deck.draw());
                }
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
            player.sendMessageWOR("Your balance is: "+ player.player.getBalance());
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
            if (player.player.isInGame()) {
                changePlayerCards(player);
            }
        }
    }

    public void getAnte(){
        totalBid = 0;
        currentBids = new int[numberOfPlayers];
        currentMaxBid = 0;
        for (ClientThread player : clients) {
            if (player.player.getBalance() < TextUtils.ANTE) {
                player.sendMessageWOR("Nie masz wystarczająco dużo pieniędzy!");
                player.player.setInGame(false);
            }
            else {
                player.sendMessageWOR("Pobrano ANTE w wysokosci:"+ TextUtils.ANTE);
                player.player.setInGame(true);
                player.player.setBalance(player.player.getBalance() - TextUtils.ANTE);
                this.totalBid += TextUtils.ANTE;
                currentBids[player.player.getPlayerID()] = 0;
            }
        }
    }

    public void gameRound() throws IOException {
        while (true) {
            getAnte();
            startGame();
            sendPlayerCards();
            getBetsFromPlayers();
            changePlayersHands();
            getBetsFromPlayers();
            getWinners();
            sendWinners();
            updateBalance(totalBid, winnersThisRound);
        }
    }


}
