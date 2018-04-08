from hand import Hand

class Player(object):
    
    def __init__(self):
        """
        Init method for a player.
        """
        self.score = 0
        self.hand = Hand()
            
    def getHand(self):
        #returns the hand, could be used with game engine to get hand
        return self.hand
    
    def changeHand(self, hand):
        #passes in new hand by game engine to change player hand
        self.hand = hand
        
    """
    The player just stores the hand... hand stores cards and you know
    This should theoretically be all this class needs...
    since the engine will control the moves and stuff
    """