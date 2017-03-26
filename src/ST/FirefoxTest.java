package ST;

import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.csvreader.CsvReader;

public class FirefoxTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://121.193.130.195:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testFirefox() throws IOException{
	  CsvReader r = new CsvReader("F://大学//软件测试技术//inputgit.csv", ',',Charset.forName("utf-8"));
	    r.readHeaders();
	    while (r.readRecord()) {
	        String id = r.get("id");
	        String password = id.substring(4);
	        String address = r.get("address");
	        driver.get(baseUrl + "/");
	       
	        driver.findElement(By.id("name")).clear();
	        driver.findElement(By.id("name")).sendKeys(id);
	        driver.findElement(By.id("pwd")).clear();
	        driver.findElement(By.id("pwd")).sendKeys(password);
	        driver.findElement(By.id("submit")).sendKeys(Keys.ENTER);
	        
	        WebElement text = driver.findElement(By.cssSelector("#table-main tr:last-child td:last-child"));
	        String address0 = text.getText();
	        assertEquals(address,address0);
	    }
	    r.close();
	  }


  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}


