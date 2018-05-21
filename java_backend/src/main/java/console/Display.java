
package console;

import console.formatters.ColorFormats;
import game.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Display {
    private Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) {
        new Display().run();
    }

    private void run() {
        Game howeststone = new Game();
        chooseHero(howeststone);
        chooseDeck(howeststone);
        startGame(howeststone);
        
        //howeststone.setDeck("Standard");
        //System.out.println(howeststone.getDeck());
        //Player you = new Player(howeststone.getHero());
        //GameBoard gb = new GameBoard(you, howeststone.getDeck());
        //System.out.println(gb);
    }

    private void chooseHero(Game howeststone) {
        Player you = new Player();
        final List<String> HEROES = new ArrayList<>(Arrays.asList(howeststone.getHeroNames()));

        System.out.println("Select one of the following heroes:");
        System.out.println(formatList(HEROES));
        String selectedHero = askInputUntilFoundInList(HEROES);
        you.setHero(selectedHero);
        howeststone.addPlayer(you);
    }

    private void chooseDeck(Game howeststone) {
        newLine();
        if (howeststone.getYou().getHero() == null){
            throw new NullPointerException();
        }

        final List<String> DECKS = new ArrayList<>(howeststone.getYou().getHero().getDeckNames());

        System.out.println("Select one of the following decks:");
        System.out.println(formatList(DECKS));
        String selectedDeck = askInputUntilFoundInList(DECKS);
        howeststone.setDeck(selectedDeck);
    }

    private void startGame(Game howeststone) {
        if (howeststone.getYou().getHero() == null || howeststone.getYou().getDeck() == null) {
            //TODO get out of this function not exception
            throw new NullPointerException();
        }
        newLine();
        final List<String> HEROES = new ArrayList<>(Arrays.asList(howeststone.getHeroNames()));
        int randomHeroIndex = (int)Math.round(Math.random())*(HEROES.size()-1);
        Player enemy = new Player();

        enemy.setHero(HEROES.get(randomHeroIndex));
        enemy.setDeck("Standard");
        howeststone.addBot(enemy);
        howeststone.setTime(50);

        System.out.println(howeststone);
        howeststone.shuffleDecks();
        flipCoin(howeststone);
    }

    private void flipCoin(Game howeststone) {
        newLine();
        String playerThatGetsCoin = "";
        String playerThatBegins = "";

        // TODO change cannot be strings
        if (Math.random() < 0.5) {
            playerThatGetsCoin = "Player";
            playerThatBegins = "Enemy";

        } else {
            playerThatGetsCoin = "Enemy";
            playerThatBegins = "Player";
        }
        System.out.println(playerThatBegins + " starts the game and "
                + playerThatGetsCoin + " gets the coin");

        replaceCards(howeststone);
    }

    private void replaceCards(Game howeststone) {
        // TODO 3 or 4 times
        // TODO IndexOutOfBoundsException

        System.out.println(howeststone.getYou().getDeck().drawCard());
        System.out.println(howeststone.getYou().getDeck().drawCard());
        System.out.println(howeststone.getYou().getDeck().drawCard());
        // TODO replace or not
        // - cardInfo // on one line

        // 
    }

    private String askInputUntilFoundInList(List<String> list) {
        String input;

        do {
            System.out.print("Input here: ");
            input = scanner.nextLine();

            if(!list.contains(input)) {
                System.out.println(ColorFormats.red("This is not an option!"));
                System.out.println("The available options are:");

                System.out.println(formatList(list));
            }
        }
        while(!list.contains(input));

        System.out.println(ColorFormats.red("Selected: ") + ColorFormats.green(input));

        return input;
    }

    @NotNull
    private String formatList(@NotNull List<String> list) {
        StringBuilder strBuilder = new StringBuilder();

        for(String str : list) {
            strBuilder.append("\t- ");
            strBuilder.append(str);
            strBuilder.append('\n');
        }

        return  strBuilder.toString();
    }
    private void newLine(){
        System.out.println();
    }
}