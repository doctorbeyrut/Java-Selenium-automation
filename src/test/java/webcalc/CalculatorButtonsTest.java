package webcalc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import webcalc.base.Setup;
import webcalc.pages.CalculatorPage;

import java.time.Duration;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

public class CalculatorButtonsTest extends Setup {

    @Test(priority = 1)
    public void calculateUsingButtonsTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        CalculatorPage calculator = new CalculatorPage(driver, log);

        calculator.openPage();
        calculator.clickConsent();

//        Calculate 35*999+(100/4)= and assert the correct result 34990.

        int firstCalculation = 35*999+(100/4);
        String firstExpectedResult = Integer.toString(firstCalculation);
        log.info("First calculation result:"+ " " + firstExpectedResult);

        calculator.calculateFirst();

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(calculator.inputField, "value", "35*999+(100/4)")));
        String firstActualResult = calculator.getInput().getAttribute("value");

        Assert.assertEquals(firstActualResult, firstExpectedResult, "Incorrect result");
        log.info("Correct result: " + firstActualResult);
        calculator.clearInput();

//        Calculate cos(pi) with the rad radio button and assert the correct result -1.

        double cpi = cos(Math.PI);
        int cosPi = (int)cpi;
        String secondExpectedResult = Integer.toString(cosPi);
        log.info("Second calculation result: " + secondExpectedResult);

        calculator.calculateSecond();

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(calculator.inputField, "value", "cos(pi)")));
        String secondActualResult = calculator.getInput().getAttribute("value");

        Assert.assertEquals(secondActualResult, secondExpectedResult, "Incorrect result");
        log.info("Correct result: " + secondActualResult);
        calculator.clearInput();

//        Calculate sqrt(81) and assert the correct result 9.

        double sqrt = sqrt(81);
        int squareRoot = (int)sqrt;
        String thirdExpectedResult = Integer.toString(squareRoot);
        log.info("Third calculation result: " + thirdExpectedResult);

        calculator.calculateThird();

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(calculator.inputField, "value", "sqrt(81)")));
        String thirdActualResult = calculator.getInput().getAttribute("value");

        Assert.assertEquals(thirdActualResult, thirdExpectedResult, "Incorrect result");
        log.info("Correct result: " + thirdActualResult);
        calculator.clearInput();

//        Press history dropdown and assert that the list contains the 3 operations executed e.g. 35*999+(100/4)=, cos(pi), sqrt(81)

        WebElement buttonHistory = driver.findElement(By.cssSelector("#hist > button.btn.dropdown-toggle.pull-right"));
        Actions action = new Actions(driver);
        action.contextClick();
        WebElement historyList = driver.findElement(By.cssSelector("#histframe > ul"));
        buttonHistory.click();
        List<WebElement> results = historyList.findElements(By.tagName("p"));

        String expectedCalculation1 = "35*999+(100/4)";
        String expectedCalculation2 = "cos(pi)";
        String expectedCalculation3 = "sqrt(81)";
        String actualCalculation1 = results.get(5).getAttribute("title");
        String actualCalculation2 = results.get(3).getAttribute("title");
        String actualCalculation3 = results.get(1).getAttribute("title");

        Assert.assertEquals(actualCalculation1, expectedCalculation1, "First calculation missing on the history list");
        log.info("First listed Calculation: " + actualCalculation1);

        Assert.assertEquals(actualCalculation2, expectedCalculation2, "Second calculation missing on the history list");
        log.info("Second listed Calculation: " + actualCalculation2);

        Assert.assertEquals(actualCalculation3, expectedCalculation3, "Third calculation missing on the history list");
        log.info("Third listed Calculation: " + actualCalculation3);

    }

}
