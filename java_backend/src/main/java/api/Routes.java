package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import formatters.ColorFormats;
import game.*;
import io.javalin.Context;
import io.javalin.Javalin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

class Routes {

    private static final String SUCCES = "SUCCES";
    private static final String ERROR = "ERROR";

    private final Game howeststone;

    Routes(final Javalin server, Game game) {
        howeststone = game;
        // case sensitive
        server.get("/API/getAllCards", this::getAllCards);
        //server.get("/API/getAllCards", this::getAllCards);

        // GAME BOARD
        server.get("/threebeesandme/get/gameboard/begincards", this::getBeginCards);
        server.get("/threebeesandme/get/gameboard/begin", this::beginGame);
        server.get("/threebeesandme/get/gameboard/mymana", this::getMyMana);
        server.get("threebeesandme/get/gameboard/battlelog", this::getBattleLog);
        server.get("threebeesandme/get/gameboard/timeleft", this::getTimeLeft);
        server.get("threebeesandme/get/useheropower", this::useHeroPower);
        server.get("/threebeesandme/get/hero", this::getHeroName);
        server.get("/threebeesandme/get/gameboard/attackpermission", this::canThisMinionAttack);
        server.get("/threebeesandme/get/gameboard/mycardsinhand", this::getMyCardsInHand);
        server.post("/threebeesandme/post/gameboard/heroattackStart", this::canHeroAttack);
        server.post("/threebeesandme/post/gameboard/replacecards", this::replaceCards);
        server.post("/threebeesandme/post/gameboard/playcard", this::playMyCard);

        // HERO AND DECK SELECTOR
        server.get("/threebeesandme/get/heroanddeckselector/decks", this::getAllDecks);
        server.get("/threebeesandme/get/heroanddeckselector/heroes", this::getAllHeroes);
        server.post("/threebeesandme/post/heroanddeckselector/hero", this::handleHeroSelection);
        server.post("/threebeesandme/post/heroanddeckselector/deck", this::handleDeckSelection);

        // DECKBUILDER
        server.post("threebeesandme/post/deckbuilder/savedeck", this::saveDeck);
        server.post("threebeesandme/post/deckbuilder/newdeck", this::newDeck);
        server.post("threebeesandme/post/deckbuilder/deleteDeck", this::deleteDeck);
        server.post("threebeesandme/post/deckbuilder/selecthero", this::handleHeroSelection);
        //TODO sort server.post("threebeesandme/post/deckbuilder/sort?by=", null);
        server.post("threebeesandme/post/deckbuilder/filterCards", this::filterCards);
    }

    // HERO AND DECK SELECTOR

    private void getMyCardsInHand(Context context) {
        context.json(howeststone.getYou().getCardsInHand().getCards());
    }

    private void getAllHeroes(Context context) {
        context.json(howeststone.getHeroNames());
    }

    private void handleHeroSelection(Context context) {
        Player you = new Player();
        you.setHero(context.body());
        howeststone.addYou(you);
        context.json(howeststone.getYou().getHero().getHeroName());
    }

    private void getAllDecks(Context context) {
        context.json(howeststone.getDeckNames());
    }

    private void handleDeckSelection(Context context) {
        // System.out.println(howeststone.deckNames);
        howeststone.getYou().setDeck(
                howeststone.deckNames.get(
                        howeststone.getYou().getHero().getHeroName()
                )
        );
        context.json(howeststone.getYou().getDeck().getName());
    }

    // GAME BOARD

    private void beginGame(Context context) {
        if (howeststone.getYou().getHero() == null || howeststone.getYou().getDeck() == null) {
            //TODO get out of this method not exception
            throw new NullPointerException();
        }
        howeststone.generateEnemy();
        howeststone.setTurnTime(50);
        howeststone.createPlayingField();

        Random rand = new Random();
        boolean doYouBegin = rand.nextBoolean();
        if (doYouBegin) {
            howeststone.setActivePlayer("you");
        } else {
            howeststone.setActivePlayer("enemy");
            howeststone.getEnemy().beginTurn();
            // do auto player

        }
        context.json(howeststone.getActivePlayer());
    }

    private void getBeginCards(Context context) {
        context.json(howeststone.getPlayerBeginCards());
    }

    private void getMyMana(Context context) {
        int[] manaInfo = {howeststone.getYou().getActiveMana(), howeststone.getYou().getTotalMana()};
        context.json(manaInfo);
    }

    private void replaceCards(Context context) throws IOException {
        String body = context.body();
        System.out.println(body);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Integer>> temp = mapper.readValue(body, new TypeReference<Map<String, List<Integer>>>(){});

        List<Integer> cardsInHand = temp.get("CardsInHand");
        List<Integer> cardsToReplace = temp.get("CardsToDeck");

        if(howeststone.setPlayerCardsInHand(cardsInHand, cardsToReplace)) {
            context.json(SUCCES);

            if(howeststone.getActivePlayer().equals("you")) {
                howeststone.getYou().beginTurn();
            }
        }
        else {
            System.out.println(ColorFormats.red("you shall not hack"));
            context.json(ERROR);
        }
    }

    private void playMyCard(Context context) throws IOException {
        boolean succeeded;
        String body = context.body();

        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        int cardId = mapper.readValue(body, new TypeReference<Integer>(){});

        succeeded = howeststone.getYou().playCard(cardId);

        if(succeeded) {
            context.json(SUCCES);
        } else {
            context.json(ERROR);
        }
    }

    private void getAllCards(Context context) {
        context.result("Cards");
    }

    private void getBattleLog(Context context) {
        context.result("Battlelog");
    }

    private void getTimeLeft(Context context) {
        context.result("Time Left");
    }

    private void handleEndUrn(Context context) {
        context.result("TurnTimer has ended");
    }

    private void useHeroPower(Context context) {
        context.result("kaaapow");
    }

    private void getHeroName(Context context) {
        String hero = context.queryParamMap().get("parent")[0];
        if (hero.equals("enemy")) {
            context.json(howeststone.getEnemy().getHero().getHeroName());
        } else {
            context.json(howeststone.getYou().getHero().getHeroName());
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

    private void filterCards(Context context) {
        context.result("Your deck has been filtered");
    }
}
