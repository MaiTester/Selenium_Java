package wx.web;

public class Test {
    void test(){
        System.out.println(this.getClass().getResource("/cookies.yaml"));
    }
    public static void main(String[] args) {
        Test test =new Test();
        test.test();
    }
}
