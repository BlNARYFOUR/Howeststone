package api;

import io.javalin.Context;
import io.javalin.Javalin;

class Routes {

    Routes(final Javalin server) {
        server.get("/API/getAllCards", this::getAllCards);
        server.get("/", this::handleRoot);
        server.get("/threebeesandme/howeststone/get/useheropower", this::useHeroPower);
    }

    private void useHeroPower(Context context) {
        context.result("kaaapow");
    }

    private void getAllCards(Context context) {
        context.result("Cards");
    }

    private void handleRoot(final Context context) {
        context.result("Hello you");
    }


}
