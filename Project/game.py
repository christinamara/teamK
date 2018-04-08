import os
from collections import deque
from deck import Deck
from hand import Hand
from suits import suits

"""
CSC495 Team K: Alan Bishel, Christina Mara, Justin Mazzola
Python implementation of Crazy Eights/Bartok

References:
    https://github.com/txt/plm18/blob/master/src/python/machines/machine.py
    https://github.com/timm/sandbox/blob/master/games.py
"""

class Game(object):
    
    def __init__(self):
        """
        Initialize game object
        :return: None
        """
        self.deck = Deck()
        self.players = {}
        self.discard = deque()
        self.current_card = None
        self.current_suit = None

    def start(self):
        """
        Begin the game
        :return: None
        """
        # Initialize the players
        player1 = Hand()
        player2 = Hand()
        self.players = [player1, player2]
        # Shuffle the deck and draw the first card.
        self.deck.shuffle()
        self.current_card = self.deck.draw()
        self.current_suit = self.current_card.suit
        for _ in range(0, 5):
            self.players[0].append(self.deck.draw())
            self.players[1].append(self.deck.draw())

    def print_cli(self, player):  
        """
        Print the command line interface.
        :return: None
        """
        os.system("clear")
        player_no = self.players.index(player) + 1
        print("Player: %d" % player_no)
        if self.current_card == 0: print("Current Suit: %s" % self.current_suit)
        else: print("Current Card: %s\n" % (self.current_card))
        print("Your Hand:")
        for card in player.cards:
            print("%d: %s" % (player.cards.index(card), card))

    def turn(self, player):
        """
        Complete a player's turn
        :return: True if game ending condition
        """
        valid_play = False
        self.print_cli(player)
        while not valid_play:
            play = input("\nEnter \'D\' to draw a card, or enter the number of the card to play. ")
            if play is 'D' or play is 'd':
                player.append(self.deck.draw())
                self.print_cli(player)
                if len(self.deck) is 0:
                    return True
            elif str.isdigit(play) and int(play) <= len(player):
                played_card = player.cards[int(play)]
                if player.cards[int(play)].rank is 8:
                    suit = input("You played an 8! What suit (C, D, H, S) would you like to choose?" ).upper()
                    if suit in suits:
                        valid_play = True
                        self.current_suit = suit
                        self.current_card = 0
                elif played_card.suit == self.current_suit or played_card.rank == self.current_card.rank:
                    valid_play = True
                    player.remove_card(played_card)
                    self.current_card = played_card
                    self.discard.append(played_card)
                    self.current_suit = played_card.suit
                    if len(player) == 0:
                        return True
                else:
                    print("Invalid play, try again.")
                    self.print_cli(player)
            else:
                self.print_cli(player)
            #self.print_cli(player)
        return False

    def run(self):
        """
        Run the game of crazy eights.
        :return: None
        """
        self.start()
        game_end = False
        while not game_end:
            for player in self.players:
                if game_end:
                    break
                else:
                    game_end = self.turn(player)
                
        #os.system('clear')
        if len(self.deck) == 0:
            # Calculate card scores and decide winner.
            print("Game over! Deck empty!")
        elif len(self.players[0].cards) == 0:
            print("Game over! Player 1 wins!")
        elif len(self.players[1].cards) == 0:
            print("Game over! Player 2 wins!")
        else:
            print("Game over! Something went wrong!")


def main():
    """ Main method """
    crazy_eights = Game()
    crazy_eights.run()

if __name__ == '__main__':
    main()
    
"""
Notes:
You must use uppercase D to draw, could this be case insensitive?
Is there a way to not need '' around each input as failing that crashes the game?
Maybe some feedback for an invalid move?
"""