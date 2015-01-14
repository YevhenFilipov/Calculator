import com.filipov.calculator.EvaluationException;
import com.filipov.calculator.MathExpressionCalculator;
import com.filipov.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class MathExpressionBracketsTest {


    @Test
    public void verifySingleBrackets() throws EvaluationException {

        final String inputString = "1+1-(4-3)+5";
        final Double referenceResult = 6.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Single Brackets test", result == referenceResult);
    }

    @Test
    public void verifyBrackets() throws EvaluationException {

        final String inputString = "1+1-(4-(2+3)-3)+5";
        final Double referenceResult = 11.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Brackets test", result == referenceResult);
    }

    @Test
    public void verifyAllBinaryOperationsWithBrackets() throws EvaluationException {

        final String inputString = "25-((2+3*2^3/2)+6)";
        final Double referenceResult = 5.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for all binary operations with brackets", result == referenceResult);
    }
}
