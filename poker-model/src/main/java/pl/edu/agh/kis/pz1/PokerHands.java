package pl.edu.agh.kis.pz1;

/**
 * Class representing poker hands.
 * Used to check the type of hand and its hash.
 */
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

    /**
     * Return the hash of the hand.
     * @param hand hand to be hashed
     * @return hash of the hand
     */
    public static int getHash(Hand hand) {
        int handType = getHandType(hand);
        int hash = handType*1000000;
        for (Card card : hand.getCards()) {
            hash += (int)Math.pow(2, card.getRank().ordinal());
        }
        return hash;
    }

    /**
     * Return the type of the hand.
     * @param hand hand to be checked
     * @return type of the hand
     */
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

    /**
     * Check if the hand is a royal flush.
     * @param hand hand to be checked
     * @return true if the hand is a royal flush, false otherwise
     */
    public static boolean isRoyalFlush(Hand hand) {
        return isStraightFlush(hand) && hand.getCards()[4].getRank() == Card.Rank.TEN;
    }

    /**
     * Check if the hand is a straight flush.
     * @param hand hand to be checked
     * @return true if the hand is a straight flush, false otherwise
     */
    public static boolean isStraightFlush(Hand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    /**
     * Check if the hand is quads.
     * @param hand hand to be checked
     * @return true if the hand is a quads, false otherwise
     */
    public static boolean isQuads(Hand hand) {
        return hand.getCard(0).getRank() == hand.getCard(3).getRank()
                || hand.getCard(1).getRank() == hand.getCard(4).getRank();
    }

    /**
     * Check if the hand is a full house.
     * @param hand hand to be checked
     * @return true if the hand is a full house, false otherwise
     */
    public static boolean isFullHouse(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(1).getRank() &&
                hand.getCard(2).getRank() == hand.getCard(3).getRank() &&
                hand.getCard(3).getRank() == hand.getCard(4).getRank();
        boolean second = hand.getCard(0).getRank() == hand.getCard(1).getRank() &&
                hand.getCard(1).getRank() == hand.getCard(2).getRank() &&
                hand.getCard(3).getRank() == hand.getCard(4).getRank();
        return first || second;
    }

    /**
     * Check if the hand is a flush.
     * @param hand hand to be checked
     * @return true if the hand is a flush, false otherwise
     */
    public static boolean isFlush(Hand hand) {
        for (int i = 1; i < hand.getCards().length; i++) {
            if (hand.getCards()[0].getSuit() != hand.getCards()[i].getSuit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the hand is a straight.
     * @param hand hand to be checked
     * @return true if the hand is a straight, false otherwise
     */
    public static boolean isStraight(Hand hand) {
        for (int i = 0; i < hand.getCards().length - 1; i++) {
            if (hand.getCards()[i].getRank().ordinal() - hand.getCards()[i + 1].getRank().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the hand is a triple.
     * @param hand hand to be checked
     * @return true if the hand is a triple, false otherwise
     */
    public static boolean isTriple(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(2).getRank();
        boolean second = hand.getCard(1).getRank() == hand.getCard(3).getRank();
        boolean third = hand.getCard(2).getRank() == hand.getCard(4).getRank();
        return first || second || third;
    }

    /**
     * Check if the hand is two pair.
     * @param hand hand to be checked
     * @return true if the hand is two pair, false otherwise
     */
    public static boolean isTwoPair(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(1).getRank()
                && hand.getCard(2).getRank() == hand.getCard(3).getRank();
        boolean second = hand.getCard(0).getRank() == hand.getCard(1).getRank()
                && hand.getCard(3).getRank() == hand.getCard(4).getRank();
        boolean third = hand.getCard(1).getRank() == hand.getCard(2).getRank()
                && hand.getCard(3).getRank() == hand.getCard(4).getRank();
        return first || second || third;
    }

    /**
     * Check if the hand is a pair.
     * @param hand hand to be checked
     * @return true if the hand is a pair, false otherwise
     */
    public static boolean isPair(Hand hand) {
        boolean first = hand.getCard(0).getRank() == hand.getCard(1).getRank();
        boolean second = hand.getCard(1).getRank() == hand.getCard(2).getRank();
        boolean third = hand.getCard(2).getRank() == hand.getCard(3).getRank();
        boolean fourth = hand.getCard(3).getRank() == hand.getCard(4).getRank();
        return first || second || third || fourth;
    }

}