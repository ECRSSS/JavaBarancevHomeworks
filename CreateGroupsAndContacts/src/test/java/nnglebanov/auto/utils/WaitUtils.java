package nnglebanov.auto.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    public static void waitForUrl(String url, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (driver.getCurrentUrl().equals(url)){
                    return Boolean.TRUE;
                }
                return null;
            }
        });
    }
}
