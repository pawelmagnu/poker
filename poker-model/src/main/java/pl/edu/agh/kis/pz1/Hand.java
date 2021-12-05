package pl.edu.agh.kis.pz1;

/**
 * Class representing a hand of cards.
 */
public class Hand {
    private Card[] cards;
    private int size;

    /**
     * Constructor.
     */
    public Hand() {
        cards = new Card[5];
        size = 0;
    }

    /**
     * Adds a card to the hand and increases its size.
     * @param card card to add
     */
    public void addCard(Card card) {
        cards[size] = card;
        size++;
    }

    /**
     * Returns the card with given index.
     * @param index index of the card
     * @return card with given index
     */
    public Card getCard(int index) {
        return cards[index];
    }

    /**
     * Returns all cards in the hand.
     * @return all cards in the hand
     */
    public Card[] getCards() {
        return cards;
    }

    /**
     * Sets the cards in the hand to given cards.
     * @param cards cards to set
     */
    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    /**
     * Returns the size of the hand.
     * @return size of the hand
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes the card with given index.
     * @param index index of the card to remove
     */
    public void removeCard(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.cards[i] = this.cards[i + 1];
        }
        size--;
    }

    /**
     * Removes the given card from the hand.
     * @param card card to remove
     */
    public void removeCard(Card card) {
        for (int i = 0; i < this.size; i++) {
            if (this.cards[i].isSame(card)) {
                removeCard(i);
            }
        }
    }

    /**
     * Clears the hand.
     */
    public void clear() {
        cards = new Card[5];
        size = 0;
    }

    /**
     * Checks if given card is in the hand.
     * @param card card to check
     * @return true if the card is in the hand, false otherwise
     */
    public boolean contains(Card card) {
        for (int i = 0; i < size; i++) {
                if (cards[i].isSame(card)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(cards[i].toString());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
