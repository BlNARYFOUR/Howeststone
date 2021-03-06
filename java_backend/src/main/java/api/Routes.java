package api;

import cards.Minion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import formatters.ColorFormats;
import game.*;
import io.javalin.Context;
import io.javalin.Javalin;
import java.io.IOException;
import java.util.*;


class Routes {

    private static final String SUCCESS = "SUCCESS";
    private static final String ERROR = "ERROR";
    private static final String ENEMY_STR = "enemy";
    private static final String YOU_STR = "you";
    private static final String PARENT_STR = "parent";

    private final Game howeststone;

    Routes(final Javalin server, Game game) {
        howeststone = game;

        setGetRequests(server);
        setPostRequests(server);
    }

    private void setGetRequests(Javalin server) {
        // GAME BOARD
        server.get("/threebeesandme/get/gameboard/begincards", this::getBeginCards);
        server.get("/threebeesandme/get/gameboard/begin", this::beginGame);
        server.get("/threebeesandme/get/gameboard/mymana", this::getMyMana);
        server.get("/threebeesandme/get/gameboard/enemymana", this::getEnemyMana);
        server.get("threebeesandme/get/gameboard/battlelog", this::getBattleLog);
        server.get("threebeesandme/get/gameboard/timeleft", this::getTimeLeft);
        server.get("threebeesandme/get/useheropower", this::useHeroPower);
        server.get("/threebeesandme/get/gameboard/attackpermission", this::canThisMinionAttack);
        server.get("/threebeesandme/get/gameboard/mycardsinhand", this::getMyCardsInHand);
        server.get("/threebeesandme/get/gameboard/amountofenemycardsinhand", this::getEnemyCardsInHand);
        server.get("/threebeesandme/get/gameboard/herohealth", this::getHeroHealth);
        server.get("/threebeesandme/get/gameboard/enemydecksize", this::getEnemyDeckSize);
        server.get("/threebeesandme/get/gameboard/mydecksize", this::getMyDeckSize);

        // HERO AND DECK SELECTOR
        server.get("/threebeesandme/get/hero", this::getHeroName);
        server.get("/threebeesandme/get/heroanddeckselector/decks", this::getAllDecks);
        server.get("/threebeesandme/get/heroanddeckselector/heroes", this::getAllHeroes);

        // DECKBUILDER
        server.get("/threebeesandme/get/deckbuilder/cards", this::getAllCards);
        server.get("/threebeesandme/get/deckbuilder/deck/cards", this::getCardsFromDeck);
    }

    private void setPostRequests(Javalin server) {
        // Loading og the page
        server.post("/threebeesandme/post/reset", this::reset);

        // GAME BOARD
        server.post("/threebeesandme/post/gameboard/heroattackStart", this::canHeroAttack);
        server.post("/threebeesandme/post/gameboard/replacecards", this::replaceCards);
        server.post("/threebeesandme/post/gameboard/playcard", this::playMyCard);
        server.post("/threebeesandme/post/gameboard/endturn", this::handleEndTurn);
        server.post("/threebeesandme/post/gameboard/playingfield/cancardbeadded", this::canCardBeAddedToPlayingField);
        server.post("/threebeesandme/post/gameboard/playingfield/attack", this::attackMinion);
        server.post("/threebeesandme/post/gameboard/playingfield/attack/hero", this::attackHero);


        // HERO AND DECK SELECTOR
        server.post("/threebeesandme/post/heroanddeckselector/hero", this::handleHeroSelection);
        server.post("/threebeesandme/post/heroanddeckselector/deck", this::handleDeckSelection);

        // DECKBUILDER
        server.post("/threebeesandme/post/deckbuilder/hero", this::handleHeroSelection);
        server.post("/threebeesandme/post/deckbuilder/deck/cancardbeadded", this::canCardBeAdded);
        server.post("/threebeesandme/post/deckbuilder/deck/addcard", this::addCardToDeck);
        server.post("/threebeesandme/post/deckbuilder/deck/deletecard", this::deleteCardFromDeck);
        server.post("/threebeesandme/post/deckbuilder/savedeck", this::saveDeck);
        server.post("/threebeesandme/post/deckbuilder/newdeck", this::newDeck);
        server.post("threebeesandme/post/deckbuilder/deleteDeck", this::deleteDeck);
        server.post("/threebeesandme/howeststone/post/deckbuilder/sort", this::sortDeck);
        server.post("/threebeesandme/howeststone/post/deckbuilder/filterCards", this::filterCards);
    }

    private void reset(Context context) {
        howeststone.setInactive();
        howeststone.resetTurnTimer();
        System.out.println(ColorFormats.red("Game currently inactive"));
        context.json("done");
    }

    private void getEnemyDeckSize(Context context) {
        int[] properties = {howeststone.getEnemy().getDeck().size(), 30};

        context.json(properties);
    }

    private void getMyDeckSize(Context context) {
        int[] properties = {howeststone.getYou().getDeck().size(), 30};

        context.json(properties);
    }

    // HERO AND DECK SELECTOR

    private void getAllHeroes(Context context) {
        context.json(howeststone.getHeroNames());
    }

    private void handleHeroSelection(Context context) {
        final Player you = new Player();
        you.setHero(context.body());
        howeststone.addYou(you);
        context.json(howeststone.getYou().getHero().getHeroName());
    }

    private void getAllDecks(Context context) {
        context.json(howeststone.getDeckNamesForChosenHero());
    }

    private void handleDeckSelection(Context context) {
        //final String yourHeroName = howeststone.getYou().getHero().getHeroName();

        for (int i = 0; i < howeststone.getDecks().size(); i++) {
            if (howeststone.getDecks().get(i).getName().equals(context.body())) {
                howeststone.getYou().setDeck(howeststone.getDecks().get(i));
            }
        }
        context.json(howeststone.getYou().getDeck().getName());
    }

    // GAME BOARD

    private void beginGame(Context context) {
        final String result = howeststone.beginGame();
        System.out.println("Active player set: " + result);
        context.json(result);
    }

    private void getBeginCards(Context context) {
        System.out.println(howeststone.getActivePlayer());
        context.json(howeststone.getPlayerBeginCards());
    }

    private void getMyMana(Context context) {
        final int[] manaInfo = {howeststone.getYou().getActiveMana(), howeststone.getYou().getTotalMana()};
        context.json(manaInfo);
    }

    private void getEnemyMana(Context context) {
        final int[] manaInfo = {howeststone.getEnemy().getActiveMana(), howeststone.getEnemy().getTotalMana()};
        context.json(manaInfo);
    }

    private void replaceCards(Context context) throws IOException {
        final String body = context.body();
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, List<Integer>> temp =
                mapper.readValue(body, new TypeReference<Map<String, List<Integer>>>() { });

        final List<Integer> cardsInHand = temp.get("CardsInHand");
        final List<Integer> cardsToReplace = temp.get("CardsToDeck");

        if (howeststone.setPlayerCardsInHand(cardsInHand, cardsToReplace)) {
            context.json(SUCCESS);
            if (howeststone.getActivePlayer().equals(YOU_STR)) {
                howeststone.startTurnYou();
            } else {
                howeststone.startTurnAutoplayer();
            }
        } else {
            System.out.println(ColorFormats.red("you shall not hack"));
            context.json(ERROR);
        }
    }

    private void getEnemyCardsInHand(Context context) {
        context.json(howeststone.getEnemy().getCardsInHand().size());
    }

    private void getMyCardsInHand(Context context) {
        context.json(howeststone.getYou().getCardsInHand().getCards());
    }

    private void canCardBeAddedToPlayingField(Context context) {
        if (!howeststone.getYou().canIPlayCard(context.body())) {
            context.json(ERROR);
        } else if (ENEMY_STR.equals(howeststone.getActivePlayer())) {
            context.json(ERROR);
        } else if (howeststone.getYou().getCardsOnPlayingField().getCards().size() >= 7) {
            context.json(ERROR);
        } else {
            context.json(SUCCESS);
        }
    }

    private void playMyCard(Context context) throws IOException {
        final boolean succeeded;
        final String body = context.body();

        final ObjectMapper mapper = new ObjectMapper();
        final int cardID = mapper.readValue(body, new TypeReference<Integer>() {
        });

        succeeded = howeststone.getYou().playCard(cardID);

        if (succeeded) {
            context.json(SUCCESS);
        } else {
            context.json(ERROR);
        }
    }

    private void getAllCards(Context context) {
        context.json(howeststone.getAllCards());
    }

    private void getBattleLog(Context context) {
        context.result("Battlelog");
    }

    private void getTimeLeft(Context context) {
        final int timeLeft = howeststone.getTurnTimeLeft();
        context.json(timeLeft);
    }

    private void handleEndTurn(Context context) {
        howeststone.resetTurnTimer();
        System.out.println("Timer cancel in routes");
        if (howeststone.getActivePlayer().equals(YOU_STR)) {
            howeststone.startTurnAutoplayer();
        }

        context.json("TurnTimer has ended");
    }

    private void useHeroPower(Context context) {
        context.result("kaaapow");
    }

    private void getHeroName(Context context) {
        final String hero = context.queryParamMap().get(PARENT_STR)[0];
        if (ENEMY_STR.equals(hero)) {
            context.json(howeststone.getEnemy().getHero().getHeroName());
        } else {
            context.json(howeststone.getYou().getHero().getHeroName());
        }
    }

    private void getHeroHealth(Context context) {
        final String hero = context.queryParamMap().get(PARENT_STR)[0];
        if (ENEMY_STR.equals(hero)) {
            context.json(howeststone.getEnemy().getHero().getHealth());
        } else {
            context.json(howeststone.getYou().getHero().getHealth());
        }
    }

    private void canHeroAttack(Context context) {
        context.result("no :P");
    }

    private void canThisMinionAttack(Context context) {
        if ("".equals(context.body())) {
            context.json(ERROR);
        } else {
            final int cardID = Integer.parseInt(context.queryParamMap().get("cardID")[0]);
            context.json(howeststone.getYou().getCardsOnPlayingField().checkIfCardCanAttack(cardID));
        }

    }
    private void attackMinion(Context context) throws IOException {
        // TODO howeststone.attackMinion(context.body());
    }

    private void attackHero(Context context) {
        // TODO check if card on playing field
        howeststone.attackEnemyHero(context.body());
        context.json(SUCCESS);
    }

    // DECK BUILDER

    private void getCardsFromDeck(Context context) {
        final String deckName = context.queryParamMap().get("deckname")[0];
        if (!howeststone.checkIfDeckNotExist(deckName)) {
            context.json(howeststone.getDeck(deckName));
        }
    }

    private void saveDeck(Context context) {
        context.json(howeststone.createDeck(howeststone.getDeckInDeckBuilder()));
    }

    private void newDeck(Context context) {
        if (howeststone.checkIfDeckNotExist(context.body())) {
            howeststone.setDeckInDeckBuilder(context.body());
            context.json(SUCCESS);
        } else {
            context.json(ERROR);
        }
    }

    private void deleteDeck(Context context) {
        context.result("Your deck has been deleted");
    }

    private void filterCards(Context context) throws IOException {
        final String body = context.body();
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, List<String>> temp = mapper.readValue(body, new TypeReference<Map<String, List<String>>>() {
        });
        final List<String> filterArray = temp.get("filterArray");
        howeststone.setFilterCollection(howeststone.filterCards(filterArray));
        if (howeststone.getFilterCollection().getCards().size() >= 1) {
            context.json(howeststone.getFilterCollection());
        } else {
            context.json(ERROR);
        }
    }

    private void sortDeck(Context context) {
        context.json(howeststone.getFilterCollection().sortDeck(context.body()));
    }

    private void canCardBeAdded(Context context) {
        context.json(
                howeststone.checkIfCardCanBeAddedToDeck(
                        context.body()));
    }

    private void addCardToDeck(Context context) {
        final int cardID = Integer.parseInt(context.body());
        howeststone.getDeckInDeckBuilder().addCard(howeststone.getAllCards().getCard(cardID));
        context.json(howeststone.getDeckInDeckBuilder());
    }
    private void deleteCardFromDeck(Context context) {
        final int cardID = Integer.parseInt(context.body());
        if (howeststone.getDeckInDeckBuilder() == null) {
            context.json(ERROR);
        } else if (howeststone.getDeckInDeckBuilder().getCards().size() == 0) {
            context.json(ERROR);
        } else {
            howeststone.getDeckInDeckBuilder().removeCard(howeststone.getAllCards().getCard(cardID));
            context.json(howeststone.getDeckInDeckBuilder());
        }
    }

}
