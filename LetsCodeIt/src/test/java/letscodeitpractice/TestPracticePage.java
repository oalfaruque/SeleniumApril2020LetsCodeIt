package letscodeitpractice;

import letscodeit.PracticePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestPracticePage {

     WebDriver driver;
     PracticePage practicePage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\Omar Al-Faruque\\IdeaProjects\\SeleniumAprilL2020LetsCodeIt\\Drivers\\chromedriver81.exe");
        driver = new ChromeDriver();
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
        boolean isChecked;
        //to select all the radio Buttons and Check Box in one method
//      List<WebElement> radioButtons = driver.findElements
//      (By.xpath("//input[contains(@type,'radio') and contains(@name,'cars')]"));

        //to select all the cars(radioButton and CheckBox)
        List<WebElement> radioButtons = driver.findElements(By.name("cars"));
        System.out.println("The size of the list is: "+radioButtons.size());
        for (int i = 0; i<radioButtons.size(); i++){
            //to check if the buttons are already selected
            isChecked = radioButtons.get(i).isSelected();
                if (!isChecked){//by default boolean variable is false
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
    //to test all the selecting items from the List in one method
    public void testSelectCarsFromTheList() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//select[@id='carselect']"));
        Select select = new Select(element);
        select.selectByIndex(1);
        Thread.sleep(2000);
        select.selectByValue("bmw");
        Thread.sleep(2000);
        select.selectByVisibleText("Honda");
        Thread.sleep(2000);
        //to get all the available listed options
        List<WebElement> elementList = select.getOptions();
        for (int i = 0; i<elementList.size(); i++){
            String options = elementList.get(i).getText();
            System.out.println(options);
        }
    }

    @Test
    public void testSelectCarsFromTheList1() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectCarsFromTheList1("Honda");
    }
    @Test
    public void testSelectCarsFromTheList2() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectCarsFromTheList2("bmw");
    }
    @Test
    public void testSelectCarsFromTheList3() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectCarsFromTheList(1);
    }

    @Test
    public void testMultipleSelectionFromTheList() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectMultipleOptionsFromTheList();
    }

    @Test//to choose multiple elements from the dropdown box
    public void testMultipleSelection() throws InterruptedException {
        WebElement apple = driver.findElement(By.xpath("//option[@value='apple']"));
        WebElement orange = driver.findElement(By.xpath("//option[@value='orange']"));
        WebElement peach = driver.findElement(By.xpath("//option[@value='peach']"));
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(apple).click(orange).click(peach).build().perform();
        Thread.sleep(2000);
    }
    @Test
    public void testWindowHandles() throws InterruptedException {
        String parentWindow = driver.getWindowHandle();//to get ID of parent Window
        System.out.println("Parent Window ID is: " +parentWindow);//to print parent window ID
        System.out.println("Parent Window Title is: " +driver.getTitle());//to print/assert parent window

        driver.findElement(By.xpath("//button[@id='openwindow']")).click();//to open new window
        Set<String> windows = driver.getWindowHandles();//to get all windows IDs
        for (String window : windows) {//for each loop to iterate all window IDs
            System.out.println(window);//to print all window IDs
            if(!window.equals(parentWindow)){//checking new window
                driver.switchTo().window(window);//switching to new window
                System.out.println("New Window Title is: " +driver.getTitle());//to get and print new window title
                Thread.sleep(3000);
                driver.findElement(By.id("search-courses")).sendKeys
                        ("Selenium WebDriver With Java");//to type in the new window
                driver.close();//to close new window
                }
            }
        driver.switchTo().window(parentWindow);//to get back into parent window
        System.out.println("Returned Parent Window Title is: "+driver.getTitle());//to print/assert returned window
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
