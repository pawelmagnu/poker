package pl.edu.agh.kis.pz1;

public class Hand {
    private Card[] cards;
    private int size;

    public Hand() {
        cards = new Card[5];
        size = 0;
    }

    public void addCard(Card card) {
        cards[size] = card;
        size++;
    }

    public Card getCard(int index) {
        return cards[index];
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void removeCard(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.cards[i] = this.cards[i + 1];
        }
        size--;
    }

    public void removeCard(Card card) {
        for (int i = 0; i < this.size; i++) {
            if (this.cards[i].isSame(card)) {
                removeCard(i);
            }
        }
    }

    public void clear() {
        cards = new Card[5];
        size = 0;
    }

    public boolean contains(Card card) {
        for (int i = 0; i < size; i++) {
                if (cards[i].isSame(card)) {
                return true;
            }
        }
        return false;
    }

    public Hand getHandFromString(String hand) {
        Hand newHand = new Hand();
        String[] cards = hand.split(",");
        for (String card : cards) {
            newHand.addCard(Card.getCardFromString(card));
        }
        return newHand;
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
