package letscodeitpractice;

import letscodeit.PracticePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestPracticePage {

     WebDriver driver;
     PracticePage practicePage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\Omar Al-Faruque\\IdeaProjects\\SeleniumAprilL2020LetsCodeIt\\Drivers\\chromedriver81.exe");
        //System.setProperty("webdriver.gecko.driver",
        // "C:\\Users\\Omar Al-Faruque\\IdeaProjects\\SeleniumAprilL2020LetsCodeIt\\Drivers\\geckodriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.get("https://learn.letskodeit.com/p/practice");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }
    public void accessToLetsCodeItPracticePage(){
        practicePage = PageFactory.initElements(driver, PracticePage.class);

    }
    @Test
    public void radioButtonSelection() throws InterruptedException {
        boolean isChecked = false;
        //to select all the radio Buttons
//      List<WebElement> radioButtons = driver.findElements
//      (By.xpath("//input[contains(@type,'radio') and contains(@name,'cars')]"));

        //to select all the cars(radioButton and CheckBox)
        List<WebElement> radioButtons = driver.findElements(By.name("cars"));
        System.out.println("The size of the list is: "+radioButtons.size());
        for (int i = 0; i<radioButtons.size(); i++){
            //to check if the buttons are already selected
            isChecked = radioButtons.get(i).isSelected();
                if (!isChecked){
                    radioButtons.get(i).click();
                    Thread.sleep(2000);
                }
        }
    }

    @Test
    public void testCarSelectionBMWRadioButton(){
        accessToLetsCodeItPracticePage();
        practicePage.carSelectionBMWRadioButton();
    }
    @Test
    public void testCarSelectionBENZRadioButton(){
        accessToLetsCodeItPracticePage();
        practicePage.carSelectionBENZRadioButton();
    }
    @Test
    public void testCarSelectionHONDARadioButton(){
        accessToLetsCodeItPracticePage();
        practicePage.carSelectionHONDARadioButton();
    }
    @Test
    public void testSelectCarsFromTheList() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectCarsFromTheList("Honda");
    }
    @Test
    public void testMultipleSelectionFromTheList() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectMultipleOptionsFromTheList();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
