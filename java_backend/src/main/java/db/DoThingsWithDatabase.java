package db;

import java.sql.*;

public class DoThingsWithDatabase {

    public static void main(String[] args) {
        new DoThingsWithDatabase().run();
    }

    private SqlDatabase db;

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
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
                throw new SQLException("No deck created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final long ID = rs.getLong(1);
                    System.out.printf("%s now has ID %d", deckName, ID);
                } else {
                    throw new SQLException("No deck created: no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
