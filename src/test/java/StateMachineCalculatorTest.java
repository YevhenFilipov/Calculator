import com.teamdev.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class StateMachineCalculatorTest {

    private static double referenceResult = 509.42;
    private static String inputString = "509.42";

    @Test

    public void verifySimpleValue() throws Exception {
        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Number inputted", result == referenceResult);
    }
}
