package api;

import abilities.*;
import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import events.Events;
import game.Game;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private static final SqlDatabase DB = new SqlDatabase("jdbc:mysql://localhost:3306/howeststone", "root", "");
    private Game howeststone;
    private final int port;

    Server(final int port) {
        this.port = port;
    }

    void start() {
        final Javalin server = Javalin.create()
                .port(port)
                .enableStaticFiles("web")
                .start();
        new Routes(server, howeststone);
    }

    public static void main(final String[] args) {
        LOGGER.debug("starting server");
        new Server(4242).init().start();
        LOGGER.debug("server started");
    }

    private Server init() {
        this.howeststone = new Game(DB);
        return this;
    }


}
