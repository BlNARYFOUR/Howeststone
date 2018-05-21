package api;

import game.*;
import hero.*;
import io.javalin.Context;
import io.javalin.Javalin;

class Routes {

    Routes(final Javalin server) {
        // case sensitive
        server.get("/API/getAllCards", this::getAllCards);
        server.get("/", this::handleRoot);
        //server.get("/API/getAllCards", this::getAllCards);

        // GAME BOARD
        server.get("threebeesandme/get/gameboard/battlelog", this::getBattleLog);
        server.get("threebeesandme/get/gameboard/timeleft", this::getTimeLeft);
        server.post("threebeesandme/post/gameboard/endturn", this::handleEndUrn);
        server.get("threebeesandme/get/useheropower", this::useHeroPower);
        server.get("/threebeesandme/get/yourhero", this::getYourHero);
        server.get("/threebeesandme/get/gameboard/attackpermission", this::canThisMinionAttack);
        server.get("/threebeesandme/post/gameboard/heroattackStart", this::canHeroAttack);

        // HERO AND DECK SELECTOR
        server.get("threebeesandme/get/heroanddeckselector/decks", this::getAllDecks);
        server.get("threebeesandme/get/heroanddeckselector/heroes", this::getAllHeroes);
        server.post("threebeesandme/post/heroanddeckselector/hero", this::handleHeroSelection);
        server.post("threebeesandme/post/heroanddeckselector/deck", this::handleDeckSelection);

        // DECKBUILDER
        server.post("threebeesandme/post/deckbuilder/savedeck", this::saveDeck);
        server.post("threebeesandme/post/deckbuilder/newdeck", this::newDeck);
        server.post("threebeesandme/post/deckbuilder/deleteDeck", this::deleteDeck);
        server.post("threebeesandme/post/deckbuilder/selecthero", this::handleHeroSelection);
        //TODO sort server.post("threebeesandme/post/deckbuilder/sort?by=", null);
        server.post("threebeesandme/post/deckbuilder/filterCards", this::filterCards);
        server.get("/threebeesandme/howeststone/get/heroanddeckselector/heroes", this::loadHeroes);
    }

    private void loadHeroes(Context context) {
        context.result("[\"Mage\",\"Paladin\"]");
    }

    Game howeststone = new Game();

    private void handleRoot(final Context context) {
        context.result("This is the HowestStone API, This API is designed so you can play HowestStone on browser :D");

    }

    // GAME BOARD

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

    private void getYourHero(Context context) {
        // TODO check if this will work (you.getYourHero.getNameOfCardCollection().toString)
    }

    private void canHeroAttack(Context context) {
        context.result("no :p");
    }

    private void canThisMinionAttack(Context context) {
        context.result("no :p");
    }

    // HERO AND DECK SELECTOR

    private void getAllDecks(Context context) {
        // TODO: DECKS IN FUNCTION OF SELECTED HERO!!

        context.result("Here are all the decks");
    }

    private void getAllHeroes(Context context) {
        context.json(howeststone.getHeroNames());
        context.result("Just got all the heroes");
    }

    private void handleHeroSelection(Context context) {
        context.result("Hero has been selected");
    }

    private void handleDeckSelection(Context context) {
        context.result("Deck has been selected");
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
