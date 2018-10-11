package exercicio07;


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
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author andreendo
 */
public class CalculoDeImpostoTest {

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
        driver.close();
    }

    /**
     * Preecha o nome, a idade, o sexo e o salário e pressione o 
     * botão “Limpar campos”. 
     * Verifique se todos os campos foram limpados ou voltaram a opção original.
     */
    @Test
    public void testLimparCampos() {
        driver.get("https://andreendo.github.io/");
        
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
    @Test
    public void testImpostoMenor() {
        driver.get("https://andreendo.github.io/");
        
        WebElement eNome = driver.findElement(By.name("nome"));
        eNome.sendKeys("Maria Silva");
        
        WebElement eIdade = driver.findElement(By.name("idade"));
        eIdade.sendKeys("44");

        Select eSexo = new Select( driver.findElement(By.name("sexo")) );
        eSexo.selectByVisibleText("feminino");
        
        WebElement eSalario = driver.findElement(By.name("salario"));
        eSalario.sendKeys("2000");
        
        driver.findElement(By.xpath("/html/body/form/input[5]")).click();
        
        assertEquals("Nome: Maria Silva", driver.findElement(By.id("divNome")).getText());
        assertEquals("Idade: 44", driver.findElement(By.id("divIdade")).getText());
        assertEquals("Sexo: F", driver.findElement(By.id("divSexo")).getText());
        assertEquals("Valor a pagar de imposto: 300", driver.findElement(By.id("divValorImposto")).getText());
    }    
    
    /**
     * Preecha o nome, a idade, o sexo e o valor do salário maior que 5000 
     * e pressione o botão “Calcular”. 
     * Verifique se o cálculo do imposto foi correto. Neste caso, 25% de imposto
     */
    @Test
    public void testImpostoMaior() {    
        //IMPLEMENTE ESTE
    }
}
