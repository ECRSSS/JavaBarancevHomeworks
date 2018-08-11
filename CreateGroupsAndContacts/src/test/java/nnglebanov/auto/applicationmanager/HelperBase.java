package nnglebanov.auto.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) { this.driver=driver; }

    protected void click(By locator){
        driver.findElement(locator).click();
    }
    protected void type(By locator,String text){
        WebElement element=driver.findElement(locator);
        element.clear();
        if(text!=null){
            element.sendKeys(text);
        }
    }
    protected String getValue(By locator){
        WebElement element=driver.findElement(locator);
        return element.getAttribute("value");
    }
    protected void selectByValue(By locator,String value){
        new Select(driver.findElement(locator)).selectByValue(value);
    }
    protected void selectByIndex(By locator,int index){
        new Select(driver.findElement(locator)).selectByIndex(index);
    }

    protected void selectByText(By locator,String text){
        new Select(driver.findElement(locator)).selectByVisibleText(text);
    }
}
