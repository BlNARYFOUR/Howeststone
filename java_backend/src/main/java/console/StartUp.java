package console;

import game.*;

public class StartUp {
    public static void main(String[] args) {
        new StartUp().run();
    }

    private void run() {
        Game howeststone = new Game();
        System.out.println(howeststone.getHeroes());
        howeststone.setHero("Paladin");
        System.out.println(howeststone.getDecks());
        // TODO standard (random) deck ???
        // not yet full
        // howeststone.setDeck("Standard");


    }
}