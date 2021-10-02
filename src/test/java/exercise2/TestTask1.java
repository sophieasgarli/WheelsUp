package exercise2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.TimeUnit;
import java.util.logging.SocketHandler;

public class TestTask1 {
    @Test
    public void test () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("https://wheelsup.com");
        driver.manage().window().maximize();

        WebDriverWait wait=new WebDriverWait(driver,3);
      // 1. Find and print out in the console the following title “Flying, Personalized”
       WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),\"Flying, Personalized\")]")));
       String strHeader = header.getText();
        System.out.println(strHeader);
      // 2. Scroll down and print out in the console “Discover The Possibilities”
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)","");
        WebElement discoverHeader =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Discover The Possibilities']")));        System.out.println(discoverHeader.getText());
        // 3. Scroll to the bottom, print out phone number, email(Contact Us), and address(Find Us)
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement phoneNum = driver.findElement(By.xpath("//a[contains(text(),' 855-FLY-8760 ')]"));
        WebElement email = driver.findElement(By.xpath("//a[contains(text(),' info@wheelsup.com ')]"));
        WebElement address = driver.findElement(By.cssSelector("span[class=base-label]"));
        System.out.println("Contact Us: \n"+phoneNum.getText()+"\n"+email.getText());
        System.out.println("Find Us : \n"+address.getText());

        // 4. Scroll Up
        driver.findElement(By.xpath("(//*[@class='icon-trendingflat'])[2]")).click();
        Thread.sleep(2000);
        // Click Fly tab
        // Fly, FLEET ... row visible only on maximised screen, without window.Maximize element is Not intractable
        WebElement fly = driver.findElement(By.xpath("//a[contains(text(), 'Fly')]"));
        fly.click();
        //click core membership
        WebElement coreMembership = driver.findElement(By.xpath("//a[contains(text(), 'CORE MEMBERSHIP')] "));
        coreMembership.click();
        Thread.sleep(2000);
        // Scroll to section “Private aviation meets Social Aviation”
        js.executeScript("window.scrollBy(0,2500)","");
        //Print out in the console “SHARED FLIGHTS”, “SHUTTLE FLIGHTS” and “HOT FLIGHTS”
        Thread.sleep(2000);
        WebElement sharedFlights = driver.findElement(By.xpath("//*[contains(text(),'SHARED FLIGHTS')]"));
        WebElement shuttleFlights = driver.findElement(By.xpath("//*[contains(text(),'SHUTTLE FLIGHTS')]"));
        WebElement hotFlight = driver.findElement(By.xpath("//*[contains(text(),'HOT FLIGHTS')]"));
        System.out.println(sharedFlights.getText());
        System.out.println(shuttleFlights.getText());
        System.out.println(hotFlight.getText());

       // Scroll to bottom -> “Learn more today” section (Enter first and last name)
        js.executeScript("window.scrollBy(0,2100)","");
        WebElement name=driver.findElement(By.xpath("//*[@id='FirstName-clone']"));
        name.sendKeys("First NAME");
        WebElement lastName=driver.findElement(By.xpath("//*[@id='LastName-clone']"));
        lastName.sendKeys("Last NAME");
        //- Click continue
        driver.findElement(By.xpath("//*[text()=' CONTINUE ']")).click();


       // - Validate url contains (request-info)
        String url=driver.getCurrentUrl();
        boolean data=url.contains("request-info");
        Assert.assertTrue(data);

       // - Enter the respective data to all fields (request-info) - DO NOT CLICK SUBMIT
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#Email-clone")).sendKeys("emailReq@info.com");
        driver.findElement(By.cssSelector("#Phone-clone")).sendKeys("555-555-4444");
        driver.findElement(By.cssSelector("#Company__c-clone")).sendKeys("Wheels UP");
        driver.findElement(By.cssSelector("#Address-clone")).sendKeys("01 Java Dr");
        driver.findElement(By.cssSelector("#City-clone")).sendKeys("Selenium City");
        driver.findElement(By.cssSelector("#PostalCode-clone")).sendKeys("010101");
        driver.findElement(By.cssSelector("#State-clone")).sendKeys("Silicon Valley");
        driver.findElement(By.cssSelector("#Country-clone")).sendKeys("Automation");

       // - Clich “X” at the top right corner
        WebElement closeButton=  driver.findElement(By.xpath("(//i[@class='icon-close'])[3]"));
      //  js.executeScript("arguments[0].scrollIntoView(true);",closeButton);
       // js.executeScript("arguments[0].click();",closeButton);
        Actions actions=new Actions(driver);
        actions.moveToElement(closeButton).click(closeButton);

        driver.close();

    }

}
