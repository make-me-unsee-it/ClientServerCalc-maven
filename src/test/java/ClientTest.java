import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
    private Client client;

    @Before
    public void setUp() {
        client = new Client();
    }

    @After
    public void tearDown() {
        client = null;
    }

    @Test
    public void testSum() {
        String actual = client.launch("3 + 3");
        String expected = "6";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication() {
        String actual = client.launch("3 * 3");
        String expected = "9";
        Assert.assertEquals(expected, actual);
    }
}