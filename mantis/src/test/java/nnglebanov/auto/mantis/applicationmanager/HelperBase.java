package nnglebanov.auto.mantis.applicationmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class HelperBase {
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
}