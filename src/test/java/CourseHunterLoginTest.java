import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CourseHunterLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void setUpClass() {
        chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void loginTest() {
        driver.get("https://coursehunter.net/");
        wait.until(elementToBeClickable(By.xpath("//a[text()='Sign in']")));
        driver.findElement(By.xpath("//a[text()='Sign in']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//input[@type='email']")));
        driver.findElement(By.xpath("//input[@name='e_mail']")).click();
        driver.findElement(By.xpath("//input[@name='e_mail']")).clear();
        driver.findElement(By.xpath("//input[@name='e_mail']")).sendKeys("nihadpzdc@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("cs2345iter");
        driver.findElement(By.cssSelector("[type = 'submit']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//button[@aria-label='User menu']")));
        driver.findElement(By.xpath("//button[@aria-label='User menu']")).click();
        driver.findElement(By.xpath("//a[@href='https://coursehunter.net/cabinet']")).click();
        WebElement userHeader = wait.until(presenceOfElementLocated(By.xpath("//div[@class='user-profile-name']")));
        assertEquals("nihadpzdc@gmail.com", userHeader.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
        }




}
