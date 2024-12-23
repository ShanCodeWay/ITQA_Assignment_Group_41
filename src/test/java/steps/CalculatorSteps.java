package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;

public class CalculatorSteps {

    private int result = 0;  // Variable to hold the result of operations
    private int firstNumber = 0;
    private int secondNumber = 0;

    // Step definition for entering numbers into the calculator
    @Given("I have entered {int} into the calculator")
    public void i_have_entered_into_the_calculator(Integer number) {
        if (firstNumber == 0) {
            firstNumber = number;  // Store the first entered number
        } else {
            secondNumber = number;  // Store the second entered number
        }
    }

    // Step definition for the add operation
    @When("I press add")
    public void i_press_add() {
        result = firstNumber + secondNumber;  // Add the first and second number
    }

    // Step definition for the subtract operation
    @When("I press subtract")
    public void i_press_subtract() {
        result = firstNumber - secondNumber;  // Subtract the second number from the first number
    }

    // Step definition to check the result on the screen
    @Then("the result should be {int} on the screen")
    public void the_result_should_be_on_the_screen(Integer expectedResult) {
        assertEquals("Expected " + expectedResult + " but got " + result, expectedResult, (Integer) result);
    }
}
