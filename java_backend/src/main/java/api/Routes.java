package api;

import io.javalin.Context;
import io.javalin.Javalin;

class Routes {

    Routes(final Javalin server) {
        server.get("/", this::handleRoot);
        server.get("/threebeesandme/howeststone/get/useheropower", this::useHeroPower);
        //server.get("/API/getAllCards", this::getAllCards);

        // GAME BOARD
        server.get("threebeesandme/get/gameboard/battlelog", this::getBattleLog);
        server.get("threebeesandme/get/gameboard/timeleft", this::getTimeLeft);
        server.post("threebeesandme/post/gameboard/endturn", this::handleEndUrn);

        // HERO AND DECK SELECTOR
        server.get("threebeesandme/get/heroanddeckselector/decks", this::getAllDecks);
        server.get("threebeesandme/get/heroanddeckselector/heroes", this::getAllHeroes);
        server.post("threebeesandme/post/heroanddeckselector/hero", this::handleHeroSelection);
        server.post("threebeesandme/post/heroanddeckselector/deck", this::handleDeckSelection);
    }

    private void useHeroPower(Context context) {
        context.result("kaaapow");
    }

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

    // HERO AND DECK SELECTOR

    private void getAllDecks(Context context) {
        // TODO: DECKS IN FUNCTION OF SELECTED HERO!!

        context.result("Here are all the decks");
    }

    private void getAllHeroes(Context context) {
        context.result("Just got all the heroes");
    }

    private void handleHeroSelection(Context context) {
        context.result("Hero has been selected");
    }

    private void handleDeckSelection(Context context) {
        context.result("Deck has been selected");
    }
}
