package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SqlHelper.*;

import ru.netology.data.SqlHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardTest {
    MainPage mainPage;
    PaymentPage paymentPage;

    @BeforeAll
    static void setUpAll () {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll () {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        mainPage = open("http://localhost:8080", MainPage.class);
        mainPage.goToDebitPage();
        paymentPage = new PaymentPage();
    }

    @AfterEach
    public void cleanBase() {
        clearDB();
    }

    @Test
    void shouldSubmitIfApprovedCard() {
        var info = getApprovedCard();
        paymentPage.inputData(info);
        paymentPage.waitNotificationApproved();
        //assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    @Test
    void shouldNotSubmitIfDeclinedCard() {
        var info = getDeclinedCard();
        paymentPage.inputData(info);
        paymentPage.waitNotificationFailure();
        //assertEquals("DECLINED", SqlHelper.getPaymentStatus());
    }

    @Test
    void shouldNotSubmitWithEmpty4FieldsCard() {
        var info = getEmptyAllFieldCard();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldNotSubmitWithEmptyCardNumber() {
        var info = getEmptyCardNumber();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldNotSubmitWithEmptyMonth() {
        var info = getEmptyCardMonth();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldNotSubmitWithEmptyYear() {
        var info = getEmptyCardYear();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldNotSubmitWithEmptyCardUser() {
        var info = getEmptyCardUser();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldNotSubmitWithEmptyCVV() {
        var info = getEmptyCardCVV();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldNotSubmitWith15DigitCardNumber() {
        var info = getNumberCard15Symbols();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithDifferentBankCard() {
        var info = getCardNotInDatabase();
        paymentPage.inputData(info);
        paymentPage.waitNotificationFailure();
    }

    @Test
    void shouldNotSubmitWithZeroAsMonth() {
        var info = getCardMonth00();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpirationDateError();
    }
    @Test
    void shouldNotSubmitWithSingleDigitMonth() {
        var info = getCardMonth1Symbol();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithOverLimitMonth() {
        var info = getCardMonthOver12();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpirationDateError();
    }

    @Test
    void shouldNotSubmitWithSingleDigitYear() {
        var info = getCardYear1Symbol();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithFutureYearBeyond6Years() {
        var info = getCardYearOverThisYearOn6();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpirationDateError();
    }

    @Test
    void shouldNotSubmitWithYearInPast() {
        var info = getCardFromLastYear();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpiredError();
    }

    @Test
    void shouldNotSubmitWithZeroYear() {
        var info = getCardYear00();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpiredError();
    }

    @Test
    void shouldNotSubmitWithSingleDigitCVV() {
        var info = getCardCvv1Symbol();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithTwoDigitCVV() {
        var info = getCardCvv2Symbols();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithSingleWordOwnerName() {
        var info = getCardHolder1Word();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithCyrillicOwnerName() {
        var info = getCardHolderCirillic();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithNumericOwnerName() {
        var info = getCardHolderNumeric();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithSpecialSymbolsInOwnerName() {
        var info = getCardHolderSpecialSymbols();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }
}
