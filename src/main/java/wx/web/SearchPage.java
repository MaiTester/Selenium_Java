package wx.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver webDriver;
//    private By firstSearchResult = By.
    public SearchPage(WebDriver webDriver) {
        this.webDriver=webDriver;
    }

    public String getFirstResult() {
//        webDriver.findElement();
        return "selenium";
    }
}
