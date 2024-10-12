package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Card;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selectors.byText;

public class PaymentPage {
    private SelenideElement heading = $$("h3").find(Condition.exactText("Оплата по карте"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private SelenideElement cardHolderField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");
    private SelenideElement continueButton = $$("button").find(Condition.exactText("Продолжить"));
    private SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]");
    private SelenideElement failureOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]");
    private SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private SelenideElement cardExpirationDateError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));

    public PaymentPage() {
        heading.shouldBe(visible);
    }

    public void inputData(Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvvField.setValue(card.getCvv());
        continueButton.click();
    }

    public void waitNotificationApproved() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationFailure() {
        failureOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationWrongFormat() {
        wrongFormatError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationExpirationDateError() {
        cardExpirationDateError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationExpiredError() {
        cardExpiredError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationRequiredField() {
        requiredFieldError.shouldBe(visible, Duration.ofSeconds(15));
    }
}
