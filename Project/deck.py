from collections import deque
import random
from card import Card
from ranks import ranks
from suits import suits

class Deck(object):
    
    def __init__(self):
        """
        Init method for a deck. A deck is a collection of 52 card objects representing a playing card deck.
        A deck is initalized by iterating over each suit, and each rank and adding a card to the deck.
        """
        self.cards = deque()
        for s in suits:
            for r in ranks:
                self.cards.append(Card(r, s))

    def shuffle(self):
        """
        Shuffles the deck of cards
        :return: None
        """
        random.shuffle(self.cards)

    def draw(self):
        """
        Returns the top card of the deck.
        :return: Pop a card from the deck and return it
        """
        return self.cards.pop()

    def __len__(self):
        """
        Returns the number of remaining cards in the deck.
        :return: length of the cards deque
        """
        return len(self.cards)
