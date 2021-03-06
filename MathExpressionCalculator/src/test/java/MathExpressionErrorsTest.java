import com.filipov.calculator.EvaluationException;
import com.filipov.calculator.MathExpressionCalculator;
import com.filipov.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class MathExpressionErrorsTest {

    @Test
    public void verifyErrorPositionForFunctionClosedBracket() {
        final String inputString = "1+sum(1;2";
        final int referenceResult = 5;
        int result = -1;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate(inputString);
        } catch (EvaluationException e) {
            result = e.getErrorIndex();
        }
        Assert.assertTrue("Test error position for function's closing bracket", result == referenceResult);
    }

    @Test
    public void verifyErrorPositionForFunctionOpeningBracket() {
        final String inputString = "1+sqrt(sum(8;8)";
        final int referenceResult = 10;
        int result = -1;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate(inputString);
        } catch (EvaluationException e) {
            result = e.getErrorIndex();
        }
        Assert.assertTrue("Test error position for function's closing bracket", result == referenceResult);
    }

    @Test
    public void verifyErrorPositionForClosedBracket() {

        final String inputString = "1+1-(4-(2+3)-3+5";
        final int referenceResult = 7;
        int result = -1;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate(inputString);
        } catch (EvaluationException e) {
            result = e.getErrorIndex();
        }
        Assert.assertTrue("Test error position for closed bracket", result == referenceResult);
    }

    @Test
    public void verifyErrorPositionForIncorrectBracketsArgument() {

        final String inputString = "sqrt(2,2)";
        final int referenceResult = 9;
        int result = -1;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate(inputString);
        } catch (EvaluationException e) {
            result = e.getErrorIndex();
        }
        Assert.assertTrue("Test error position for incorrect argument in brackets", result == referenceResult);
    }


    @Test(expected = EvaluationException.class)
    public void discrepancyTransitionMatrix() throws EvaluationException {

        final String inputString = "2+2+";

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        calculator.evaluate(inputString);
    }

    @Test(expected = EvaluationException.class)
    public void CanNotFindOpeningBracket() throws EvaluationException {
        final String inputString = "(1+2)+3)";
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        calculator.evaluate(inputString);
    }

    @Test(expected = EvaluationException.class)
    public void CanNotFindClosingBracket() throws EvaluationException {
        final String inputString = "((1+2)+3";
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        calculator.evaluate(inputString);
    }
}
