package pl.edu.agh.kis.pz1;

public class Card {
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
    public enum Suit{
        CLUBS, DIAMONDS, HEARTS, SPADES
    }
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public Card(Integer rank, Integer suit) {
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isSame(Card card) {
        if(card == this) return true;
        if(card == null) return false;
        if(card.getClass() != this.getClass()) return false;
        return this.suit == card.suit && this.rank == card.rank;
    }

    public static Card getCardFromString(String card) {
        String[] cardSplit = card.split(" ");
        return new Card(Suit.valueOf(cardSplit[1]), Rank.valueOf(cardSplit[0]));
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
