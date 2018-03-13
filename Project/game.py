import os
from collections import deque
from deck import Deck
from hand import Hand

"""
CSC495 Team K: Alan Bishel, Christina Mara, Justin Mazzola
Python implementation of Crazy Eights

References:
    https://github.com/txt/plm18/blob/master/src/python/machines/machine.py
    https://github.com/timm/sandbox/blob/master/games.py
"""

class Game(object):
    
    def __init__(self):
        # Initialize game object
        self.deck = Deck()
        self.players = {}
        self.discard = deque()
        self.current_card = None
        self.current_suit = None

    def start(self):
        # Initialize the players
        player1 = Hand()
        player2 = Hand()
        self.players = [player1, player2]
        # Shuffle the deck and draw the first card.
        # self.deck.shuffle()
        self.current_card = self.deck.draw()
        self.current_suit = self.current_card.suit
        for _ in range(0, 5):
            self.players[0].append(self.deck.draw())
            self.players[1].append(self.deck.draw())

    def print_cli(self, player):  
        os.system("clear")
        player_no = self.players.index(player) + 1
        print("Player: %d" % player_no)
        print("Current Suit: %s Current Card: %s\n" % (self.current_suit, self.current_card))
        print("Your Hand:")
        for card in player.cards:
            print("%d: %s" % (player.cards.index(card), card))

    def turn(self, player):
        valid_play = False
        self.print_cli(player)
        while not valid_play:
            play = input("\nEnter \'D\' to draw a card, or enter the number of the card to play. ")
            if play is 'D':
                player.append(self.deck.draw())
                if len(self.deck) is 0:
                    return True
            elif str.isdigit(play) and int(play) <= len(player):
                played_card = player.cards[int(play)]
                # ToDo: Add logic for opponent plays a suit different from the current one after playing an 8
                # if player.cards[play].rank is 8:
                #     while not valid_play:
                #         self.print_cli(player)
                #         suit = input("You played an 8! What suit (C, D, H, S) would you like to choose?" )
                #         if suit in suits:
                #             valid_play = True
                #             self.current_suit = suit
                if played_card.suit == self.current_suit or played_card.rank == self.current_card.rank:
                    valid_play = True
                    player.remove_card(played_card)
                    self.current_card = played_card
                    self.discard.append(played_card)
                    self.current_suit = played_card.suit
                    if len(player) == 0:
                        return True
                else:
                    self.print_cli(player)
            else:
                self.print_cli(player)
            self.print_cli(player)
        return False

    def run(self):
        self.start()
        game_end = False
        while not game_end:
            for player in self.players:
               if game_end:
                   break
               else:
                   game_end = self.turn(player)
                
        os.system('clear')
        if len(self.deck) == 0:
            # Calculate card scores and decide winner.
            print("Game over!")
        elif len(self.players[0].cards) == 0:
            print("Game over! Player 1 wins!")
        elif len(self.players[1].cards) == 0:
            print("Game over! Player 2 wins!")
        else:
            print("Game over! Something went wrong!")


def main():
    crazy_eights = Game()
    crazy_eights.run()

if __name__ == '__main__':
    main()