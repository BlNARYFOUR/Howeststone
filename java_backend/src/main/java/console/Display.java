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
        final List<String> HEROES = new ArrayList<>(Arrays.asList(howeststone.getHeroNames()));

        System.out.println("Select one of the following heroes:");
        System.out.println(formatList(HEROES));
        String selectedHero = askInputUntilFoundInList(HEROES);
        howeststone.setHero(selectedHero);

        System.out.println(howeststone.getDeckNames());
        // TODO standard (random) deck ???
        // not yet full
        howeststone.setDeck("Standard");
        //System.out.println(howeststone.getDeck()
        Player you = new Player(howeststone.getHero());
        GameBoard gb = new GameBoard(you, howeststone.getDeck());
        System.out.println(gb);

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
    };

    @NotNull
    private String formatList( List<String> list) {
        StringBuilder strBuilder = new StringBuilder();

        for(String str : list) {
            strBuilder.append("\t- ");
            strBuilder.append(str);
            strBuilder.append('\n');
        }

        return  strBuilder.toString();
    }
}