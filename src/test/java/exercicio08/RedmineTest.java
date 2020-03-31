package exercicio08;


import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author andreendo
 */
public class RedmineTest {

    WebDriver d;

    @BeforeAll
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--lang=pt-br");

        d = new ChromeDriver(chromeOptions);
        d.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after() {
        d.close();
    }

    @Test
    public void testLoginLogout() {
        d.get("http://demo.redmine.org/");
        d.findElement(By.linkText("Entrar")).click();
        
        d.findElement(By.id(("username"))).sendKeys("thetester");
        d.findElement(By.id(("password"))).sendKeys("1234");
        d.findElement(By.name(("login"))).click();
        
        assertEquals("Página inicial", d.findElement(By.xpath("//*[@id='content']/h2")).getText());
        assertEquals("Acessando como: thetester", d.findElement(By.id("loggedas")).getText());
        
        d.findElement(By.linkText("Sair")).click();
        
        assertTrue( d.findElement(By.tagName("body"))
        				.getText()
        				.contains("This is the online Redmine demo for uses to test Redmine") );
    }
    
    //IMPLEMENTE OUTROS TESTES PARA O REDMINE
}