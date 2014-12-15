import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class MathExpressionNumberTest {

    @Test

    public void verifySingleNumber() throws EvaluationException {

        final String inputString = "50";
        final Double referenceResult = 50d;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Single number test", result == referenceResult);
    }

    @Test

    public void verifySingleFractionalNumber() throws EvaluationException {

        final String inputString = "509.42";
        final Double referenceResult = 509.42;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Single number test", result == referenceResult);
    }

    @Test

    public void verifySingleNegativeNumber() throws EvaluationException {

        final String inputString = "-509";
        final Double referenceResult = -509d;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Single number test", result == referenceResult);
    }
}
