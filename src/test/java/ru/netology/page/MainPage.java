package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement heading = $$("h3").find(Condition.exactText("Выберите способ оплаты"));
    private SelenideElement buyWithDebitCardButton = $$("button").find(Condition.exactText("Купить"));
    private SelenideElement buyWithCreditCardButton = $$("button").find(Condition.exactText("Купить в кредит"));

    public MainPage() {
        heading.shouldBe(visible);
    }

    public PaymentPage goToDebitPage() {
        buyWithDebitCardButton.click();
        return new PaymentPage(); // Переход к странице оплаты дебетовой картой
    }

    public PaymentPage goToCreditPage() {
        buyWithCreditCardButton.click();
        return new PaymentPage(); // Переход к странице оплаты кредитной картой
    }
}
