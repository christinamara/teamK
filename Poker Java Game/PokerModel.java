import java.util.*;

/**
 * The PokerModel class is the game engine for the poker program.
 * It keeps track of points and new games and hands it to the GUI
 * in the PokerGUI class for display.
 * @author Justin Mazzola
 */
public class PokerModel {
    /** Integer for a random game */
    public static int RANDOM_GAME = -1;
    
    /** Integer for number of cards in hand */
    public static int CARDS_IN_HAND = 5;
    
    /** Integer for starting points */
    public static int STARTING_POINTS = 100;
    
    /** Integer for new game points */
    public static int POINTS_FOR_NEW_GAME = 10;
    
    /** Integer for royal flush points */
    public static int ROYAL_FLUSH = 100;
    
    /** Integer for straight flush points */
    public static int STRAIGHT_FLUSH = 60;
    
    /** Integer for 4 of a kind points */
    public static int FOUR_OF_A_KIND = 50;
    
    /** Integer for full house points */
    public static int FULL_HOUSE = 40;
    
    /** Integer for flush points */
    public static int FLUSH = 30;
    
    /** Integer for straight points */
    public static int STRAIGHT = 25;
    
    /** Integer for 3 of a kind points */
    public static int THREE_OF_A_KIND = 15;
    
    /** Integer for 2 pairs points */
    public static int TWO_PAIRS = 10;
    
    /** Integer for 1 pair points */
    public static int ONE_PAIR = 7;
    
    /** Creates the Deck object */
    private Deck deck;
    
    /** Creates the Hand object */
    private Hand hand;
    
    /** Integer for the current total points */
    private int points;
    
    /**
     * Constructor for a new PokerModel game
     * @param seed is the shuffling number for testing
     */
    public PokerModel(int seed) {
        deck = new Deck(seed);
        points = STARTING_POINTS;
    }
    
    /**
     * The getPoints method returns the current points
     * @return the total points
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * The getCardFileName method gets the file name
     * for the image of the needed card to display.
     * @param index is the index of the card in hand
     * @return the file name of the card image
     */
    public String getCardFileName(int index) {
        String cardString = hand.getCard(index).toString();
        return "cards/" + cardString + ".gif";
    }
    
    /**
     * The getCard method returns the current card in hand
     * @param index is the index of the card
     * @return the current card in hand
     */
    public Card getCard(int index) {
        Card card = hand.getCard(index);
        return card;
    }
    
    /**
     * The newGame method begins a new game and resets deck
     * and points, as well as the hand.
     */
    public void newGame() {
        points -= POINTS_FOR_NEW_GAME;
        deck.shuffle();
        Card[] cards = new Card[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            cards[i] = deck.nextCard();
        }
        hand = new Hand(cards);
    }
    
    /**
     * The replaceCard method replaces the card at the index
     * with the next card in the deck.
     * @param index is the index of the replacable card
     */
    public void replaceCard(int index) {
        Card nextCard = deck.nextCard();
        hand.replace(index, nextCard);
    }
    
    /**
     * The scoreHand method gives a score for the current
     * hand, adds up total points, and returns a string
     * with the type of hand.
     * @return the string for the hand type
     */
    public String scoreHand() {
        if(hand.isRoyalFlush()) {
            points += ROYAL_FLUSH;
            return "Royal Flush";
        } else if(hand.isStraightFlush()) {
            points += STRAIGHT_FLUSH;
            return "Straight Flush";
        } else if(hand.hasFourOfAKind()) {
            points += FOUR_OF_A_KIND;
            return "Four of a Kind";
        } else if(hand.isFullHouse()) {
            points += FULL_HOUSE;
            return "Full House";
        } else if(hand.isFlush()) {
            points += FLUSH;
            return "Flush";
        } else if(hand.isStraight()) {
            points += STRAIGHT;
            return "Straight";
        } else if(hand.hasThreeOfAKind()) {
            points += THREE_OF_A_KIND;
            return "Three of a Kind";
        } else if(hand.hasTwoPairs()) {
            points += TWO_PAIRS;
            return "Two Pairs";
        } else if(hand.hasOnePair()) {
            points += ONE_PAIR;
            return "One Pair";
        } else {
            return "No Pair";
        }
    }
}