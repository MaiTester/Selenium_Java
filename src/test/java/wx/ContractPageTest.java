package wx;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import wx.web.ContractPage;
import wx.web.MainPage;
import wx.web.WeWork;
import wx.web.util.RandomDataProvider;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContractPageTest {

    private static MainPage mainPage;
    private static ContractPage contractPage;
    private static String username;//非静态时，方法取值null

    @BeforeAll
    static void beforeAll() {
        //todo 清理数据
        mainPage = new WeWork().toMainPage();
        contractPage = mainPage.toContactPage();
    }


    @ParameterizedTest
    @MethodSource("addMemberData")
    @Order(1)
    void addMemberTest(String username, String account, String phone) {
        ContractPageTest.username=username;
        List<String> memberList = contractPage
                //步骤一：添加成员
                .addMember(username, account, phone)
                //步骤二：获取当前成员列表
                .getCurrentMemberList();
        assertThat(memberList, hasItem(username));
    }

    @Test
    @Order(2)
    void updateMemberTest(){
        contractPage.updateMember(username);
    }

    @Test
    @Order(3)
    void deleteMemberTest(){
        contractPage.deleteMember(username+"_1");
    }


    void importContractTest(){

    }

    static Stream<Arguments> addMemberData() {
        String username = RandomDataProvider.getSysdate() + "_" + RandomDataProvider.getRandomStr(4);
        String phone = RandomDataProvider.getRandomPhone();
        return Stream.of(
                arguments(username, username, phone)
        );
    }
}