import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by asadullah.qazi on 16/03/2016.
 */
public class Excercise1 {

    private WebDriver driver;
    private WebDriverWait wait;



    @Before
    public void setupBrowser(){

        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");


    }

    @Test
    public void selectFirstValueFromDropdown(){

        //WebElement DropdownValue = driver.findElement(By.cssSelector("#combo1 option[value='3']"));
        //wait.until(ExpectedConditions.

        //Verification Statment 1
        wait.until(ExpectedConditions.urlMatches("http://compendiumdev.co.uk/selenium/basic_ajax.html"));

        WebElement DropdownValue1 = driver.findElement(By.id("combo1"));
        Select selectValue1 = new Select(DropdownValue1);
        selectValue1.selectByValue("3");

        //Verification Statment 2
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy")));

        WebElement DropdownValue2 = driver.findElement(By.id("combo2"));
        Select selectValue2 = new Select(DropdownValue2);
        selectValue2.selectByValue("23");

        //Verification Statment 3
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("option[value='23']")));

        WebElement inputfield = driver.findElement(By.id("lteq30"));
        inputfield.sendKeys("20");

        WebElement btn = driver.findElement(By.cssSelector("input[type='submit']"));
        btn.click();

        //Verification Statment 4
        wait.until(ExpectedConditions.titleIs("Processed Form Details"));


        //WebElement VerifyResult = driver.findElement(By.cssSelector("_valueid"));~
        //int a = VerifyResult.

        WebElement VerifyResult = driver.findElement(By.id("_valueid"));
        String Matching1 = VerifyResult.getText();
        Assert.assertEquals("3",Matching1);


        //Extra Code
        if (Matching1.matches("3"))
        {

            System.out.println("Result Matched : " + Matching1);
        }
        else
        {
            System.out.println("Result Not Matched : ");
        }


    }


    @After
    public void afterTestSuccessful(){
        driver.quit();
    }
}
