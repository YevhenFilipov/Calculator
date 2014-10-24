import com.teamdev.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class StateMachineCalculatorTest {

    @Test

    public void verifySingleNumber() throws Exception {

        final String inputString = "509.42";
        final Double referenceResult = 509.42;

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Number inputted", result == referenceResult);
    }

    @Test

    public void verifyAddOperation() throws Exception {

        final String inputString = "2+2+1";
        final Double referenceResult = 5.0;

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("AddOperation", result == referenceResult);
    }

    @Test

    public void verifyAddSubtractOperations() throws Exception{

        final String inputString = "0-1+1-1+1+10";
        final Double referenceResult = 10.0;

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("AddSubtractOperations", result == referenceResult);

    }

    @Test(expected = IllegalStateException.class)

    public void discrepancyTransitionMatrix() throws Exception{

        final String inputString = "2+2+";

        final StateMachineCalculator calculator = new StateMachineCalculator();
        calculator.evaluate(inputString);

    }
}
