package wx.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeWork {
    private WebDriver webDriver;

    private void start(){
        //①：驱动放到谷歌浏览器位置后，添加环境变量至Path  ②通过System.setProperty
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chrome/95/chromedriver.exe");
        //todo 多浏览器兼容测试时，需要根据变量调整
         webDriver = new ChromeDriver();
        webDriver.get("https://work.weixin.qq.com/wework_admin/frame");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public MainPage toMainPage(){
        start();
        loadCookie();
        return new MainPage(webDriver);
    }

    private void loadCookie()  {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        //为啥这个泛型两边都要加
        TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String, Object>> cookies = null;
        try {
//            cookies = objectMapper.readValue(this.getClass().getResource("/cookies.yaml"), typeReference);
            cookies = objectMapper.readValue(new File("src/main/resources/cookies.yaml"), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(cookies);

        //driver添加cookie
        cookies.forEach(cookie -> {
                    webDriver.manage().addCookie(new Cookie(cookie.get("name").toString(), cookie.get("value").toString()));
                }
        );
        webDriver.navigate().refresh();
    }
}
