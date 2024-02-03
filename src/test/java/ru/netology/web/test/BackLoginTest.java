package ru.netology.web.test;

import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanAuthCodes;
import static ru.netology.web.data.SQLHelper.cleanDateBase;

public class BackLoginTest {
    LoginPage loginPage;

    @AfterAll
    static void tearDownALL() {
        cleanDateBase();
    }

    @AfterEach
    void tearDown() {
        cleanAuthCodes();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    @DisplayName("Should successfully login to dashboard with exist login and password from sut test data")
    void successFullLogTest() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.VerificationPage();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerifi(verificationCode.getCode());

    }

    @Test
    @DisplayName("Should ger error notification if user not exist in base")
    void errorNotificationTest() {
        var authInfo = DataHelper.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotification("Ошибка! \nНеверно указан логин или пароль");

    }

    @Test
    @DisplayName(" Should ger error notification if login with exist in base and active user and random verification code")
    void errorNotificationWithExistUserAndRandomVerifyCodeTest() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.VerificationPage();
        var verificationCode = DataHelper.generateRandomVerifivationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotification("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");

    }


}
