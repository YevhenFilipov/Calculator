import com.filipov.calculator.EvaluationException;
import com.filipov.calculator.MathExpressionCalculator;
import com.filipov.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class MathExpressionBinaryOperationTest {

    @Test

    public void verifyAddOperation() throws EvaluationException {

        final String inputString = "2+2+1";
        final Double referenceResult = 5.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Add operation test", result == referenceResult);
    }

    @Test

    public void verifySubtractOperations() throws EvaluationException {

        final String inputString = "3-1-1";
        final Double referenceResult = 1d;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Subtract operation  test", result == referenceResult);
    }


    @Test

    public void verifyAddAndSubtractOperations() throws EvaluationException {

        final String inputString = "0-1+1-1+1+10";
        final Double referenceResult = 10.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Add and subtract operations  test", result == referenceResult);
    }

    @Test

    public void verifyMultiplyOperation() throws EvaluationException {

        final String inputString = "2*2";
        final Double referenceResult = 4.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("MultiplyOperation test", result == referenceResult);
    }

    @Test

    public void verifyDivisionOperation() throws EvaluationException {

        final String inputString = "6/2";
        final Double referenceResult = 3.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("DivisionOperation test", result == referenceResult);
    }

    @Test

    public void verifyPowerOperation() throws EvaluationException {

        final String inputString = "2^3";
        final Double referenceResult = 8.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("PowerOperation test", result == referenceResult);
    }

    @Test

    public void verifyRightAssociatedOperation() throws EvaluationException {

        final String inputString = "2^3^2";
        final Double referenceResult = 512.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Right-associated operation test", result == referenceResult);
    }

    @Test

    public void verifyAllBinaryOperations() throws EvaluationException {

        final String inputString = "2+3*2^3/2-4";
        final Double referenceResult = 10.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for all binary operations", result == referenceResult);
    }
}
