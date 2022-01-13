package wx.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomDataProvider {

    /**
     * 随机生成电话号码
     *
     * @return 有效的11位电话号码
     */
    public static String getRandomPhone() {
        Random random = new Random();
        String[] phonePrefixArr = {"133", "149", "153", "173", "177",
                "180", "181", "189", "199", "130", "131", "132",
                "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "134", "135",
                "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172",
                "178", "182", "183", "184", "187", "188", "198", "170", "171"};
        //随机获取数组中的电话前缀
        String phonePrefix = phonePrefixArr[(int) (Math.random() * phonePrefixArr.length)];
        StringBuilder phonePostfix = new StringBuilder();
        for (int i = 0; i < 8; i++) {
//            phonePostfix.append((int)(Math.random() * 10));
            phonePostfix.append(random.nextInt(10));//好象这个更好用
        }
        return phonePrefix + phonePostfix;
    }


    /**
     * 获取格式化后的当前系统日期
     *
     * @return yyyyMMdd:20211224
     */
    public static String getSysdate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取指定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 随机字符串
     */
    public static String getRandomStr(int length) {
        Random random = new Random();
        if (length <= 0) {
            return "";
        } else {
            List<String> strList = new ArrayList<>();
            //添加26个大写字母
            for (int i = 0; i < 26; i++) {
                strList.add((char) (i + 65) + "");
            }
            //添加26个小写字母
            for (int i = 0; i < 26; i++) {
                strList.add((char) (i + 97) + "");
            }
            //添加0-9数字
            for (int i = 0; i < 10; i++) {
                strList.add(i + "");
            }
//            char[] codes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//                    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
//                    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
//                    'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
//                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
//                    'y', 'z'};
            //
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(strList.get((int) (Math.random() * strList.size())));

            }
            return sb.toString();
        }
    }


    public static void main(String[] args) {
        System.out.println(RandomDataProvider.getRandomStr(3));
        //这个Random与Math的random有什么区别？
        Random random = new Random();
        System.out.println(random.nextInt(10));

    }
}
