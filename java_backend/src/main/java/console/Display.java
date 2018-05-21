
package console;

import cards.Card;
import console.formatters.ColorFormats;
import game.*;
import org.jetbrains.annotations.NotNull;
import java.util.*;

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
        
        //howeststone.setYourDeck("Standard");
        //System.out.println(howeststone.getDeck());
        //Player you = new Player(howeststone.getYourHero());
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
        howeststone.addYou(you);
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
        howeststone.setYourDeck(selectedDeck);
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
        howeststone.addEnemy(enemy);
        howeststone.setTurnTime(50);

        System.out.println(howeststone);
        howeststone.shuffleDecks();
        flipCoin(howeststone);
    }

    private void flipCoin(Game howeststone) {
        newLine();

        // TODO change cannot be strings

        // change to boolean
        Random rand = new Random();
        boolean doYouBegin = rand.nextBoolean();
        if (doYouBegin) {
            howeststone.setActivePlayer("You");
            System.out.println(ColorFormats.magenta("You begin the game"));
        } else {
            howeststone.setActivePlayer("Enemy");
            System.out.println(ColorFormats.yellow("Enemy begins the game"));
        }

        replaceCards(howeststone);
    }

    private void replaceCards(Game howeststone) {
        List<String> yourCardsInHandList = new ArrayList<>();
        List<String> enemyCardsInHandList = new ArrayList<>();
        for (int i = 0; i < 3 ; i++){
            yourCardsInHandList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
            enemyCardsInHandList.add(String.valueOf(howeststone.getEnemy().getDeck().drawCard()));
        }
        if (howeststone.getActivePlayer().equals("You")){
            enemyCardsInHandList.add(String.valueOf(howeststone.getEnemy().getDeck().drawCard()));
        }else {
            yourCardsInHandList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
        }
        yourCardsInHandList.add("stop");
        System.out.println("Select which one(s) you want to switch:");
        System.out.println(formatList(yourCardsInHandList));
        // - cardInfo // on one line
        List<String> replace = askInputUntilStop(yourCardsInHandList);

        howeststone.getYou().getDeck().addCards(replace);
        for (String cardID: replace) {
            yourCardsInHandList.remove(cardID);
            yourCardsInHandList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
        }
        yourCardsInHandList.remove("stop");
        howeststone.getEnemy().setCardsInHand(enemyCardsInHandList);
        howeststone.getYou().setCardsInHand(yourCardsInHandList);
        if (howeststone.getActivePlayer().equals("You")){
            yourTurn(howeststone);
        }else {
            enemyTurn(howeststone);
        }
    }

    private void enemyTurn(Game howeststone) {
        //TODO als het niet de eerste turn is, howeststone.getEnemy().getCardsInHand().addCard(drawCard())

        /*String cheapestCard = howeststone.getEnemy().getCardsInHand().getCheapestCard();
        if (howeststone.getEnemy().getMana() >= cheapestCard) {
            enemyPlay(cheapestCard);
        } else {
            howeststone.endTurn();
        }*/
    }

    private void yourTurn(Game howeststone) {
        // TODO change because cannot be defined here
        howeststone.getYou().getCardsInHand().addCard(howeststone.getYou().getDeck().drawCard().getCardID());
        System.out.println(formatCardList(howeststone.getYou().getCardsInHand().getCards()));
        // info
        // cardssInPlayingField
        // cardsInHand
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


    private List<String> askInputUntilStop(List<String> list) {
        String input;
        List<String> replace = new ArrayList<>();
        do {
            System.out.print("Input here: ");
            input = scanner.nextLine();

            if(list.contains(input)) {
                if (replace.contains(input)){
                    replace.remove(input);
                } else {
                    replace.add(input);
                }
                // change ? remove and add ?

                System.out.println("Select which one(s) you want to switch:");
                System.out.println(formatList(list));
                System.out.println("Select which one(s) you don't want to switch:");
                System.out.println(formatList(replace));
            }
        }
        while(!input.equals("stop"));
        replace.remove("stop");
        System.out.println(ColorFormats.red("Selected: ") + formatList(replace));
        return replace;
    }
    @NotNull
    private String formatCardList(@NotNull List<Card> list) {
        StringBuilder strBuilder = new StringBuilder();

        for(Card str : list) {
            strBuilder.append("\t- ");
            strBuilder.append(str);
            strBuilder.append('\n');
        }

        return  strBuilder.toString();
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