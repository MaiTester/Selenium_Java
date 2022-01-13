import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class searchTest {

    @Test
    public void test() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        webDriver.get("http://ceshiren.com");
        webDriver.findElement(By.cssSelector("#search-button")).click();
        webDriver.findElement(By.cssSelector("#search-term")).sendKeys("selenium"+ Keys.ENTER);
        //显式等待
//        new WebDriverWait(webDriver,1000).until();
    }

    @Test
    public void testLogin() throws IOException, InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        webDriver.get("https://work.weixin.qq.com/wework_admin/frame");
        //sleep 10s
        Thread.sleep(15000);
        Set<Cookie> cookies = webDriver.manage().getCookies();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new File("src/main/resources/cookies.yaml"), cookies);

    }

    @Test
    void testLogined() throws IOException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        webDriver.get("https://work.weixin.qq.com/wework_admin/frame");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        //为啥这个泛型两边都要加
        TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String, Object>> cookies = objectMapper.readValue(new File("cookies.yaml"), typeReference);
        System.out.println(cookies);

        //driver添加cookie
        cookies.forEach(cookie -> {
                    webDriver.manage().addCookie(new Cookie(cookie.get("name").toString(), cookie.get("value").toString()));
                }
        );
        webDriver.navigate().refresh();
    }

}

