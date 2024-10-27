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

public class CreditCardTest {
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
        mainPage.goToCreditPage();
        paymentPage = new PaymentPage();
    }

    @AfterEach
    public void cleanBase() {
        clearDB();
    }

    @Test
    void shouldCreditSubmitIfApprovedCard() {
        var info = getApprovedCard();
        paymentPage.inputData(info);
        paymentPage.waitNotificationApproved();
        //assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    @Test
    void shouldCreditNotSubmitIfDeclinedCard() {
        var info = getDeclinedCard();
        paymentPage.inputData(info);
        paymentPage.waitNotificationFailure();
        //assertEquals("DECLINED", SqlHelper.getPaymentStatus());
    }

    @Test
    void shouldCreditNotSubmitWithEmpty4FieldsCard() {
        var info = getEmptyAllFieldCard();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldCreditNotSubmitWithEmptyCardNumber() {
        var info = getEmptyCardNumber();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldCreditNotSubmitWithEmptyMonth() {
        var info = getEmptyCardMonth();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldCreditNotSubmitWithEmptyYear() {
        var info = getEmptyCardYear();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldCreditNotSubmitWithEmptyCardUser() {
        var info = getEmptyCardUser();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldCreditNotSubmitWithEmptyCVV() {
        var info = getEmptyCardCVV();
        paymentPage.inputData(info);
        paymentPage.waitNotificationRequiredField();
    }

    @Test
    void shouldCreditNotSubmitWith15DigitCardNumber() {
        var info = getNumberCard15Symbols();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithDifferentBankCard() {
        var info = getCardNotInDatabase();
        paymentPage.inputData(info);
        paymentPage.waitNotificationFailure();
    }

    @Test
    void shouldCreditNotSubmitWithZeroAsMonth() {
        var info = getCardMonth00();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpirationDateError();
    }
    @Test
    void shouldCreditNotSubmitWithSingleDigitMonth() {
        var info = getCardMonth1Symbol();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithOverLimitMonth() {
        var info = getCardMonthOver12();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpirationDateError();
    }

    @Test
    void shouldCreditNotSubmitWithSingleDigitYear() {
        var info = getCardYear1Symbol();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithFutureYearBeyond6Years() {
        var info = getCardYearOverThisYearOn6();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpirationDateError();
    }

    @Test
    void shouldCreditNotSubmitWithYearInPast() {
        var info = getCardFromLastYear();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpiredError();
    }

    @Test
    void shouldCreditNotSubmitWithZeroYear() {
        var info = getCardYear00();
        paymentPage.inputData(info);
        paymentPage.waitNotificationExpiredError();
    }

    @Test
    void shouldCreditNotSubmitWithSingleDigitCVV() {
        var info = getCardCvv1Symbol();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithTwoDigitCVV() {
        var info = getCardCvv2Symbols();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithSingleWordOwnerName() {
        var info = getCardHolder1Word();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithCyrillicOwnerName() {
        var info = getCardHolderCirillic();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithNumericOwnerName() {
        var info = getCardHolderNumeric();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldCreditNotSubmitWithSpecialSymbolsInOwnerName() {
        var info = getCardHolderSpecialSymbols();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithThreeWordOwnerName() {
        var info = getCardHolder3Words();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithLowercaseOwnerName() {
        var info = getCardHolderDataInLowercase();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithUppercaseOwnerName() {
        var info = getCardHolderDataInUppercase();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithOwnerNameUnderMinLength() {
        var info = getCardHolderLessThan1Symbol();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }

    @Test
    void shouldNotSubmitWithOwnerNameOverMaxLength() {
        var info = getCardHolderMoreThan64Symbols();
        paymentPage.inputData(info);
        paymentPage.waitNotificationWrongFormat();
    }
}