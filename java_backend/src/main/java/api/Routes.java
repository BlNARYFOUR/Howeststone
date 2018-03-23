package api;

import io.javalin.Context;
import io.javalin.Javalin;

class Routes {

    Routes(final Javalin server) {
        server.get("/", this::handleRoot);
    }

    private void handleRoot(final Context context) {
        context.result("Hey you");
    }

}
