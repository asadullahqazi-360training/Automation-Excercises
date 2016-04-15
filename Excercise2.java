import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




/**
 * Created by asadullah.qazi on 24/03/2016.
 */
public class Excercise2 {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void setupTest()

    {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }





    @Test
    public void testCase1(){

        //Verification 1
        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));

        WebElement userName = driver.findElement(By.cssSelector("input[name='username']"));
        userName.sendKeys("asad");

        //Verification 2
        wait.until(ExpectedConditions.textToBePresentInElementValue(userName,"asad"));

        WebElement pwd = driver.findElement(By.cssSelector("input[name='password']"));
        pwd.sendKeys("password");

        //Verification 3
        wait.until(ExpectedConditions.textToBePresentInElementValue(pwd,"password"));

        WebElement btnTC1 = driver.findElement(By.cssSelector("input[value='submit']"));
        btnTC1.click();


        /**
         * Assert Expected Result 1
         *
         */
        String gettitle = driver.getTitle();
        Assert.assertEquals(gettitle,"Processed Form Details");

        if (gettitle.matches("Processed Form Details")){ System.out.println("Testcase 1 Pass"); }
        else { System.out.println("Testcase 1 Fail"); }


        /**
         * Assert Expected Result 2
         *QUESTION: can i user same web element again???
         *
         */
        String uname = driver.findElement(By.id("_valueusername")).getText();  //.findElement(By.id("_valueusername"));
        Assert.assertEquals(uname,"asad");


        if (uname.matches("asad")){ System.out.println("Testcase 2 Pass"); }
        else { System.out.println("Testcase 2 Fail"); }


        /**
         * Assert Expected Result 3
         *QUESTION: can i user same web element again???
         *
         */
        String pd = driver.findElement(By.id("_valuepassword")).getText();
        Assert.assertEquals(pd, "password");

        if (pd.matches("password")){ System.out.println("Testcase 3 Pass"); }
        else { System.out.println("Testcase 3 Fail"); }
    }




    @Test
    public void testCase2(){

        WebElement textArea = driver.findElement(By.cssSelector("textarea[name='comments']"));
        textArea.clear();
        //wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("textarea[name='comments']"),"Comments..."));
        textArea.sendKeys("This is my Testing Text. This is my Testing Text. This is my Testing Text.");

        WebElement btnTC2 = driver.findElement(By.cssSelector("input[value='submit']"));
        btnTC2.click();


        /**
         * Assert Expected Result 4
         *
         *
         */
        String readText = driver.findElement(By.id("_valuecomments")).getText();
        Assert.assertEquals(readText,"This is my Testing Text. This is my Testing Text. This is my Testing Text.");
    }





    @Test
    public void testCase3(){

        WebElement selectRadio = driver.findElement(By.cssSelector("input[value='rd1']"));
        selectRadio.click();

        wait.until(ExpectedConditions.elementToBeSelected(selectRadio));

        //Assert.assertTrue(selectRadio.isSelected());
        WebElement btnTC3 = driver.findElement(By.cssSelector("input[value='submit']"));
        btnTC3.click();


        /**
         * Assert Expected Result 5
         *
         */
        String readRadio = driver.findElement(By.id("_valueradioval")).getText();
        Assert.assertEquals(readRadio,"rd1");
        System.out.println(readRadio);
    }




    @Test
    public void testCase4(){

        WebElement seletCheckBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        WebElement seletCheckBox2 = driver.findElement(By.cssSelector("input[value='cb2']"));
        WebElement seletCheckBox3 = driver.findElement(By.cssSelector("input[value='cb3']"));

        /*if (seletCheckBox1.isSelected() || seletCheckBox2.isSelected() || seletCheckBox3.isSelected()){
            seletCheckBox1.click();
            seletCheckBox2.click();
            seletCheckBox3.click();

            seletCheckBox1.click();
        }*/

        if (seletCheckBox1.isSelected()){ seletCheckBox1.click(); }
        else if (seletCheckBox2.isSelected()) { seletCheckBox2.click(); }
        else if (seletCheckBox3.isSelected()){ seletCheckBox3.click(); }

        seletCheckBox1.click();
        wait.until(ExpectedConditions.elementToBeSelected(seletCheckBox1));
        WebElement btnTC4 = driver.findElement(By.cssSelector("input[value='submit']"));
        btnTC4.click();


        /**
         * Assert Expected Result 6
         *
         */
        String readCheckbox = driver.findElement(By.id("_valuecheckboxes0")).getText();
        Assert.assertEquals("cb1", readCheckbox);
        System.out.println("Testcase 4 Pass "+ readCheckbox);
    }




    @Test
    public void testCase5(){

        WebElement locateDropDown = driver.findElement(By.cssSelector("select[name='dropdown']"));
        //locateDropDown.clear();
        Select readDropDownValues = new Select(locateDropDown);
        //readDropDownValues.deselectAll();
        readDropDownValues.selectByValue("dd5");

        wait.until(ExpectedConditions.textToBePresentInElement(locateDropDown, "Drop Down Item 5"));
        WebElement btnTC5 = driver.findElement(By.cssSelector("input[value='submit']"));
        btnTC5.click();


        /**
         * Assert Expected Result 7
         *
         */
        String readDropDown = driver.findElement(By.id("_valuedropdown")).getText();
        Assert.assertEquals("dd5",readDropDown);
        System.out.println("Testcase 5 Pass "+readDropDown);
    }



    @Test
    public void testCase6(){

        WebElement multiSelectBox = driver.findElement(By.cssSelector("select[name='multipleselect[]']"));
        Select readMultiSelectBox = new Select(multiSelectBox);
        readMultiSelectBox.deselectAll();
        Actions builder = new Actions(driver);
        builder.keyDown(Keys.CONTROL)
                .click(readMultiSelectBox.getOptions().get(0))
                .click(readMultiSelectBox.getOptions().get(1))
                .click(readMultiSelectBox.getOptions().get(2))
                .keyUp(Keys.CONTROL);
        builder.build().perform();

        /*readMultiSelectBox.selectByValue("ms1");
        readMultiSelectBox.selectByValue("ms2");
        readMultiSelectBox.selectByValue("ms3");*/

        WebElement btnTC6 = driver.findElement(By.cssSelector("input[value='submit']"));
        btnTC6.click();


        /**
         * Assert Expected Result 8
         *
         */
        String readMultiSelectOptions0 = driver.findElement(By.id("_valuemultipleselect0")).getText();
        String readMultiSelectOptions1 = driver.findElement(By.id("_valuemultipleselect1")).getText();
        String readMultiSelectOptions2 = driver.findElement(By.id("_valuemultipleselect2")).getText();

        Assert.assertEquals("ms1",readMultiSelectOptions0);
        Assert.assertEquals("ms2",readMultiSelectOptions1);
        Assert.assertEquals("ms3",readMultiSelectOptions2);

        System.out.println("Testcase 6 Pass "+ readMultiSelectOptions0);
        System.out.println("Testcase 6 Pass "+ readMultiSelectOptions1);
        System.out.println("Testcase 6 Pass "+ readMultiSelectOptions2);
    }


    @Test
    public void testCase7BONUS(){

        WebElement browseButton = driver.findElement(By.name("filename"));
        browseButton.sendKeys("C:\\Users\\asadullah.qazi\\Pictures\\Time Spent 2.PNG");

        WebElement btnTC7 = driver.findElement(By.cssSelector("input[value='submit']"));
        btnTC7.click();

        /**
         * Assert Expected Result 9
         *
         */

        String getFileName = driver.findElement(By.id("_valuefilename")).getText();
        Assert.assertEquals("Time Spent 2.PNG",getFileName);


    }


   @After
    public void afterCompletionOfTestCases(){ driver.quit(); }
}
