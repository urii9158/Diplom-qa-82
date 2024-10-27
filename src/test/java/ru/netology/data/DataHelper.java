package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.lang.Math;

public class DataHelper {

    public static Card getApprovedCard() {
        return new Card("4444444444444441", "12", "24", "Denisova Ekaterina", "123");
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "12", "24", "Denisova Ekaterina", "123");
    }

    public static Card getEmptyAllFieldCard() {
        return new Card("", "", "", "", "");
    }

    public static Card getEmptyCardMonth() {
        return new Card("4444444444444441", " ", "24", "Denisova Ekaterina", "123");
    }

    public static Card getEmptyCardYear() {
        return new Card("4444444444444441", "12", " ", "Denisova Ekaterina", "123");
    }

    public static Card getEmptyCardUser() {
        return new Card("4444444444444441", "12", "24", " ", "123");
    }

    public static Card getEmptyCardCVV() {
        return new Card("4444444444444441", "12", "24", "Denisova Ekaterina", " ");
    }

    public static Card getEmptyCardNumber() {
        return new Card(" ", "12", "24", "Denisova Ekaterina", "123");
    }

    public static String getShiftedMonth(){
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount){
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static Card getNumberCard15Symbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        String number = faker.number().digits(15);
        return new Card(number, month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardNotInDatabase() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("1444444444444444", month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardMonth1Symbol() {
        Faker faker = new Faker();
        String month = faker.number().digit();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardMonthOver12() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "15", year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardMonth00() {
        Faker faker = new Faker();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "00", year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardYear1Symbol() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = faker.number().digit();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardYearOverThisYearOn6() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(6);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardFromLastYear() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(-1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardYear00() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, "00", "Denisova Ekaterina", cvv);
    }

    public static Card getCardCvv1Symbol() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(1);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardCvv2Symbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(2);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    public static Card getCardHolder1Word() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Ekaterina", cvv);
    }

    public static Card getCardHolderCirillic() {
        Faker faker = new Faker(new Locale("ru"));
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Денисова Екатерина", cvv);
    }

    public static Card getCardHolderNumeric() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova " + faker.number().digit(), cvv);
    }

    public static Card getCardHolderSpecialSymbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina %$*!&", cvv);
    }

    public static Card getCardHolder3Words() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina Ekaterina", cvv);
    }

    public static Card getCardHolderDataInLowercase() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "denisova ekaterina", cvv);
    }

    public static Card getCardHolderDataInUppercase() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "DENISOVA EKATERINA", cvv);
    }
    public static Card getCardHolderLessThan1Symbol() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "y", cvv);
    }

    public static Card getCardHolderMoreThan64Symbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String longName = faker.lorem().characters(65, 70, true, false);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, longName, cvv);
    }
}
