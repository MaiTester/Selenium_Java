package wx.web;

import org.openqa.selenium.WebDriver;

public class BasePage {
    public WebDriver webDriver;

    public BasePage(WebDriver webDriver){
        this.webDriver=webDriver;
    }
}
