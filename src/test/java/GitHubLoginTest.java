import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class GitHubLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void setUpClass() {
        chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        //driver = new ChromeDriver(options);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest() throws InterruptedException {
        // Open main GitHub page
        driver.get("https://github.com/");
        // Click on 'Sign in' button
        wait.until(elementToBeClickable(By.xpath("//*[contains(@class, 'HeaderMenu')]//a[contains(text(), 'Sign in')]")));
        driver.findElement(By.xpath("//*[contains(@class, 'HeaderMenu')]//a[contains(text(), 'Sign in')]")).click();

        // Wait login form to be displayed
        wait.until(presenceOfElementLocated(By.id("login_field")));

        driver.findElement(By.id("login_field")).click();
        driver.findElement(By.id("login_field")).clear();
        driver.findElement(By.id("login_field")).sendKeys("nihad562");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Nihad271193");
        driver.findElement(By.cssSelector("[type = 'submit']")).click();

        wait.until(presenceOfElementLocated(By.xpath("//a[contains(@class, 'btn') and contains(text(), 'Create an organization')]")));

        driver.findElement(By.cssSelector("img.avatar-user.avatar-small")).click();

        WebElement userHeader = wait.until(presenceOfElementLocated(By.cssSelector(".dropdown-menu .header-nav-current-user")));
        assertEquals("Signed in as nihad562", userHeader.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
