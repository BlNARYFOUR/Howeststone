package game;

import cards.*;
import hero.*;


public class GameBoard {
    private Player player;
    private Player enemy;
    /* does this have to be here
    private CardCollection playerDeck;
    private CardCollection enemyDeck;
    */
    private int turnTime;

    public GameBoard(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
        this.turnTime = 50;
        beginGame();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }

    private void beginGame() {
        if (Math.random() > 0.5){
            // draw 3 cards (from your deck)
            // replace cards ?
            // give enemy 4 cards
            // give enemy coin
            // your turn first
        } else {
            // draw 4 cards (from your deck)
            // replace cards ?
            // give enemy 3 cards
            // give coin to you
            // enemy turn
        }
    }
// constructor with player (enemy is chosen random)
    // method end turn

    private void giveTurn(){

    }
}
