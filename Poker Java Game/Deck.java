import java.util.*;

/**
 * The Deck class uses the Card class to create a deck of 52
 * playing cards which will be used by the Hand and PokerModel classes.
 * @author Justin Mazzola
 */
public class Deck {
    /** Integer for the number of cards in the deck */
    public static final int CARDS_IN_DECK = 52;
    
    /** Integer for the number of times to shuffle the deck */
    public static final int SHUFFLE_SWAPS = 500;
    
    /** 
     * Integer for the difference between the index of 
     * the card and its actual number.
     */
    public static final int ZERO_TWO_DIFF = 2;
    
    /** Integer for the index change of clubs */
    public static final int CLUBS_INDEX = 13;
    
    /** Integer for the index change of diamonds */
    public static final int DIAMONDS_INDEX = 26;
    
    /** Integer for the index change of hearts */
    public static final int HEARTS_INDEX = 39;
    
    /** Creates the new deck of cards, no initialization */
    private Card[] deck;
    
    /** Integer for the next index of the next card in the deck */
    private int nextIndex;
    
    /** Integer of the seed to be use for shuffling decks */
    private int seed;
    
    /**
     * Constructor for the Deck object using the seed
     * @param seed is the number to be used in shuffling for testing.
     */
    public Deck(int seed) {
        this.seed = seed;
        deck = new Card[CARDS_IN_DECK];
        nextIndex = 0;

        for (int i = 0; i < deck.length; i++) {
            char suit;
            if (i < CLUBS_INDEX) {
                suit = Card.CLUBS;
            } else if (i < DIAMONDS_INDEX) {
                suit = Card.DIAMONDS;
            } else if (i < HEARTS_INDEX) {
                suit = Card.HEARTS;
            } else {
                suit = Card.SPADES;
            }
    
            deck[i] = new Card(((i + CLUBS_INDEX) % CLUBS_INDEX + ZERO_TWO_DIFF), suit);
        }
    }
    
    /**
     * The shuffle method uses the SHUFFLE_SWAPS constant
     * to shuffle the deck a given number of times.
     */
    public void shuffle() {
        Random rand;
        if (seed != -1) {
            rand = new Random(seed);
        } else {
            rand = new Random();
        }
    
        for (int i = 1; i <= SHUFFLE_SWAPS; i++) {
            int swap1 = rand.nextInt(CARDS_IN_DECK);
            int swap2 = rand.nextInt(CARDS_IN_DECK);
        
            Card temp = deck[swap1];
            deck[swap1] = new Card(deck[swap2].getValue(), deck[swap2].getSuit());
            deck[swap2] = new Card(temp.getValue(), temp.getSuit());
        }
    
        nextIndex = 0;
    }
    
    /**
     * The nextCard method returns the next card in the deck to
     * be used in the poker game.
     * @return the next card in the deck
     */
    public Card nextCard() {
        Card nextCard = deck[nextIndex];
        nextIndex++;
        return nextCard;
    }
    
    /**
     * The toString method converts the deck to a readable form
     * and returns the deck as a string.
     * @return the string version of the deck
     */
    public String toString() {
        String s = "";
    
        for (int i = 0; i < deck.length; i++) {
            s += "card " + i + ": " + deck[i].getSuit() + deck[i].getValue() + "\n";
        }
    
        return s;
    }
    
    /**
     * The equals method checks for equality of two decks, or if both
     * are decks at all.
     * @param o is the object for comparison
     * @return the equality of two objects
     */
    public boolean equals(Object o) {
        if (o instanceof Deck) {
            Deck other = (Deck)o;
            
            boolean equalDeck = false;
            if (deck[0].equals(other.deck[0]) && 
                deck[CARDS_IN_DECK - 1].equals(other.deck[CARDS_IN_DECK - 1])) {
                equalDeck = true;
            }
            
            if (nextIndex == other.nextIndex && seed == other.seed && equalDeck) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
