package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement buyWithDebitCardButton = $$("button").find(Condition.exactText("Купить"));
    private SelenideElement buyWithCreditCardButton = $$("button").find(Condition.exactText("Купить в кредит"));
    private SelenideElement creditVersion = $$("h3").find(Condition.exactText("Кредит по данным карты"));
    private SelenideElement debitVersion = $$("h3").find(Condition.exactText("Оплата по карте"));

    public PaymentPage goToDebitPage() {
        buyWithDebitCardButton.click();
        debitVersion.shouldBe(visible);
        return new PaymentPage();
    }

    public PaymentPage goToCreditPage() {
        buyWithCreditCardButton.click();
        creditVersion.shouldBe(visible);
        return new PaymentPage();
    }
}
