package api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServerTest {
    @BeforeClass
    public static void onlyOnce() {
        new Server(4242).start();
    }

    @Test
    public void testSomeAppMethod() {
        assertTrue("is this 'true'?", true);
    }

    @Test
    public void testRootRouteAsString() {
        try {
            assertTrue("testing / as string",
                    Unirest.get("http://localhost:4242/").asString().getBody()
                            .equals ("Hello you"));
        } catch (UnirestException e) {
            e.printStackTrace ();
        }
    }
}
