package pl.edu.agh.kis.pz1;

/**
 * Class representing a player.
 */
public class Player{
    private Hand hand;
    private int handHash;
    private int playerID;
    private int balance;
    private boolean inGame;

    /**
     * Constructor.
     */
    public Player() {
        playerID = 0;
        hand = new Hand();
        balance = 0;
        handHash = 0;
        inGame = true;
    }

    /**
     * Method for printing player's id.
     * @return player's id.
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Method for setting player's id.
     * @param playerID player's id.
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Method for printing player's hand.
     * @return player's hand.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Method for printing player's balance.
     * @return player's balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Method for setting player's balance.
     * @param balance player's balance.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Method for adding given amount to player's balance.
     * @param amount amount to add.
     */
    public void addToBalance(int amount) {
        balance += amount;
    }

    /**
     * Method for adding a card to player's hand.
     * @param card card to add.
     */
    public void addCard(Card card) {
        this.hand.addCard(card);
    }

    /**
     * Method for clearing player's hand
     */
    public void clearHand() {
        this.hand.clear();
    }

    /**
     * Method for calculating player's hand hash.
     */
    public void calculateHash() {
        handHash = PokerHands.getHash(this.hand);
    }

    /**
     * Method for printing player's hand hash.
     * @return player's hand hash.
     */
    public int getHandHash() {
        return handHash;
    }

    /**
     * Method for checking if a player is still in game
     * @return true if player is still in game, false otherwise
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * Method for setting player's inGame status
     * @param inGame player's inGame status
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}
