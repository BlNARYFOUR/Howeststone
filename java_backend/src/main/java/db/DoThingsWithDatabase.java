package db;

import java.sql.*;

public class DoThingsWithDatabase {
/*
    private SqlDatabase db;

    public static void main(String[] args) {
        new DoThingsWithDatabase().run();
    }

    private void run() {
        db = new SqlDatabase("localhost:88", "root", "");
    }

    private void insertDeck(String deckName, int heroId) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_DECK,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, deckName);
            stmt.setInt(2, heroId);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No deck created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final long id = rs.getLong(1);
                    System.out.printf("%s now has ID %d", deckName, id);
                } else {
                    throw new SQLException("No deck created: no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
