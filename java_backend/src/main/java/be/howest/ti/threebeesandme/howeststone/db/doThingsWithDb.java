package be.howest.ti.threebeesandme.howeststone.db; /*
  Created by Bert on 16/05/2018.
  Have a nice day!
 */

public class doThingsWithDb {

    public static void main(String[] args) {
        new doThingsWithDb().run();
    }

    SqlDatabase db;

    private void run() {
        db = new SqlDatabase(
                "localhost:88",
                "root",
                ""
        );
    }

}