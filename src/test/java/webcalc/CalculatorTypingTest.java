package webcalc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import webcalc.base.CsvData;
import webcalc.base.Setup;
import webcalc.pages.CalculatorPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CalculatorTypingTest extends Setup {

    @Test(priority = 2, dataProvider = "csvReader", dataProviderClass = CsvData.class)
    public void calculateUsingKeyboardTest(Map<String, String> testData) {

        String firstCalculation = testData.get("firstCalculation");
        String firstExpectedResult = testData.get("firstExpectedResult");
        String secondCalculation = testData.get("secondCalculation");
        String secondExpectedResult = testData.get("secondExpectedResult");
        String thirdCalculation = testData.get("thirdCalculation");
        String thirdExpectedResult = testData.get("thirdExpectedResult");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        CalculatorPage calculator = new CalculatorPage(driver, log);

        calculator.openPage();
        calculator.clickConsent();

//        Calculate 35*999+(100/4)= and assert the correct result 34990.

        calculator.type(firstCalculation, calculator.inputField);
        calculator.calculate();
        log.info("First calculation result:"+ " " + firstExpectedResult);

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(calculator.inputField, "value", firstCalculation)));
        String firstActualResult = calculator.getInput().getAttribute("value");

        Assert.assertEquals(firstActualResult, firstExpectedResult, "Incorrect result");
        log.info("Correct result: " + firstActualResult);
        calculator.clearInput();

//        Calculate cos(pi) with the rad radio button and assert the correct result -1.

        calculator.pressRad();
        calculator.type(secondCalculation, calculator.inputField);
        calculator.calculate();
        log.info("Second calculation result: " + secondExpectedResult);

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(calculator.inputField, "value", secondCalculation)));
        String secondActualResult = calculator.getInput().getAttribute("value");

        Assert.assertEquals(secondActualResult, secondExpectedResult, "Incorrect result");
        log.info("Correct result: " + secondActualResult);
        calculator.clearInput();

//        Calculate sqrt(81) and assert the correct result 9.

        calculator.pressRad();
        calculator.type(thirdCalculation, calculator.inputField);
        calculator.calculate();
        log.info("Third calculation result: " + thirdExpectedResult);

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(calculator.inputField, "value", thirdCalculation)));
        String thirdActualResult = calculator.getInput().getAttribute("value");

        Assert.assertEquals(thirdActualResult, thirdExpectedResult, "Incorrect result");
        log.info("Correct result: " + thirdActualResult);
        calculator.clearInput();

//        Press history dropdown and assert that the list contains the 3 operations executed e.g. 35*999+(100/4)=, cos(pi), sqrt(81)

        WebElement buttonHistory = driver.findElement(By.cssSelector("#hist > button.btn.dropdown-toggle.pull-right"));
        WebElement historyList = driver.findElement(By.cssSelector("#histframe > ul"));
        buttonHistory.click();
        List<WebElement> results = historyList.findElements(By.tagName("p"));


        String actualCalculation1 = results.get(5).getAttribute("title");
        String actualCalculation2 = results.get(3).getAttribute("title");
        String actualCalculation3 = results.get(1).getAttribute("title");

        Assert.assertEquals(actualCalculation1, firstCalculation, "First calculation missing on the history list");
        log.info("First listed Calculation: " + actualCalculation1);

        Assert.assertEquals(actualCalculation2, secondCalculation, "Second calculation missing on the history list");
        log.info("Second listed Calculation: " + actualCalculation2);

        Assert.assertEquals(actualCalculation3, thirdCalculation, "Third calculation missing on the history list");
        log.info("Third listed Calculation: " + actualCalculation3);

    }
}
