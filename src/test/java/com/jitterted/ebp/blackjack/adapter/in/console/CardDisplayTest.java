package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardDisplayTest {

    @Test
    public void displayTenAsString() throws Exception {
        Card card = new Card(Suit.CLUBS, Rank.TEN);

        assertThat(ConsoleCard.display(card))
                .isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    public void displayNonTenCardAsString() throws Exception {
        Card card = new Card(Suit.SPADES, Rank.TWO);

        assertThat(ConsoleCard.display(card))
                .isEqualTo("[30m┌─────────┐[1B[11D│2        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        2│[1B[11D└─────────┘");
    }
}
