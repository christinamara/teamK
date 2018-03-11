from card import Card

class Hand(object):

    def __init__(self):
        """
        Init method for a hand.
        A hand is a container class for a list of cards that can be operated upon.
        """
        self.cards = []
    
    def __len__(self):
        """
        Returns the number of cards in a hand.
        :return: length of the list of cards in a hand
        """
        return len(self.cards)

    def append(self, card):
        """
        Add a card to the list of cards in the hand.
        :return: None
        """
        if isinstance(card, Card):
            self.cards.append(card)
    
    def has_card(self, card):
        """
        Method checks if hand contains a card.
        :return: True if the hand contains a card
        """
        for c in self.cards:
            if c == card:
                return True
        return False
    
    def remove_card(self, card):
        """
        Method removes a card from a hand and returns it, returns None otherwise.
        :return: the removed card from the hand
        """
        if self.has_card(card):
            self.cards.remove(card)
            return card
        else:
            return None



    
