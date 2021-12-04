package pl.edu.agh.kis.pz1;

public class Deck {
    private Card[] cards;
    private int size;

    public Deck() {
        cards = new Card[52];
        size = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[size++] = new Card(j, i);
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < size; i++) {
            int j = (int) (Math.random() * size);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card draw() {
        return cards[--size];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
