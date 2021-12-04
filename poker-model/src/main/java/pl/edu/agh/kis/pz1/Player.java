package pl.edu.agh.kis.pz1;

public class Player{
    private Hand hand;
    private int handHash;
    private int playerID;
    private int balance;

    public Player() {
        playerID = 0;
        hand = new Hand();
        balance = 0;
        handHash = 0;
    }

    public Player(int playerID, int balance) {
        this.playerID = playerID;
        hand = new Hand();
        this.balance = balance;
        handHash = 0;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addToBalance(int amount) {
        balance += amount;
    }

    public void addCard(Card card) {
        this.hand.addCard(card);
    }

    public void removeCard(Card card) {
        this.hand.removeCard(card);
    }

    public void clearHand() {
        this.hand.clear();
    }

    public void calculateHash() {
        handHash = PokerHands.getHash(this.hand);
    }

    public int getHandHash() {
        return handHash;
    }
}
