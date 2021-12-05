package pl.edu.agh.kis.pz1;

/**
 * Class representing a card.
 */
public class Card {
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
    public enum Suit{
        CLUBS, DIAMONDS, HEARTS, SPADES
    }
    private final Suit suit;
    private final Rank rank;

    /**
     * Creates a new card with given rank and suit but in a numerical form.
     * @param rank of the card
     * @param suit of the card
     */
    public Card(Integer rank, Integer suit) {
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    /**
     * Returns the suit of the card.
     * @return suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the rank of the card.
     * @return rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Comparator for cards.
     * @return if this card is the same as the given card
     */
    public boolean isSame(Card card) {
        if(card == this) return true;
        if(card == null)   return false;
        if(card.getClass() != this.getClass()) return false;
        return this.suit == card.suit && this.rank == card.rank;
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
