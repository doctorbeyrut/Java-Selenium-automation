package webcalc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.apache.logging.log4j.Logger;

public class CalculatorPage {

    protected WebDriver driver;
    protected Logger log;
    private String url = "https://web2.0calc.com/";
    private final By btn0 = By.id("Btn0");
    private final By btn1 = By.id("Btn1");
    private final By btn3 = By.id("Btn3");
    private final By btn4 = By.id("Btn4");
    private final By btn5 = By.id("Btn5");
    private final By btn8 = By.id("Btn8");
    private final By btn9 = By.id("Btn9");
    private final By btnMult = By.id("BtnMult");
    private final By btnPlus = By.id("BtnPlus");
    private final By btnDiv = By.id("BtnDiv");
    private final By btnParanL = By.id("BtnParanL");
    private final By btnParanR = By.id("BtnParanR");
    private final By btnCalc = By.id("BtnCalc");
    private final By btnClear = By.id("BtnClear");
    private final By btnCos = By.id("BtnCos");
    private final By btnPi = By.id("BtnPi");
    private final By btnRad = By.id("trigorad");
    private final By btnSqrt = By.id("BtnSqrt");
    public final By btnInput = By.id("input");
    private final By consentButton = By.className("fc-cta-do-not-consent");

    public CalculatorPage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }
    public void openPage() {
        log.info("Opening: " + url);
        driver.get(url);
    }
    protected WebElement locate(By locator) {
        return driver.findElement(locator);
    }
    private void press(By locator) {
        waitFor(locator);
        locate(locator).click();
    }
    private void waitFor(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void type(String text, By locator) {
        waitFor(locator);
        locate(locator).sendKeys(text);
    }
    public WebElement getInput() {
        return driver.findElement(btnInput);
    }
    public void calculate() {
        press(btnCalc);
    }
    public void pressRad() {
        press(btnRad);
    }
    public void calculateFirst() {
        press(btn3);
        press(btn5);
        press(btnMult);
        press(btn9);
        press(btn9);
        press(btn9);
        press(btnPlus);
        press(btnParanL);
        press(btn1);
        press(btn0);
        press(btn0);
        press(btnDiv);
        press(btn4);
        press(btnParanR);
        press(btnCalc);
    }
    public void calculateSecond() {
        press(btnRad);
        press(btnCos);
        press(btnPi);
        press(btnParanR);
        press(btnCalc);
    }
    public void calculateThird() {
        press(btnSqrt);
        press(btn8);
        press(btn1);
        press(btnParanR);
        press(btnCalc);
    }
    public void clearInput() {
        press(btnClear);
    }
    public void clickConsent() {
        press(consentButton);
    }

}
