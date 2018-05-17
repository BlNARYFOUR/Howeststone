package console;

import game.*;

import java.util.Scanner;

public class StartUp {
    public static void main(String[] args) {
        new StartUp().run();
    }

    private void run() {
        Game howeststone = new Game();
        System.out.println(howeststone.getHeroes());
        howeststone.setHero("Paladin");
        System.out.println(howeststone.getDecks());
        // TODO standard (random) deck ??? need db for that
        // not yet full
        howeststone.setDeck("Standard");
        //System.out.println(howeststone.getDeck()
        Player you = new Player(howeststone.getHero());
        GameBoard gb = new GameBoard(you, howeststone.getDeck());
        System.out.println(gb);

    }
}