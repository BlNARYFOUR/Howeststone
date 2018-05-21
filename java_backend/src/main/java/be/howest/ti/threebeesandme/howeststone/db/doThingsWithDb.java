package be.howest.ti.threebeesandme.howeststone.db; /*
  Created by Bert on 16/05/2018.
  Have a nice day!
 */

import java.sql.*;

public class doThingsWithDb {

    public static void main(String[] args) {
        new doThingsWithDb().run();
    }

    private SqlDatabase db;

    private void run() {
        db = new SqlDatabase(
                "localhost:88",
                "root",
                ""
        );
    }
    private void insertDeck(String deckName, int heroId) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_DECK,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, deckName);
            stmt.setInt(2, heroId);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No deck created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    System.out.printf("%s now has ID %d", deckName, id);
                }
                else {
                    throw new SQLException("No deck created: no id obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}