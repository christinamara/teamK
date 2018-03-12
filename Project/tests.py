import unittest
from card import Card
from hand import Hand
from deck import Deck

"""
Unit tests for card class.
To run all tests, use the following command:
    'python3 -m unittest'
"""

class TestCard(unittest.TestCase):

    def test_init(self):
        card_1 = Card(2, "C")
        self.assertEqual(2, card_1.rank)
        self.assertEqual("C", card_1.suit)
    
    def test_value(self):
        card_1 = Card(2, "C")
        card_2 = Card("J", "C")
        card_3 = Card("Q", "C")
        card_4 = Card("K", "C")
        card_5 = Card("A", "C")

        self.assertEqual(0, card_1.value())
        self.assertEqual(9, card_2.value())
        self.assertEqual(10, card_3.value())
        self.assertEqual(11, card_4.value())
        self.assertEqual(12, card_5.value())
    
    def test_operators(self):
        card_1 = Card(2, "C")
        card_2 = Card(3, "C")

        self.assertTrue(card_1 == Card(2, "C"))
        self.assertTrue(card_1 < card_2)
        self.assertTrue(card_1 != card_2)
        self.assertTrue(card_2 > card_1)
        self.assertTrue(card_1 <= card_2)
        self.assertTrue(card_1 <= Card(2, "D"))
        self.assertTrue(card_2 >= Card(3, "D"))
        self.assertTrue(card_1 <= card_2)
    
    def test_str(self):
        card_1 = Card(2, "C")
        card_2 = Card("A", "S")
        self.assertEqual(card_1.__str__(), "Card(2, C)")
        self.assertEqual(card_2.__str__(), "Card(A, S)")

class TestHand(unittest.TestCase):
   
    def test_card(self):
        
        # Empty hand
        test_hand = Hand()
        self.assertEqual(0, test_hand.__len__())
        
        # Add a 2 of Clubs
        test_hand.append(Card(2, "C"))
        self.assertEqual(1, test_hand.__len__())
        
        # Add an Ace of Spades
        test_hand.append(Card("A", "S"))
        self.assertEqual(2, test_hand.__len__())
       
        # Check for an Ace of Spades
        self.assertTrue(test_hand.has_card(Card("A", "S")))
       
        # Check for a Jack of Diamonds
        self.assertFalse(test_hand.has_card(Card("J", "D")))
        
        # Remove an Ace of Spades
        test_hand.remove_card(Card("A", "S"))
        self.assertEqual(1, test_hand.__len__())
        
        # Remove a card that isn't in the hand
        removed_card = test_hand.remove_card(Card("J", "D"))
        self.assertEqual(None, removed_card)
        self.assertEqual(1, test_hand.__len__())

class TestDeck(unittest.TestCase):

    def test_deck(self):
        # New deck
        test_deck = Deck()
        self.assertEqual(52, len(test_deck))

        # Draw top card, an Ace of Spades
        card_1 = test_deck.draw()
        self.assertEqual(card_1, Card("A", "S"))
        self.assertEqual(51, len(test_deck))
        # Draw next card, a King of Spades

        card_2 = test_deck.draw()
        self.assertEqual(card_2, Card("K", "S"))
        self.assertEqual(50, len(test_deck))
    
    def test_shuffle(self):
        
        # Two new decks, shuffle one
        deck_1 = Deck()
        deck_2 = Deck()
        deck_2.shuffle()

        # The likelihood of shuffling a deck in order is really, really, really, damn small.
        # Compare the two, if they're the same, then go buy a lottery ticket.
        self.assertNotEqual(str(deck_1), str(deck_2))


