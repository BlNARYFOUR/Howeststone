package api;

import cards.Card;
import cards.CardCollection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.*;
import io.javalin.Context;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

class Routes {
    private final Game HOWESTSTONE;
    private CardCollection filterCollection;

    Routes(final Javalin server, Game game) {
        HOWESTSTONE = game;
        // case sensitive

        // GAME BOARD
        server.get("/threebeesandme/get/gameboard/begincards", this::getBeginCards);
        server.get("/threebeesandme/get/gameboard/begin", this::beginGame);
        server.get("threebeesandme/get/gameboard/battlelog", this::getBattleLog);
        server.get("threebeesandme/get/gameboard/timeleft", this::getTimeLeft);
        server.get("threebeesandme/get/useheropower", this::useHeroPower);
        server.get("/threebeesandme/get/hero", this::getHeroName);
        server.get("/threebeesandme/get/gameboard/attackpermission", this::canThisMinionAttack);
        server.post("/threebeesandme/post/gameboard/heroattackStart", this::canHeroAttack);
        server.post("/threebeesandme/post/gameboard/cardsinhand", this::getCardsInHand);
        server.post("threebeesandme/post/gameboard/endturn", this::handleEndUrn);

        // HERO AND DECK SELECTOR
        server.get("/threebeesandme/get/heroanddeckselector/decks", this::getAllDecks);
        server.get("/threebeesandme/get/heroanddeckselector/heroes", this::getAllHeroes);
        server.post("/threebeesandme/post/heroanddeckselector/hero", this::handleHeroSelection);
        server.post("/threebeesandme/post/heroanddeckselector/deck", this::handleDeckSelection);

        // DECKBUILDER
        server.get("/threebeesandme/get/deckbuilder/cards", this::getAllCards);
        server.post("/threebeesandme/post/deckbuilder/hero", this::handleHeroSelection);


        server.post("threebeesandme/post/deckbuilder/savedeck", this::saveDeck);
        server.post("/threebeesandme/post/deckbuilder/newdeck", this::newDeck);
        server.post("threebeesandme/post/deckbuilder/deleteDeck", this::deleteDeck);
        server.post("/threebeesandme/howeststone/post/deckbuilder/sort", this::sortDeck);
        server.post("/threebeesandme/howeststone/post/deckbuilder/filterCards", this::filterCards);
    }


    private static final String SUCCES = "SUCCES";
    private static final String ERROR = "ERROR";
    // HERO AND DECK SELECTOR

    private void getAllHeroes(Context context) {
        context.json(HOWESTSTONE.getHeroNames());
    }

    private void handleHeroSelection(Context context) {
        Player you = new Player();
        you.setHero(context.body());
        HOWESTSTONE.addYou(you);
        context.json(HOWESTSTONE.getYou().getHero().getHeroName());
    }

    private void getAllDecks(Context context) {
        context.json(HOWESTSTONE.getDeckNames());
    }

    private void handleDeckSelection(Context context) {
        // System.out.println(HOWESTSTONE.deckNames);
        HOWESTSTONE.getYou().setDeck(
                HOWESTSTONE.deckNames.get(
                        HOWESTSTONE.getYou().getHero().getHeroName()
                )
        );
        context.json(HOWESTSTONE.getYou().getDeck().getNameOfCardCollection());
    }
    // GAME BOARD

    private void beginGame(Context context) {
        if (HOWESTSTONE.getYou().getHero() == null || HOWESTSTONE.getYou().getDeck() == null) {
            //TODO get out of this method not exception
            throw new NullPointerException();
        }
        HOWESTSTONE.generateEnemy();
        HOWESTSTONE.setTurnTime(50);

        Random rand = new Random();
        boolean doYouBegin = rand.nextBoolean();
        if (doYouBegin) {
            HOWESTSTONE.setActivePlayer("you");
        } else {
            HOWESTSTONE.setActivePlayer("enemy");
        }
        context.json(HOWESTSTONE.getActivePlayer());
    }

    private void getBeginCards(Context context) {
        // TODO can beginCards change this way
        context.json(HOWESTSTONE.getPlayerBeginCards());
        // TODO give cards ipv id's
    }

    private void getCardsInHand(Context context) throws IOException {
        String body = context.body();
        System.out.println(body);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Integer>> temp = mapper.readValue(body, new TypeReference<Map<String, List<Integer>>>() {
        });

        List<Integer> cardsInHand = temp.get("CardsInHand");
        List<Integer> cardsToReplace = temp.get("CardsToDeck");

        if (HOWESTSTONE.setPlayerCardsInHand(cardsInHand, cardsToReplace)) {
            context.json(SUCCES);
        } else {
            context.json(ERROR);
        }
    }

    private void getAllCards(Context context) {
        context.json(HOWESTSTONE.allCards);
    }

    private void getBattleLog(Context context) {
        context.result("Battlelog");
    }

    private void getTimeLeft(Context context) {
        context.result("Time Left");
    }

    private void handleEndUrn(Context context) {
        context.result("Turn has ended");
    }

    private void useHeroPower(Context context) {
        context.result("kaaapow");
    }

    private void getHeroName(Context context) {
        String hero = context.queryParamMap().get("parent")[0];
        if (hero.equals("enemy")) {
            context.json(HOWESTSTONE.getEnemy().getHero().getHeroName());
        } else {
            context.json(HOWESTSTONE.getYou().getHero().getHeroName());
        }
    }

    private void canHeroAttack(Context context) {
        context.result("no :p");
    }

    private void canThisMinionAttack(Context context) {
        context.result("no :p");
    }


    // DECK BUILDER

    private void saveDeck(Context context) {
        context.result("Deck has been saved");
    }

    private void newDeck(Context context) {
        context.result("A new deck has been created");
    }

    private void deleteDeck(Context context) {
        context.result("Your deck has been deleted");
    }

    private void filterCards(Context context) throws IOException {
        String body = context.body();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<String>> temp = mapper.readValue(body, new TypeReference<Map<String, List<String>>>() {
        });
        List<String> filterArray = temp.get("filterArray");
        filterCollection = HOWESTSTONE.filterCards(filterArray);
        if (filterCollection.getCards().size() >= 1) {
            context.json(filterCollection);
        } else {
            context.json("ERROR");
        }
    }

    private void sortDeck(Context context) {
        System.out.println(context.body());
        // alfaz
        //alfza
        //mana07
        //mana70
        // Collections.sort(filterCollection.getCards());
    }
}
