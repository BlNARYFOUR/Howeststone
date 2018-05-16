package api;

import io.javalin.Context;
import io.javalin.Javalin;

class Routes {

    Routes(final Javalin server) {
        // case sensitive
        server.get("/API/getAllCards", this::getAllCards);
        server.get("/", this::handleRoot);
    }

    private void getAllCards(Context context) {
        context.result("Cards");
    }

    private void handleRoot(final Context context) {
        context.result("Hello you");
    }


}
