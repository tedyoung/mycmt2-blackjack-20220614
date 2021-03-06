package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void playerHitsAndGoesBustAndLoses() throws Exception {
        Game game = new Game(StubDeck.playerHitsAndGoesBust());
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    public void playerDealtBetterHandThanDealerAndStandsThenPlayerBeatsDealer() throws Exception {
        Game game = new Game(StubDeck.playerStandsAndBeatsDealer());
        game.initialDeal();

        game.playerStands();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void playerDealtBlackjackUponInitialDealThenWinsBlackjack() throws Exception {
        Game game = new Game(StubDeck.playerDealtBlackjack());

        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isTrue();
        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);
    }

    @Test
    public void playerIsNotDoneUponNonBlackjackInitialDeal() throws Exception {
        Game game = new Game(new StubDeck(Rank.TEN, Rank.EIGHT,
                                          Rank.FOUR, Rank.JACK));

        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isFalse();
    }

    @Test
    public void playerGetsTo21NotBlackjackThenOnlyBeatsDealer() throws Exception {
        Game game = new Game(new StubDeck(Rank.TEN, Rank.EIGHT,
                                          Rank.FOUR, Rank.JACK,
                                          Rank.SEVEN));
        game.initialDeal();

        game.playerHits();
        game.playerStands();

        assertThat(game.determineOutcome())
                .isNotEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);
    }

    @Test
    public void standResultsInDealerDrawingCard() throws Exception {
        Deck dealerBeatsPlayerAfterDrawingAdditionalCardDeck =
                new StubDeck(Rank.TEN,  Rank.QUEEN,
                             Rank.NINE, Rank.FIVE,
                             Rank.SIX);
        Game game = new Game(dealerBeatsPlayerAfterDrawingAdditionalCardDeck);
        game.initialDeal();

        game.playerStands();

        assertThat(game.dealerHand().cards())
                .hasSize(3);
    }


}


