package pl.edu.agh.kis.pz1;

public class PokerHands {
    public static final int HIGHCARD = 0;
    public static final int PAIR = 1;
    public static final int TWOPAIR = 2;
    public static final int TRIPLE = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULLHOUSE = 6;
    public static final int QUADS = 7;
    public static final int STRAIGHTFLUSH = 8;
    public static final int ROYALFLUSH = 9;

    private static void sortHand(Hand hand) {
        Hand temp = new Hand();
        Card tempCard;
        for (int i = 12; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                tempCard = new Card(i, j);
                if (hand.contains(tempCard)) {
                    temp.addCard(tempCard);
                }
            }
        }
        hand.setCards(temp.getCards());
    }
    public static int getHash(Hand hand) {
        int handType = getHandType(hand);
        int hash = handType*1000000;
        for (Card card : hand.getCards()) {
            hash += (int)Math.pow(2, card.getRank().ordinal());
        }
        return hash;
    }

    public static int getHandType(Hand hand) {
        sortHand(hand);
        if (isRoyalFlush(hand)) {
            return ROYALFLUSH;
        }
        if (isStraightFlush(hand)) {
            return STRAIGHTFLUSH;
        }
        if (isQuads(hand)) {
            return QUADS;
        }
        if (isFullHouse(hand)) {
            return FULLHOUSE;
        }
        if (isFlush(hand)) {
            return FLUSH;
        }
        if (isStraight(hand)) {
            return STRAIGHT;
        }
        if (isTriple(hand)) {
            return TRIPLE;
        }
        if (isTwoPair(hand)) {
            return TWOPAIR;
        }
        if (isPair(hand)) {
            return PAIR;
        }
        return HIGHCARD;
    }

    public static boolean isRoyalFlush(Hand hand) {
        return isStraightFlush(hand) && hand.getCards()[4].getRank() == Card.Rank.TEN;
    }

    public static boolean isStraightFlush(Hand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    public static boolean isQuads(Hand hand) {
        return hand.getCard(0).getRank() == hand.getCard(3).getRank()
                || hand.getCard(1).getRank() == hand.getCard(4).getRank();
    }

    public static boolean isFullHouse(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(1).getRank() &&
                hand.getCard(2).getRank() == hand.getCard(3).getRank() &&
                hand.getCard(3).getRank() == hand.getCard(4).getRank();
        boolean second = hand.getCard(0).getRank() == hand.getCard(1).getRank() &&
                hand.getCard(1).getRank() == hand.getCard(2).getRank() &&
                hand.getCard(3).getRank() == hand.getCard(4).getRank();
        return first || second;
    }

    public static boolean isFlush(Hand hand) {
        for (int i = 1; i < hand.getCards().length; i++) {
            if (hand.getCards()[0].getSuit() != hand.getCards()[i].getSuit()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStraight(Hand hand) {
        for (int i = 0; i < hand.getCards().length - 1; i++) {
            if (hand.getCards()[i].getRank().ordinal() - hand.getCards()[i + 1].getRank().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTriple(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(2).getRank();
        boolean second = hand.getCard(1).getRank() == hand.getCard(3).getRank();
        boolean third = hand.getCard(2).getRank() == hand.getCard(4).getRank();
        return first || second || third;
    }

    public static boolean isTwoPair(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(1).getRank()
                && hand.getCard(2).getRank() == hand.getCard(3).getRank();
        boolean second = hand.getCard(0).getRank() == hand.getCard(1).getRank()
                && hand.getCard(3).getRank() == hand.getCard(4).getRank();
        boolean third = hand.getCard(1).getRank() == hand.getCard(2).getRank()
                && hand.getCard(3).getRank() == hand.getCard(4).getRank();
        return first || second || third;
    }

    public static boolean isPair(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(1).getRank();
        boolean second = hand.getCard(1).getRank() == hand.getCard(2).getRank();
        boolean third = hand.getCard(2).getRank() == hand.getCard(3).getRank();
        boolean fourth = hand.getCard(3).getRank() == hand.getCard(4).getRank();
        return first || second || third || fourth;
    }

}