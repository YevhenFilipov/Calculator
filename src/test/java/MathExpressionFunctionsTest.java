import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class MathExpressionFunctionsTest {

    @Test
    public void verifySqrtFunction() throws EvaluationException {

        final String inputString = "sqrt(4)";
        final Double referenceResult = 2.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for sqrt function", result == referenceResult);
    }

    @Test
    public void verifyMinFunction() throws EvaluationException {

        final String inputString = "min(4;3;7)";
        final Double referenceResult = 3.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for min function", result == referenceResult);
    }

    @Test
    public void verifyMaxFunction() throws EvaluationException {

        final String inputString = "max(4;3;7)";
        final Double referenceResult = 7.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for max function", result == referenceResult);
    }

    @Test
    public void verifySumFunction() throws EvaluationException {

        final String inputString = "sum(4;3;7)";
        final Double referenceResult = 14.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for sum function", result == referenceResult);
    }

    @Test
    public void verifyNestedFunction() throws EvaluationException {

        final String inputString = "min(sqrt(25);sum(2;1);max(4;5;7))";
        final Double referenceResult = 3.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for nested functions", result == referenceResult);
    }

    @Test
    public void verifyNestedFunctionsWithBrackets() throws EvaluationException {

        final String inputString = "10-(1+max(sqrt(25);sum(2;1);max(4;5;7))+1)";
        final Double referenceResult = 1.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for nested functions with brackets", result == referenceResult);
    }

    @Test
    public void verifyNestedFunctionsWithBracketsInArgs() throws EvaluationException {

        final String inputString = "max(sqrt(5*5);sum(1+1;1);max(4;5;10-(1+2)))";
        final Double referenceResult = 7.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for nested functions with brackets in args", result == referenceResult);
    }
}
