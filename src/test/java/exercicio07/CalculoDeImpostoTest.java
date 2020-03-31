package exercicio07;


import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author andreendo
 */
public class CalculoDeImpostoTest {

    WebDriver driver;

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

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after() {
        driver.close();
    }

    /**
     * Preecha o nome, a idade, o sexo e o salário e pressione o 
     * botão “Limpar campos”. 
     * Verifique se todos os campos foram limpados ou voltaram a opção original.
     */
    @Test
    public void testLimparCampos() {
        driver.get("https://andreendo.github.io/sample-ui-compras/index-2.html");
        
        WebElement eNome = driver.findElement(By.name("nome"));
        eNome.sendKeys("Jose Silva");
        
        WebElement eIdade = driver.findElement(By.name("idade"));
        eIdade.sendKeys("34");

        Select eSexo = new Select( driver.findElement(By.name("sexo")) );
        eSexo.selectByVisibleText("masculino");
        
        WebElement eSalario = driver.findElement(By.name("salario"));
        eSalario.sendKeys("2000");
        
        driver.findElement(By.xpath("/html/body/form/input[4]")).click();
        
        assertEquals("", eNome.getText());
        assertEquals("", eIdade.getText());
        assertEquals("-", eSexo.getFirstSelectedOption().getText());
        assertEquals("", eSalario.getText());
    }
    
    /**
     * Preecha o nome, a idade, o sexo e o valor do salário menor que 5000 
     * e pressione o botão “Calcular”. 
     * Verifique se o cálculo do imposto foi correto. Neste caso, 15% de imposto
     */
    @Disabled
    @Test
    public void testImpostoMenor() {
        driver.get("");
        
        WebElement eNome = driver.findElement(By.name(""));
        eNome.sendKeys("Maria Silva");
        
        WebElement eIdade = driver.findElement(By.name(""));
        eIdade.sendKeys("44");

        Select eSexo = new Select( driver.findElement(By.name("")) );
        eSexo.selectByVisibleText("feminino");
        
        WebElement eSalario = driver.findElement(By.name(""));
        eSalario.sendKeys("2000");
        
        driver.findElement(By.xpath("")).click();
        
        assertEquals("Nome: Maria Silva", driver.findElement(By.id("")).getText());
        assertEquals("Idade: 44", driver.findElement(By.id("")).getText());
        assertEquals("Sexo: F", driver.findElement(By.id("")).getText());
        assertEquals("Valor a pagar de imposto: 300", driver.findElement(By.id("")).getText());
    }    
    
    /**
     * Preecha o nome, a idade, o sexo e o valor do salário maior que 5000 
     * e pressione o botão “Calcular”. 
     * Verifique se o cálculo do imposto foi correto. Neste caso, 25% de imposto
     */
    @Disabled
    @Test
    public void testImpostoMaior() {    
        //IMPLEMENTE ESTE
    }
    
    private void comoApelarComJavaScript() {
        //ESTE CODIGO NAO EH FUNCIONAL, APENAS ILUSTRATIVO
        
        WebElement e = driver.findElement(By.id("algum_elemento"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", e);
    }
}