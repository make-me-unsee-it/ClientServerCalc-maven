import exception.UserInputException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalcTest {
    private Calc calc;

    @Before
    public void setUp() {
        calc = new Calc();
    }

    @After
    public void tearDown() {
        calc = null;
    }

    @Test
    public void testRunSum() throws UserInputException {
        String expected = calc.run("3 + 2");
        String actual = "5";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRunSubtraction() throws UserInputException {
        String expected = calc.run("10 - 4");
        String actual = "6";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRunMultiplication() throws UserInputException {
        String expected = calc.run("5 * 5");
        String actual = "25";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRunDivision() throws UserInputException {
        String expected = calc.run("75 / 5");
        String actual = "15.0";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRunDivisionDouble() throws UserInputException {
        String expected = calc.run("71 / 5");
        String actual = "14.2";
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = UserInputException.class)
    public void testArgumentCountFail() throws UserInputException {
        calc.run("3 + 2 + 4");
    }

    @Test(expected = UserInputException.class)
    public void testOtherInputError1() throws UserInputException {
        calc.run("3+2");
    }

    @Test(expected = UserInputException.class)
    public void testOtherInputError2() throws UserInputException {
        calc.run("qwerty");
    }

    @Test(expected = UserInputException.class)
    public void testOtherInputError3() throws UserInputException {
        calc.run("3^3");
    }
}