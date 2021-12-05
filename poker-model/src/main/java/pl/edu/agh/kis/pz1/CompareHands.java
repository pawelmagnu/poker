package pl.edu.agh.kis.pz1;

/**
 * Class used for comparing hands and determining winner.
 */
public class CompareHands {
    private int topHandScore(Player[] players) {
        int max = 0;
        for (int i = 0; i < players.length; i++) {
            max = Math.max(max, PokerHands.getHandType(players[i].getHand()));
        }
        return max;
    }
    private int[] getWinnersByHash(Player[] players, int topHandHash) {
        int[] result = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            if (players[i].getHandHash() == topHandHash) {
                result[i] = 1;
            }
            else {
                result[i] = 0;
            }
        }
        return result;
    }
    private int getPairType(Hand hand) {
        if(hand.getCard(0).getRank() == hand.getCard(1).getRank()) {
            return hand.getCard(0).getRank().ordinal();
        }
        else if(hand.getCard(1).getRank() == hand.getCard(2).getRank()) {
            return hand.getCard(1).getRank().ordinal();
        }
        else if(hand.getCard(2).getRank() == hand.getCard(3).getRank()) {
            return hand.getCard(2).getRank().ordinal();
        }
        else if(hand.getCard(3).getRank() == hand.getCard(4).getRank()) {
            return hand.getCard(3).getRank().ordinal();
        }
        else {
            return -1;
        }
    }
    private int[] getPairsTypes(Hand hand) {
        int [] result = new int[2];
        result[0] = getPairType(hand);
        if(hand.getCard(0).getRank() == hand.getCard(1).getRank()) {
            result[0] = hand.getCard(0).getRank().ordinal();
        }
        else if(hand.getCard(1).getRank() == hand.getCard(2).getRank()) {
            result[0] = hand.getCard(1).getRank().ordinal();
        }
        else if(hand.getCard(2).getRank() == hand.getCard(3).getRank()) {
            if (hand.getCard(2).getRank().ordinal() != result[0]) {
                result[1] = hand.getCard(2).getRank().ordinal();
            }
        }
        else if(hand.getCard(3).getRank() == hand.getCard(4).getRank()) {
            if (hand.getCard(3).getRank().ordinal() != result[0]) {
                result[1] = hand.getCard(3).getRank().ordinal();
            }
        }
        else {
            result[1] = -1;
        }
        if (result[1] > result[0]) {
            int temp = result[0];
            result[0] = result[1];
            result[1] = temp;
        }
        return result;
    }
    private int getTripleType(Hand hand) {
        return hand.getCard(2).getRank().ordinal();
    }
    private int getQuadsType(Hand hand) {
        return hand.getCard(2).getRank().ordinal();
    }

    /**
     * Method for sorting given players by their hash
     * @param players array of players
     * @return array of players sorted by their hash
     */
    public Player[] sortPlayersByHash(Player[] players) {
        Player[] result = new Player[players.length];
        for (int i = 0; i < players.length; i++) {
            result[i] = players[i];
            result[i].calculateHash();
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (result[i].getHandHash() > result[j].getHandHash()) {
                    Player temp = result[i];
                    result[i] = result[j];
                    result[j] = temp;
                }
            }
        }
        return result;
    }

    /**
     * Method for determining winner of given players
     * @param players array of players
     * @return array of players with 1 in the index of player who won and 0 in the others
     */
    public int[] getWinners(Player[] players) {
        int topHandScore = topHandScore(players);
        if(topHandScore == 0 || topHandScore == 4 || topHandScore == 5 || topHandScore == 8) {
            return getWinnersByHash(players, players[0].getHandHash());
        }
        else if(topHandScore == 1) {
            int[] pairsTypes = new int[players.length];
            int maxType = 0;
            for (int i = 0; i < players.length; i++) {
                pairsTypes[i] = getPairType(players[i].getHand());
                if(pairsTypes[i] > maxType) {
                    maxType = pairsTypes[i];
                }
            }
            int[] values = new int[players.length];
            int maxHash = 0;
            for (int i = 0; i < players.length; i++) {
                if(pairsTypes[i] == maxType) {
                    values[i] = players[i].getHandHash();
                    if(values[i] > maxHash) {
                        maxHash = values[i];
                    }
                }
                else {
                    values[i] = -1;
                }
            }
            return getWinnersByHash(players, maxHash);
        }
        else if(topHandScore == 2) {
            int[][] pairsTypes = new int[players.length][2];
            int maxType1 = 0;
            int maxType2 = 0;
            for (int i = 0; i < players.length; i++) {
                if (PokerHands.isTwoPair(players[i].getHand())) {
                    pairsTypes[i] = getPairsTypes(players[i].getHand());
                    if (pairsTypes[i][0] > maxType1) {
                        maxType1 = pairsTypes[i][0];
                    }
                }
                else {
                    pairsTypes[i][0] = -1;
                }
            }
            for (int i = 0; i < players.length; i++) {
                if(pairsTypes[i][0] == maxType1 && pairsTypes[i][1] > maxType2) {
                    maxType2 = pairsTypes[i][1];
                }
            }
            int[] values = new int[players.length];
            int maxHash = 0;
            for (int i = 0; i < players.length; i++) {
                if(pairsTypes[i][0] == maxType1 && pairsTypes[i][1] == maxType2) {
                    values[i] = players[i].getHandHash();
                    if(values[i] > maxHash) {
                        maxHash = values[i];
                    }
                }
                else {
                    values[i] = -1;
                }
            }
            return getWinnersByHash(players, maxHash);
        }
        else if(topHandScore == 3 || topHandScore == 6) {
            int[] tripleTypes = new int[players.length];
            int maxType = 0;
            for (int i = 0; i < players.length; i++) {
                if (PokerHands.isTriple(players[i].getHand())) {
                    tripleTypes[i] = getTripleType(players[i].getHand());
                    if(tripleTypes[i] > maxType) {
                        maxType = tripleTypes[i];
                    }
                }
                else {
                    tripleTypes[i] = -1;
                }
            }
            int[] values = new int[players.length];
            for (int i = 0; i < players.length; i++) {
                if(tripleTypes[i] == maxType) {
                    values[i] = 1;
                }
                else {
                    values[i] = 0;
                }
            }
            return values;
        }
        else if(topHandScore == 7) {
            int[] quadTypes = new int[players.length];
            int maxType = 0;
            for (int i = 0; i < players.length; i++) {
                if (PokerHands.isQuads(players[i].getHand())) {
                    quadTypes[i] = getQuadsType(players[i].getHand());
                    if(quadTypes[i] > maxType) {
                        maxType = quadTypes[i];
                    }
                }
                else {
                    quadTypes[i] = -1;
                }
            }
            int[] values = new int[players.length];
            for (int i = 0; i < players.length; i++) {
                if(quadTypes[i] == maxType) {
                    values[i] = 1;
                }
                else {
                    values[i] = 0;
                }
            }
            return values;
        }
        else if(topHandScore == 9) {
            int[] result = new int[players.length];
            for (int i = 0; i < players.length; i++) {
                if(PokerHands.isRoyalFlush(players[i].getHand())) {
                    result[i] = 1;
                }
                else {
                    result[i] = 0;
                }
            }
            return result;
        }
        return new int[players.length];
    }

    /**
     * Method for proper parsing winners into more useful format
     * @param players - sorted array of players
     * @param winners - array of winners from sorted players
     * @return - array of winners sorted by their id
     */
    public int[] parseWinners(Player[] players, int[] winners) {
        int[] result = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            if(winners[i] == 1) {
                result[players[i].getPlayerID()] = 1;
            }
            else {
                result[players[i].getPlayerID()] = 0;
            }
        }
        return result;
    }
}
