package api;

import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private final int port;

    Server(final int port) {
        this.port = port;
    }

    private void start() {
        final Javalin server = Javalin.create()
                .port(port)
                .start();
        new Routes(server);
    }

    public static void main(final String[] args) {
        LOGGER.debug("starting server");
        new Server(4242).start();
        LOGGER.debug("server started");
    }
}
