import java.util.*;

/**
 * The Card class creates individual cards with values and suits. In
 * other classes of the Poker program, arrays of Cards are used.
 * @author Justin Mazzola
 */
public class Card implements Comparable<Card> {
	/** Number on the card */
	private int value;

    /** Suit of the card */
    private char suit;

	/** Character for the club suit */
	public static final char CLUBS = 'c';

	/** Character for the diamond suit */
	public static final char DIAMONDS = 'd';

	/** Character for the spade suit */
	public static final char SPADES = 's';

	/** Character for the heart suit */
	public static final char HEARTS = 'h';

	/** Lowest number on a card */
	public static final int LOWEST_VALUE = 2;

	/** Highest value of a card (ace) */
	public static final int HIGHEST_VALUE = 14;

	/**
	 * Constructor for the Card object
	 * @param value is the number on the card
	 * @param suit is the suit of the card
	 */
	public Card(int value, char suit) {
		this.value = value;
		this.suit = suit;
	}

	/**
	 * The getSuit method returns the suit of the card
	 * @return suit of the card
	 */
	public char getSuit() {
		return suit;
	}

	/**
	 * The getValue method returns the value of the card
	 * @return the value of the card
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * The toString method converts a card to a string and returns it
	 * @return the readable form of the card
	 */
	public String toString() {
		String s = "" + suit;
		s = s.toLowerCase();
		return s + value;
	}
	
	/**
	 * The equals method checks if two cards are equal to each other in suit and value,
	 * or if an object is even a card.
	 * @param o is the object to be compared with
	 * @return the equality of two objects
	 */
	public boolean equals(Object o) {
		if (o instanceof Card) {
			Card other = (Card)o;
			if (value == other.value && suit == other.suit) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
     * This method is used for sorting the cards in a player's hand in a game of
     * Poker. Cards are sorted first by value, then by suit.
     * 
     * @param other
     *            The Card object to which this Card is being compared.
     * @return negative value if this Card should be before the other Card,
     *         positive value if this Card should be after the other Card.
     */
    public int compareTo(Card other) {
        if (this.value != other.value) {
            return this.value - other.value;
        } else {
            return this.suit - other.suit;
        }
    }
}
