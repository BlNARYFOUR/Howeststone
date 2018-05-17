package console;

import game.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StartUp {
    private Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) {
        new StartUp().run();
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
        // howeststone.setDeck("Standard");
        // System.out.println(howeststone.getDeck());
    }

    private String askInputUntilFoundInList(List<String> list) {
        String input;

        do {
            System.out.print("Input here: ");
            input = scanner.nextLine();

            if(!list.contains(input)) {
                System.out.println("\033[31;1mThis is not an option!");
                System.out.println("\033[0mThe available options are:");

                System.out.println(formatList(list));
            }
        }
        while(!list.contains(input));

        System.out.println("\033[31;1mSelected: \033[32m" + input + "\033[0m");

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