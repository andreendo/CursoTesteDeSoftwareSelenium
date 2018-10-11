package exercicio06;


import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class BuscaGoogleTest {

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        //driver.close();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com.br/");
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("teste de software");

        searchInput.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().toLowerCase().startsWith("teste"));

        assertTrue(driver.getTitle().startsWith("teste de software"));
    }
}
