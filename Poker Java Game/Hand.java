import java.util.*;

/**
 * The Hand class uses the deck and card objects to make
 * a poker hand of 5 cards.
 * @author Justin Mazzola
 */
public class Hand {
    /** Creates a private hand object (an array of cards) */
    private Card[] hand;
    
    /** Integer for the cards in a hand */
    public static final int CARDS_IN_HAND = 5;
    
    /** Integer for the lowest value in a royal flush */
    public static final int ROYAL_FLUSH_LOW = 10;
    
    /** Integer for the number of cards in a pair */
    public static final int PAIR = 2;
    
    /** Integer for the number of cards in 3 of a kind */
    public static final int TRIO = 3;
    
    /** Integer for the cards in 4 of a kind */
    public static final int QUARTET = 4;

    /**
     * Constructor for the Hand object, which is actually
     * just an array of cards from the deck. Throws
     * an exception under certain circumstances.
     * @param hand is the array of cards to be used
     */
    public Hand(Card[] hand) {
        if (hand.length != CARDS_IN_HAND) {
            throw new IllegalArgumentException("Wrong hand size");
        }
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                throw new NullPointerException("Error: null card");
            }
        }
        
        this.hand = hand;
    }
    
    /**
     * The getCard method returns the current card in the hand.
     * Throws an exception if the index is invalid.
     * @param index is the index of the current card.
     * @return the actual current card
     */
    public Card getCard(int index) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Illegal index");
        }
        
        return hand[index];
    }
    
    /**
     * The replace method replaces the current card
     * at the index with the new card. Throws an exception
     * if index or card is invalid.
     * @param index is the index to be modified
     * @param card is the new card
     */
    public void replace(int index, Card card) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (card == null) {
            throw new NullPointerException("Error: null card");
        }
        
        hand[index] = card;
    }
    
    /**
     * The toString method converts a hand to a string
     * for reading purposes.
     * @return the string of the hand
     */
    public String toString() {
        String s = "[";
        for (int i = 0; i < hand.length; i++) {
            s += hand[i].toString();
            
            if (i != hand.length - 1) {
                s += ", ";
            }
        }
        
        return s + "]";
    }
    
    /**
     * The equals method checks for deck equality
     * @param o is the object to be tested
     * @return the equality of the two objects
     */
    public boolean equals(Object o) {
        if (o instanceof Hand) {
            Hand other = (Hand)o;
            other.hand = other.getSortedHand();
            Card[] sorted = getSortedHand();
            for (int i = 0; i < hand.length; i++) {
                if (!sorted[i].equals(other.hand[i])) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Counts the number of cards with each value in the hand
     * @return tally array containing number of cards of each value from 2 to 14.
     */
    public int[] getCounts() {
        int[] counts = new int[Card.HIGHEST_VALUE + 1];
        for (int i = 0; i < hand.length; i++) {
            counts[hand[i].getValue()]++;
        }
        return counts;
    }

    /**
     * Creates a copy of the hand sorted first by value, then by suit
     * @return copy of the hand sorted first by value, then by suit
     */
    public Card[] getSortedHand() {
        Card[] sortedHand = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sortedHand);
        return sortedHand;
    }
    
    /**
     * The isFlush method checks to see if the hand makes a flush
     * @return whether or not the hand is a flush
     */
    public boolean isFlush() {
        char suit = hand[0].getSuit();
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].getSuit() != suit) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * The isStraight method checks to see if the hand makes a straight
     * @return whether or not the hand is a straight
     */
    public boolean isStraight() {
        Card[] sorted = getSortedHand();
        int val = sorted[0].getValue();
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i].getValue() != val + i) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * The isStraightFlush method checks to see if the hand makes a straight flush
     * @return whether or not the hand is a straight flush
     */
    public boolean isStraightFlush() {
        if (isFlush() && isStraight()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * The isRoyalFlush method checks to see if the hand makes a royal flush
     * @return whether or not the hand is a royal flush
     */
    public boolean isRoyalFlush() {
        Card[] sorted = getSortedHand();
        if (!isStraightFlush()) {
            return false;
        }
        if (sorted[0].getValue() == ROYAL_FLUSH_LOW) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * The hasFourOfAKind method checks to see if the hand makes four of a kind
     * @return whether or not the hand is four of a kind
     */
    public boolean hasFourOfAKind() {
        int[] tally = getCounts();
        for (int i = 0; i < tally.length; i++) {
            if (tally[i] == QUARTET) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * The hasThreeOfAKind method checks to see if the hand makes three of a kind
     * @return whether or not the hand is three of a kind
     */
    public boolean hasThreeOfAKind() {
        int[] tally = getCounts();
        for (int i = 0; i < tally.length; i++) {
            if (tally[i] == TRIO) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * The hasTwoPairs method checks to see if the hand has two pairs
     * @return whether or not the hand has two pairs
     */
    public boolean hasTwoPairs() {
        int pairCount = 0;
        int[] tally = getCounts();
        for (int i = 0; i < tally.length; i++) {
            if (tally[i] == PAIR) {
                pairCount++;
            }
        }
        if (pairCount == PAIR) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * The hasOnePair method checks to see if the hand has one pair
     * @return whether or not the hand has one pair
     */
    public boolean hasOnePair() {
        int pairCount = 0;
        int[] tally = getCounts();
        for (int i = 0; i < tally.length; i++) {
            if (tally[i] == PAIR) {
                pairCount++;
            }
        }
        if (pairCount == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * The isFullHouse method checks to see if the hand makes a full house
     * @return whether or not the hand is a full house
     */
    public boolean isFullHouse() {
        int pairCount = 0;
        int threeCount = 0;
        int[] tally = getCounts();
        for (int i = 0; i < tally.length; i++) {
            if (tally[i] == PAIR) {
                pairCount++;
            }
            if (tally[i] == TRIO) {
                threeCount++;
            }
        }
        if (pairCount == 1 && threeCount == 1) {
            return true;
        } else {
            return false;
        }
    }
}
