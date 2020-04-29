package letscodeitpractice;

import letscodeit.PracticePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Iterator;
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
        practicePage = PageFactory.initElements(driver,PracticePage.class);

    }
    @Test//Selecting all the radio Buttons and Check Box in one method
    public void radioButtonSelection() throws InterruptedException {
        boolean isChecked;
        //Selecting all the radio Buttons only
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
    @Test//Selecting BMW radio button
    public void testCarSelectionBMWRadioButton(){
        accessToLetsCodeItPracticePage();
        practicePage.carSelectionBMWRadioButton();
    }
    @Test//Selecting Benz radio button
    public void testCarSelectionBENZRadioButton(){
        accessToLetsCodeItPracticePage();
        practicePage.carSelectionBENZRadioButton();
    }
    @Test//Selecting Honda radio button
    public void testCarSelectionHONDARadioButton(){
        accessToLetsCodeItPracticePage();
        practicePage.carSelectionHONDARadioButton();
    }
    @Test//to test all the selecting items from the List
    public void testSelectCarsFromTheList() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//select[@id='carselect']"));
        Select select = new Select(element);
        select.selectByIndex(1);
        Thread.sleep(2000);
        select.selectByValue("bmw");
        Thread.sleep(2000);
        select.selectByVisibleText("Honda");
        Thread.sleep(2000);
    }
    @Test//to test all the selecting items from the List in one method
    public void testSelectCaresFromTheListUsingForLoop() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//select[@id='carselect']"));
        Select select = new Select(element);
        List<WebElement> elementList = select.getOptions();
        for (int i = 0; i<elementList.size(); i++){
            elementList.get(i).click();
            Thread.sleep(2000);
            System.out.println(elementList.get(i).getText());
        }
    }
    @Test//Select by visible text from dropdown
    public void testSelectCarsFromTheList1() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectCarsFromTheList1("Honda");
    }
    @Test//Select by value from dropdown
    public void testSelectCarsFromTheList2() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectCarsFromTheList2("bmw");
    }
    @Test//Select by index from dropdown
    public void testSelectCarsFromTheList3() throws InterruptedException {
        accessToLetsCodeItPracticePage();
        practicePage.selectCarsFromTheList(1);
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
    @Test//Window handling using for loop
    public void testWindowHandles() throws InterruptedException {
        String parentWindow = driver.getWindowHandle();//to get ID of parent Window
        System.out.println("Parent Window ID is: " +parentWindow);//to print parent window ID
        System.out.println("Parent Window Title is: " +driver.getTitle());//to print/assert parent window

        driver.findElement(By.xpath("//button[@id='openwindow']")).sendKeys(Keys.ENTER);//instead of .click method ENTER key is used
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
    @Test//Window Handling using Iterator
    public void testWindowHandling() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id='openwindow']")).click();
        //to store both window IDs in a Set
        Set<String> windowsID = driver.getWindowHandles();
        //to Iterate both window IDs
        Iterator<String> iterator = windowsID.iterator();
        iterator.hasNext();//to check if there has in iterator object/start iterator
        String parentWindow = iterator.next();//to go to the parent window
        String newWindow = iterator.next();//to go to the new window
        System.out.println("Parent WindowID is    : "+parentWindow);
        System.out.println("New WindowID is       : "+ newWindow);

        driver.switchTo().window(newWindow);//to switch to the new window
        System.out.println("New window title is   : "+ driver.getTitle());
        Thread.sleep(2000);
        //to perform any action on the new window
        driver.findElement(By.id("search-courses")).sendKeys("java");
        Thread.sleep(2000);
        driver.close();//to close new window
        driver.switchTo().window(parentWindow);//to switch to the parent window
        System.out.println("Parent Window Title is: " +driver.getTitle());
    }
    @Test//TabWindow Handling using for loop
    public void testNewTabHandling() throws InterruptedException {
        String parentWindowTabID = driver.getWindowHandle();
        System.out.println("Parent window ID is: "+parentWindowTabID);
        System.out.println("The parent tab window ID is: "+driver.getTitle());

        driver.findElement(By.id("opentab")).click();
        Set<String> windows = driver.getWindowHandles();
        for(String window: windows){
            System.out.println(window);
            if(!window.equals(parentWindowTabID)){
                driver.switchTo().window(window);
                driver.findElement(By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//a[1]//div[1]//div[1]//img[1]")).click();
                //driver.findElement(By.xpath("//input[@id='search-courses']")).sendKeys("java");
                driver.findElement(By.xpath("//a[@id='watchpromo']")).click();
                
                Thread.sleep(2000);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindowTabID);
        System.out.println("Returned window ID is: "+parentWindowTabID);
    }
    @Test//Alert Handling by accepting the alert
    public void alertHandlingByaccepting() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Mike Miller");
        driver.findElement(By.id("alertbtn")).click();
        Alert myAlert = driver.switchTo().alert();
        System.out.println("Alert's message: "+ myAlert.getText());
        Thread.sleep(2000);
        myAlert.accept();
    }
    @Test//Alert Handling by dismissing the alert
    public void alertHandlingByDismissing() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Mike Miller");
        driver.findElement(By.id("confirmbtn")).click();
        Alert myAlert = driver.switchTo().alert();
        System.out.println("Alert message: "+myAlert.getText());
        Thread.sleep(2000);
        myAlert.dismiss();
    }
    @Test//test Mouse Hovering to reload practice page
    public void mouseHovering() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("mousehover"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Reload')]")).click();
    }
    @Test//test Drag and Drop(in www.jquery.com)
    public void testDragAndDrop() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable,droppable).build().perform();
        Thread.sleep(3000);
    }
    @Test//test to get Text from Web Table
    public void testGettingTextFromWebTable(){
        String strOfTable = driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]")).getText();
        System.out.println(strOfTable);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
