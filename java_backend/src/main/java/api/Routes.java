package api;

import cards.CardCollection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.*;
import io.javalin.Context;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

class Routes {

    Routes(final Javalin server) {
        // case sensitive
        server.get("/API/getAllCards", this::getAllCards);
        //server.get("/API/getAllCards", this::getAllCards);

        // GAME BOARD
        server.get("/threebeesandme/get/gameboard/begincards", this::getBeginCards);
        server.get("/threebeesandme/get/gameboard/begin", this::beginGame);
        server.get("threebeesandme/get/gameboard/battlelog", this::getBattleLog);
        server.get("threebeesandme/get/gameboard/timeleft", this::getTimeLeft);
        server.get("threebeesandme/get/useheropower", this::useHeroPower);
        server.get("/threebeesandme/get/hero", this::getHeroName);
        server.get("/threebeesandme/get/gameboard/attackpermission", this::canThisMinionAttack);
        server.get("/threebeesandme/post/gameboard/heroattackStart", this::canHeroAttack);
        server.post("/threebeesandme/post/gameboard/cardsinhand", this::getCardsInHand);
        server.post("threebeesandme/post/gameboard/endturn", this::handleEndUrn);

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

    private static final String SUCCES = "SUCCES";
    private static final String ERROR = "ERROR";
    private Game howeststone = new Game();
    private CardCollection beginCards = new CardCollection();

    // HERO AND DECK SELECTOR

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
        context.json(howeststone.getYou().getHero().getDeckNames());
    }
    private void handleDeckSelection(Context context) {
        howeststone.getYou().setDeck(context.body());
        context.json(howeststone.getYou().getDeck().getNameOfCardCollection());
    }
    // GAME BOARD
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private void beginGame(Context context) {
        if (howeststone.getYou().getHero() == null || howeststone.getYou().getDeck() == null) {
            //TODO get out of this function not exception
            throw new NullPointerException();
        }
        howeststone.generateEnemy();
        howeststone.setTurnTime(50);

        Random rand = new Random();
        boolean doYouBegin = rand.nextBoolean();
        if (doYouBegin) {
            howeststone.setActivePlayer("you");
        }else {
            howeststone.setActivePlayer("enemy");
        }
        context.json(howeststone.getActivePlayer());
    }

    private void getBeginCards(Context context) {
        beginCards = new CardCollection();

        if (howeststone.getActivePlayer().equals("enemy")){
            beginCards.addCard(howeststone.getYou().getDeck().drawCard().getCardID());
        }
        beginCards.addCard(howeststone.getYou().getDeck().drawCard().getCardID());
        beginCards.addCard(howeststone.getYou().getDeck().drawCard().getCardID());
        beginCards.addCard(howeststone.getYou().getDeck().drawCard().getCardID());
        context.json(beginCards);
        // TODO give cards ipv id's
    }

    private void getCardsInHand(Context context) throws IOException {
        String body = context.body();
        System.out.println(body);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,List<String>> temp = mapper.readValue(body, new TypeReference<Map<String, List<String>>>(){});

        List<String> cardsInHand = temp.get("CardsInHand");
        List<String> cardsToReplace = temp.get("CardsToDeck");
        System.out.println(cardsInHand.toString());

        boolean hasFalseData = false;

        for(String gottenCardID : cardsInHand) {
            // TODO: check if cards are valid
        }

        if(!hasFalseData) {
            for (String cardToReplace : cardsToReplace) {
                cardsInHand.add(Integer.toString(howeststone.getYou().getDeck().drawCard().getCardID()));
                howeststone.getYou().getDeck().addCard(Integer.parseInt(cardToReplace));
            }

            System.out.println("Cards + replaced: " + cardsInHand);

            howeststone.getYou().setCardsInHand(cardsInHand);

            context.json(SUCCES);
        }
        else {
            context.json(ERROR);
        }
        // howeststone.getYou().setCardsInHand(null);
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
        context.result("Turn has ended");
    }

    private void useHeroPower(Context context) {
        context.result("kaaapow");
    }

    private void getHeroName(Context context) {
        String hero = context.queryParamMap().get("parent")[0];
        if (hero.equals("enemy")){
            context.json(howeststone.getEnemy().getHero().getHeroName());
        }else{
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

    private void deleteDeck(Context context){
        context.result("Your deck has been deleted");
    }

    private void filterCards(Context context) {
        context.result("Your deck has been filtered");
    }
}
