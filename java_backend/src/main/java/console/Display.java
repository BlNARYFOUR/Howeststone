
package console;

import cards.Card;
import cards.CardCollection;
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
            System.out.println("You begin the game");
        } else {
            howeststone.setActivePlayer("Enemy");
            System.out.println("Enemy begins the game");
        }

        replaceCards(howeststone);
    }

    private void replaceCards(Game howeststone) {
        List<String> replaceCardList = new ArrayList<>();
        if (howeststone.getActivePlayer().equals("You")){
            for (int i = 0; i < 3 ; i++){
                replaceCardList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
            }
        }else {
            for (int i = 0; i < 4 ; i++){
                replaceCardList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
            }
        }
        replaceCardList.add("stop");
        System.out.println("Select which one(s) you want to switch:");
        System.out.println(formatList(replaceCardList));
        List<String> replace = askInputUntilStop(replaceCardList);
        // TODO replace or not
        // - cardInfo // on one line

        System.out.println("replace" + replace);
        howeststone.getYou().getDeck().addCards(replace);
        for (String cardID: replace) {
            replaceCardList.remove(cardID);
            replaceCardList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
        }
        replaceCardList.remove("stop");
        System.out.println("replaceCardList" + replaceCardList);
        System.out.println(howeststone.getYou().getDeck());

        // howeststone.getYou().setCardsInHand(replace);

        if (howeststone.getActivePlayer().equals("You")){
            // add 3/4 cards to hand
            howeststone.setTurnTime(50);
        }else {
            for (String card: replaceCardList) {
                //TODO add card to hand enemy
            }
            // replace cards ?
            System.out.println(howeststone.getEnemy().getCardsInHand());
            enemyTurn(howeststone);
        }
    }

    private void enemyTurn(Game howeststone) {
        // check deze code zeker
        howeststone.getEnemy().getCardsInHand().addCard(Integer.parseInt(howeststone.getYou().getDeck().drawCard().toString()));
        howeststone.getEnemy().setMana(howeststone.getEnemy().getMana()+1);

        // enemy speelt altijd duurste kaarten eerst:
        Card mostExpensiveCard = howeststone.getEnemy().getCardsInHand().getMostExpensiveCard();
        while (howeststone.getManaEnemy() >= mostExpensiveCard.getManaCost()) {
            playCard(mostExpensiveCard, howeststone);
            mostExpensiveCard = howeststone.getEnemy().getCardsInHand().getMostExpensiveCard();
            printGame(howeststone);
        }
        //TODO use heroPower and attack with minions
        // endTurn();
        howeststone.setActivePlayer("You");
        System.out.println(howeststone.getEnemy().getCardsOnPlayingField());

    }

    private void printGame(Game howeststone) {
        System.out.println(ColorFormats.red("weapon/hero/mana/hand Enemy: ") + ColorFormats.green("NYI"));
        System.out.println(ColorFormats.red("Playing Field Enemy: ") + ColorFormats.green(howeststone.getEnemy().getCardsOnPlayingField().toString()));
        System.out.println(ColorFormats.red("Playing Field Player: ") + ColorFormats.green(howeststone.getYou().getCardsOnPlayingField().toString()));
        System.out.println(ColorFormats.red("weapon/hero/mana/hand Player: ") + ColorFormats.green("NYI"));
    }


    private void yourTurn(Game howeststone) {
        // TODO change because cannot be defined here
        List<String> cardsInHand= new ArrayList<>();
        cardsInHand.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));

        // info
        // cardssInPlayingField
        // cardsInHand
    }

    private void playCard(Card card, Game howeststone) {
        CardCollection areaPlayingField = howeststone.getYou().getCardsOnPlayingField();
        if ((howeststone.getActivePlayer()).equals("Enemy")) {
            areaPlayingField = howeststone.getEnemy().getCardsOnPlayingField();
        }
        if (areaPlayingField.getCards().size() < 7) {
            areaPlayingField.addCard(Integer.parseInt(card.toString()));
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
        System.out.println(ColorFormats.red("Selected: ") + ColorFormats.green(formatList(replace)));
        return replace;
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