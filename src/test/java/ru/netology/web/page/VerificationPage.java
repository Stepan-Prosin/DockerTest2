package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotification=$("[data-test-id='error-notification'] .notification__content");
    public void VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }
    public void verifyErrorNotification(String expectedText){
        errorNotification.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }
    public DashboardPage validVerifi(String verificationCode){
        verify(verificationCode);
        return  new DashboardPage();

    }
    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();

    }
}
