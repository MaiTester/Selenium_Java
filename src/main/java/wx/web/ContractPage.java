package wx.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ContractPage extends BasePage {

    public ContractPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * 添加成员
     *
     * @return
     */
    public ContractPage addMember(String username, String account, String phone) {
        //有ID有name时，为什么优先使用name？
        //答：name是前后端交互的字段，要改的话，前后端都会改，所以name稳定性更高，id的话，前端可以随便改
        //todo 改为隐式等待，可能是JS把页面刷新了
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(By.xpath("//div[@class='ww_operationBar']/a[1]")).click();
        webDriver.findElement(By.name("username")).sendKeys(username);
        webDriver.findElement(By.name("acctid")).sendKeys(account);
        webDriver.findElement(By.name("mobile")).sendKeys(phone);
        webDriver.findElement(By.xpath("//form/div[1]/a[2]")).click();
        //因为点击保存，返回通讯录页面
        return this;
    }

    /**
     * 导入通讯率
     *
     * @param filePath
     * @return
     */
    public ContractPage importContract(String filePath) {
        //返回this，合并两个语句，实现链式调用
        return this;

    }

    /**
     * 获取当前通讯率（成员）列表
     *
     * @return
     */
    public List<String> getCurrentMemberList() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> memberList = new ArrayList();
        webDriver.findElements(By.xpath("//*[@id='member_list']//td[position()=2]/span")).forEach(webElement ->
                memberList.add(webElement.getText()));
        return memberList;
    }


    public ContractPage deleteMember(String username) {
        //通过xpath定位到姓名的td，在定位到前面的td标签(即单选框)，并勾选上
        webDriver.findElement(By.xpath("//*[text()='"+username+"']/../preceding-sibling::td[1]")).click();
        //点击ww_operationBar上的删除按钮
        webDriver.findElement(By.xpath("//div[@class='ww_operationBar']/a[3]")).click();
        //弹框点击确认
        webDriver.findElement(By.xpath("//div[@class='qui_dialog_foot ww_dialog_foot']/a[1]")).click();
        return this;
    }

    public ContractPage updateMember(String username){
        //根据入参用户姓名文本定位，进入编辑页面
        webDriver.findElement(By.xpath("//*[text()='"+username+"']")).click();
        //点击编辑
        webDriver.findElement(By.xpath("//a[@class='qui_btn ww_btn js_edit']")).click();
        //姓名后连接_1，表示更新过
        webDriver.findElement(By.name("username")).clear();
        webDriver.findElement(By.name("username")).sendKeys(username+"_1");
        //点击保存
        webDriver.findElement(By.xpath("//form/div[1]/a[1]")).click();
        //点击返回
        webDriver.findElement(By.xpath("//a[@class='qui_btn ww_btn ww_btn_Back js_back']")).click();
        return this;
    }


    /**
     *
     * @param content 查询的字符串
     * @return
     */
    public ContractPage searchMemberAndDepartment(String content){

        return this;
    }
    public ContractPage deleteAllMember() {
        return this;
    }
}
