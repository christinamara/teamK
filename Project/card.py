from ranks import ranks
from suits import suits

class Card(object):

    def __init__(self, rank, suit):
        """
        Init method for a card.
        """
        if rank in ranks and suit in suits:
            self.rank = rank
            self.suit = suit
        else:
            raise TypeError("Error while creating card")
    
    def value(self):
        """
        Numerical value of a card
        :return: The index of the cards rank in ranks
        """
        return ranks.index(self.rank)

    def __eq__(self, card):
        """
        Equals operator
        :return: True if two cards are equal.
        """
        if type(self) != type(card):
            return False
        return self.rank == card.rank and self.suit == card.suit

    def __ne__(self, other):
        """
        Not-equals operator
        :return: True if two cards are not equal.
        """
        return not self.__eq__(other)

    def __gt__(self, card):
        """
        Greater-than operator
        :return: True if self has a greater value than card
        """
        return self.value() > card.value()

    def __lt__(self, card):
        """
        Less-than operator
        :return: True if self has a lesser value than card
        """
        return self.value() < card.value()

    def __ge__(self, card):
        """
        Greater-than, or equal, operator
        :return: True if self has a greater than, or equal value to card
        """
        return self.value() >= card.value()

    def __le__(self, card):
        """
        Less-than, or equal, operator
        :return: True if self has a lesser or equal value than card
        """
        return self.value() <= card.value()

    def __repr__(self):
        """
        Returns a string representation of a card in the format "Card(<rank>, <suit>)"
        :return: str representation of a card
        """
        return "Card(" + str(self.rank) + ", " + str(self.suit) + ")"

    def __str__(self):
        """
        Returns a string representation of a card using __repr__
        :return: str representation of a card
        """
        return self.__repr__()