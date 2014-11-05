import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.calculator.impl.StateMachineCalculator;
import junit.framework.Assert;
import org.junit.Test;

public class MathExpressionCalculatorTest {

    @Test

    public void verifySingleNumber() throws EvaluationException {

        final String inputString = "509.42";
        final Double referenceResult = 509.42;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Single number test", result == referenceResult);
    }

    @Test

    public void verifyAddOperation() throws EvaluationException {

        final String inputString = "2+2+1";
        final Double referenceResult = 5.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("AddOperation test", result == referenceResult);
    }

    @Test

    public void verifyAddSubtractOperations() throws EvaluationException {

        final String inputString = "0-1+1-1+1+10";
        final Double referenceResult = 10.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Add and subtract operations  test", result == referenceResult);
    }

    @Test

    public void verifyBrackets() throws EvaluationException {

        final String inputString = "1+1-(4-(2+3)-3)+5";
        final Double referenceResult = 11.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("TestBrackets test", result == referenceResult);
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

        final String inputString = "2+3*2^3/2";
        final Double referenceResult = 14.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for all binary operations", result == referenceResult);
    }

    @Test

    public void verifyAllBinaryOperationsWithBrackets() throws EvaluationException {

        final String inputString = "25-((2+3*2^3/2)+6)";
        final Double referenceResult = 5.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for all binary operations with brackets", result == referenceResult);
    }

    @Test

    public void verifySqrtFunction() throws EvaluationException {

        final String inputString = "sqrt[4]";
        final Double referenceResult = 2.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for sqrt function", result == referenceResult);
    }

    @Test

    public void verifyMinFunction() throws EvaluationException {

        final String inputString = "min[4;3;7]";
        final Double referenceResult = 3.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for min function", result == referenceResult);
    }

    @Test

    public void verifyMaxFunction() throws EvaluationException {

        final String inputString = "max[4;3;7]";
        final Double referenceResult = 7.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for max function", result == referenceResult);
    }

    @Test

    public void verifySumFunction() throws EvaluationException {

        final String inputString = "sum[4;3;7]";
        final Double referenceResult = 14.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for sum function", result == referenceResult);
    }

    @Test

    public void verifyNestedFunction() throws EvaluationException {

        final String inputString = "min[sqrt[25];sum[2;1];max[4;5;7]]";
        final Double referenceResult = 3.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for nested functions", result == referenceResult);
    }

    @Test

    public void verifyNestedFunctionsWithBrackets() throws EvaluationException {

        final String inputString = "10-(1+max[sqrt[25];sum[2;1];max[4;5;7]]+1)";
        final Double referenceResult = 1.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for nested functions with brackets", result == referenceResult);
    }

    @Test

    public void verifyNestedFunctionsWithBracketsInArgs() throws EvaluationException {

        final String inputString = "max[sqrt[5*5];sum[1+1;1];max[4;5;10-(1+2)]]";
        final Double referenceResult = 7.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(inputString);
        Assert.assertTrue("Test for nested functions with brackets in args", result == referenceResult);
    }

    @Test

     public void verifyErrorPositionForFunctionClosedBracket() {

        final String inputString = "1+sum[1;2";
        final Double referenceResult = 2.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate(inputString);
        } catch (EvaluationException e) {
            int result = e.getErrorIndex();
            Assert.assertTrue("Test error position for function's closing bracket", result == referenceResult);
        }
    }

    @Test

    public void verifyErrorPositionForFunctionOpeningBracket() {

        final String inputString = "1+sqrt[sum[8;8]";
        final Double referenceResult = 7.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate(inputString);
        } catch (EvaluationException e) {
            int result = e.getErrorIndex();
            Assert.assertTrue("Test error position for function's closing bracket", result == referenceResult);
        }
    }

    @Test

    public void verifyErrorPositionForClosedBracket() {

        final String inputString = "1+1-(4-(2+3)-3+5";
        final Double referenceResult = 7.0;

        final MathExpressionCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate(inputString);
        } catch (EvaluationException e) {
            int result = e.getErrorIndex();
            Assert.assertTrue("Test error position for closed bracket", result == referenceResult);
        }
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

