from ranks import ranks
from suits import suits
from card import Card
from deck import Deck
from hand import Hand
from player import Player

class Game(object):
    
    def __init__(self):
        #initializes game object
        #sets everything up
        print("hi")
        #creates and shuffles deck
        deck = Deck()
        deck.shuffle()
        
        #creates players
        player1 = Player() 
        player2 = Player()
        
        #deals cards
        deal()
        
        #starts game
        controller()
        
    def deal(self):
        #deal out initial hands of 5 cards to each player
        
        #player 1
        hand = Hand()
        for x in range(0, 5):
            card = deck.draw()
            hand.append(card)
        player1.changeHand(hand)
        
        #player2
        hand = Hand()
        for x in range(0, 5):
            card = deck.draw()
            hand.append(card)
        player2.changeHand(hand)
        
    def controller(self):
        #this is the game controller
        #could also be where we put the machine and stuff
        print()