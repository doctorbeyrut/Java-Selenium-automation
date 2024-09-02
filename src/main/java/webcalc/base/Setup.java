package webcalc.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class Setup {
    protected WebDriver driver;
    protected Logger log;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(@Optional("chrome") String browser, ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();
        String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
        log = LogManager.getLogger(testName);
        log.info(suiteName + " " + testName);

        DriverManager manager = new DriverManager(browser, log);
        driver = manager.createDriver();
        driver.manage().window().maximize();
    }
    @AfterTest
    public void tearDown(){
        log.info("Close browser");
        driver.quit();
    }
}
