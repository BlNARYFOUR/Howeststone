package api;

import game.GameBoard;
import game.Player;
import hero.Hero;
import io.javalin.Context;
import io.javalin.Javalin;

class Routes {

    Routes(final Javalin server) {
        // case sensitive
        server.get("/API/getAllCards", this::getAllCards);
        server.get("/", this::handleRoot);
        server.get("/threebeesandme/howeststone/get/heroanddeckselector/heroes", this::loadHeroes);
    }

    private void loadHeroes(Context context) {
        context.result("[\"Mage\",\"Paladin\"]");
    }

    private void getAllCards(Context context) {
        context.result("Cards");
    }

    private void handleRoot(final Context context) {
        context.result("Hello you");
    }


}
