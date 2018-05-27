
package console;

import cards.Card;
import cards.CardCollection;
import formatters.ColorFormats;
import game.Game;
import game.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Display {
    private static final String LIST_ITEM_STR = "\t- ";
    private static final String SELECT_WHAT_YOU_WANT_TO_DO_STR = "Select what you want to do:";
    private static final String SELECTED_STR = "Selected: ";
    private static final String INPUT_HERE_STR = "Input here: ";
    private static final String NYI_STR = "NYI";
    private static final String STOP_STR = "stop";
    private static final String YOU_STR = "you";

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Display().run();
    }

    private void run() {
        final Game howeststone = new Game();
        chooseHero(howeststone);
        chooseDeck(howeststone);
        startGame(howeststone);

        //HOWESTSTONE.setYourDeck("Standard");
        //System.out.println(HOWESTSTONE.getDeck());
        //Player you = new Player(HOWESTSTONE.getYourHero());
        //GameBoard gb = new GameBoard(you, HOWESTSTONE.getDeck());
        //System.out.println(gb);
    }

    private void chooseHero(Game howeststone) {
        final Player you = new Player();
        final List<String> heroes = new ArrayList<>(howeststone.getHeroNames());

        System.out.println("Select one of the following heroes:");
        final String selectedHero = askInputUntilFoundInList(heroes);
        you.setHero(selectedHero);
        howeststone.addYou(you);
    }

    private void chooseDeck(Game howeststone) {
        newLine();
        if (howeststone.getYou().getHero() == null) {
            throw new NullPointerException();
        }

        final List<String> decks = new ArrayList<>(howeststone.getDeckNames());
        System.out.println(ColorFormats.magenta("Check this: " + decks));
        System.out.println("Select one of the following decks:");
        //final String selectedDeck = askInputUntilFoundInList(decks);

        //howeststone.setYourDeck(selectedDeck);
    }

    private void startGame(Game howeststone) {
        if (howeststone.getYou().getHero() == null || howeststone.getYou().getDeck() == null) {
            //TODO get out of this function not exception
            throw new NullPointerException();
        }
        howeststone.generateEnemy();
        //howeststone.setTurnTime(50);
        System.out.println(howeststone);
        howeststone.shuffleDecks();
        flipCoin(howeststone);
    }

    private void flipCoin(Game howeststone) {
        newLine();

        // TODO change cannot be strings

        // change to boolean
        final Random rand = new Random();
        final boolean doYouBegin = rand.nextBoolean();
        if (doYouBegin) {
            howeststone.setActivePlayer(YOU_STR);
            System.out.println(ColorFormats.magenta("You begin the game!"));
        } else {
            howeststone.setActivePlayer("enemy");
            System.out.println(ColorFormats.yellow("Enemy begins the game!"));
        }

        replaceCards(howeststone);
    }

    private void replaceCards(Game howeststone) {
        final List<String> yourCardsInHandList = new ArrayList<>();
        final List<String> enemyCardsInHandList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            yourCardsInHandList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
            enemyCardsInHandList.add(String.valueOf(howeststone.getEnemy().getDeck().drawCard()));
        }
        if (howeststone.getActivePlayer().equals(YOU_STR)) {
            enemyCardsInHandList.add(String.valueOf(howeststone.getEnemy().getDeck().drawCard()));
            System.out.println("Enemy gets a card" + enemyCardsInHandList);
        } else {
            yourCardsInHandList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
        }


        // - cardInfo // on one line
        final List<String> replaceCardList = askInputUntilStop(yourCardsInHandList);

        // TODO howeststone.getYou().getDeck().addCards(replaceCardList);
        for (String cardID: replaceCardList) {
            yourCardsInHandList.remove(cardID);
            yourCardsInHandList.add(String.valueOf(howeststone.getYou().getDeck().drawCard()));
        }
        replaceCardList.remove(STOP_STR);
        System.out.println("replaceCardList" + replaceCardList);
        System.out.println(howeststone.getYou().getDeck());

        // HOWESTSTONE.getYou().setCardsInHand(replace);

        if (howeststone.getActivePlayer().equals(YOU_STR)) {
            // add 3/4 cards to hand
            //howeststone.setTurnTime(50);
        } else {
            for (String card: replaceCardList) {
                //TODO add card to hand enemy
                final boolean fixError = true;
            }
            // replace cards ?
            System.out.println(howeststone.getEnemy().getCardsInHand());
            enemyTurn(howeststone);
        }
    }

    private void enemyTurn(Game howeststone) {
        // check deze code zeker
        howeststone.getEnemy().beginTurn();

        // enemy speelt altijd duurste kaarten eerst:
        Card mostExpensiveCard = howeststone.getEnemy().getCardsInHand().getMostExpensiveCard();
        while (howeststone.getManaEnemy() >= mostExpensiveCard.getManaCost()) {
            playCard(mostExpensiveCard, howeststone);
            mostExpensiveCard = howeststone.getEnemy().getCardsInHand().getMostExpensiveCard();
            printGame(howeststone);
        }

        //enemyMinionsAttack(howeststone);
        // TODO check of je genoeg mana hebt voor de heropower + als het mage is: of er een target is
        //useHeroPower(howeststone, howeststone.getEnemy());

        // endTurn();
        // setCanAttack of all minions of enemy to true
        howeststone.setActivePlayer(YOU_STR);
        System.out.println(howeststone.getEnemy().getCardsOnPlayingField());
    }

    /*
    private void enemyMinionsAttack(Game howeststone) {
        for (Card card: howeststone.getEnemy().getCardsOnPlayingField().getCards()) {
            // currently final because not yet used, may be changed
            final int windfuryCounter = 1;
            while (!card.isExhausted() && card.getAmountAttacked() < card.getMaxAmountOfAttacks()) {
                final boolean fixError = true;

                //HOWESTSTONE.getYou().getRandomTarget();

                // kies random minion/hero van speler ==> randomCardPlayer
                // Card target = randomCardPlayer;
                // int health = target.getHealth (voor hieronder)

                // val die aan (attack functie in classe minion)
                // HOWESTSTONE.getEnemy().getCardsOnPlayingField()...attack(target)
                // + int attack = getAttack

                // voer eventuele abilities uit

                // update health
                // health - attack (- eventuele abilitydamage)

                // HOWESTSTONE.getEnemy().getCardsOnPlayingField()...
                // setHealth(card.getHealth - target.getDamage (- eventuele ability))

                // voer dode kaarten af

                // update battlelog

                if (!card.getCardAbilities().contains(Abilities.WINDFURY)) {
                    card.setExhausted(true);
                } else {
                     card.increaseAmountAttacked();
                }

            }
        }
    }
    */

    private void yourTurn(Game howeststone) {
        // TODO change because cannot be defined here
        howeststone.getYou().getCardsInHand().addCard(howeststone.getYou().getDeck().drawCard());

        // info:
        // cardName
        // manaCost
        // (attack/health/durability)
        // abilities & mechanics
        // type
        // heroID

        // cardssInPlayingField
        // cardsInHand

        // draw a card
        // mana up by 1

        final List<String> action = new ArrayList<>();
        action.add("Use hero power");
        // all values of these list
        final List<Card> cardsInHand = howeststone.getYou().getCardsInHand().getCards();
        for (Card card : cardsInHand) {
            action.add(String.valueOf(card));
        }
        // getAllAttackableMinions();
        // if you have weapon
        // action.add("Attack with hero");
        askInputUntilStop(action);
        // actions
        // - heroPower 2Mana
        // - play a card
        // - attack
        //      - hero
        //      - minion
        // end turn (active player change and enemy turn)
    }


    private void playCard(Card card, Game howeststone) {
        CardCollection areaPlayingField = howeststone.getYou().getCardsOnPlayingField();
        if ((howeststone.getActivePlayer()).equals("Enemy")) {
            areaPlayingField = howeststone.getEnemy().getCardsOnPlayingField();
        }
        if (areaPlayingField.getCards().size() < 7) {
            areaPlayingField.addCard(card);
        }
    }

    /*
    private void useHeroPower(Game howeststone, Player player) {
        if (Objects.equals(player.getHero().getHeroName(), "Paladin")) {
            //TODO replace silver hand recruit id
            //player.getCardsOnPlayingField().addCard("Silver Hand Recruit");
        } else {
            // TODO choose if target is selected before or in this function

            if (player == howeststone.getEnemy()) {
                // HOWESTSTONE.getYou().getRandomTarget();
            } else {
                // select target
            }

            final int damage = howeststone.getYou().getHero().getMageAttack();

            //ofwel 2
            //TODO target health -damage
        }
    }
    */

    private void printGame(Game howeststone) {
        System.out.println(ColorFormats.red("weapon/hero/mana/hand Enemy: ")
                + ColorFormats.green(NYI_STR));
        System.out.println(ColorFormats.red("Playing Field Enemy: ")
                + ColorFormats.green(howeststone.getEnemy().getCardsOnPlayingField().toString()));
        System.out.println(ColorFormats.red("Playing Field Player: ")
                + ColorFormats.green(howeststone.getYou().getCardsOnPlayingField().toString()));
        System.out.println(ColorFormats.red("weapon/hero/mana/hand Player: ")
                + ColorFormats.green(NYI_STR));
    }

    private String askInputUntilFoundInList(List<String> list) {
        String input;
        System.out.println(formatList(list));
        do {
            System.out.print(INPUT_HERE_STR);
            input = scanner.nextLine();

            if (!list.contains(input)) {
                System.out.println(ColorFormats.red("This is not an option!"));
                System.out.println("The available options are:");

                System.out.println(formatList(list));
            }
        }
        while (!list.contains(input));

        System.out.println(ColorFormats.red(SELECTED_STR) + ColorFormats.green(input));

        return input;
    }


    private List<String> askInputUntilStop(List<String> list) {
        String input;
        list.add(STOP_STR);
        final List<String> replace = new ArrayList<>();
        System.out.println(SELECT_WHAT_YOU_WANT_TO_DO_STR);
        System.out.println(formatList(list));
        do {
            System.out.print(INPUT_HERE_STR);
            input = scanner.nextLine();

            if (list.contains(input)) {
                if (replace.contains(input)) {
                    // TODO how to say that this cannot be done always
                    // for example actions
                    replace.remove(input);
                } else {
                    replace.add(input);
                }
                // change ? remove and add ?

                System.out.println(SELECT_WHAT_YOU_WANT_TO_DO_STR);
                System.out.println(formatList(list));
                System.out.println("Select what you want to undo:");
                System.out.println(formatList(replace));
            }
        }
        while (!STOP_STR.equals(input));
        replace.remove(STOP_STR);
        list.remove(STOP_STR);
        System.out.println(ColorFormats.red(SELECTED_STR) + formatList(replace));
        return replace;
    }
    @NotNull
    private String formatCardList(@NotNull List<Card> list) {
        final StringBuilder strBuilder = new StringBuilder();

        for (Card str : list) {
            strBuilder.append(LIST_ITEM_STR);
            strBuilder.append(str);
            strBuilder.append('\n');
        }

        return strBuilder.toString();
    }
    @NotNull
    private String formatList(@NotNull List<String> list) {
        final StringBuilder strBuilder = new StringBuilder();

        for (String str : list) {
            strBuilder.append(LIST_ITEM_STR);
            strBuilder.append(str);
            strBuilder.append('\n');
        }

        return strBuilder.toString();
    }
    private void newLine() {
        System.out.println();
    }
}
