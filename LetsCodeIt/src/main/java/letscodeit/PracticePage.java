package letscodeit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PracticePage {

    @FindBy(xpath = "//input[@id='bmwradio']")
    WebElement bmwradio;
    @FindBy(id = "benzradio")
    WebElement benzradio;
    @FindBy(id = "hondaradio")
    WebElement hondaradio;

    @FindBy(xpath = "//select[@id='carselect']")
    WebElement selectCars;

    @FindBy(id = "multiple-select-example")
    WebElement multipleSelection;


    public void carSelectionBMWRadioButton(){
        bmwradio.click();
    }
    public void carSelectionBENZRadioButton(){
        benzradio.click();
    }
    public void carSelectionHONDARadioButton(){
        hondaradio.click();
    }
    public void selectCarsFromTheList(String car) throws InterruptedException {
        Select select = new Select(selectCars);
        select.selectByVisibleText(car);
        Thread.sleep(2000);
    }
    public void selectMultipleOptionsFromTheList() throws InterruptedException {
        Select select = new Select(multipleSelection);
        select.selectByValue("Orange");
        Thread.sleep(2000);
    }
}
