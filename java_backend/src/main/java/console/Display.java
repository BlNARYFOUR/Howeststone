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
        final List<String> HEROES = new ArrayList<>(Arrays.asList(howeststone.getHeroNames()));

        System.out.println("Select one of the following heroes:");
        System.out.println(formatList(HEROES));
        String selectedHero = askInputUntilFoundInList(HEROES);
        howeststone.setHero(selectedHero);
    }

    private void chooseDeck(Game howeststone) {
        final List<String> DECKS = new ArrayList<>(howeststone.getDeckNames());

        System.out.println("Select one of the following decks:");
        System.out.println(formatList(DECKS));
        String selectedDeck = askInputUntilFoundInList(DECKS);
        howeststone.setDeck(selectedDeck);
    }

    private void startGame(Game howeststone) {
        // TODO check if Player has hero and deck
        // TODO make enemy
        System.out.println(ColorFormats.red("Match Settings: Player: ") + ColorFormats.green(howeststone.getYou().getHero().getHeroName()));
        int beginnerIndex = (int) Math.round(Math.random());

        if (beginnerIndex == 1) {

        }
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
}