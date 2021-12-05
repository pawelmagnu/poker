package pl.edu.agh.kis.pz1;

/**
 * Class representing a deck of cards.
 */
public class Deck {
    private Card[] cards;
    private int size;

    /**
     * Creates a new deck of 52 cards.
     */
    public Deck() {
        cards = new Card[52];
        size = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[size++] = new Card(j, i);
            }
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        for (int i = 0; i < size; i++) {
            int j = (int) (Math.random() * size);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    /**
     * Draws a card from the deck.
     * @return drawn card
     */
    public Card draw() {
        return cards[--size];
    }
}
