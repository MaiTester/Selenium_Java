package wx.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage extends BasePage{

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * 跳转到通讯录页面
     * @return
     */
    public ContractPage toContactPage(){
        webDriver.findElement(By.id("menu_contacts")).click();
        return new ContractPage(webDriver);
    }
}
