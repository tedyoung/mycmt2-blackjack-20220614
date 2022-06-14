package com.jitterted.ebp.blackjack;

public class ConsoleHand {

    // CONVERT DOMAIN object (Hand) to CONSOLE display String
    static String displayFaceUpCard(Hand hand) {
        return ConsoleCard.display(hand.faceUpCard());
    }
}
