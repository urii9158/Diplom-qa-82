package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.lang.Math;

public class DataHelper {

    // Карта, которая должна быть одобрена
    public static Card getApprovedCard() {
        return new Card("4444444444444441", "12", "24", "Denisova Ekaterina", "123");
    }

    // Карта, которая должна быть отклонена
    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "12", "24", "Denisova Ekaterina", "123");
    }

    // Пустая карта
    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }

    // Случайный месяц
    public static String getShiftedMonth(){
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    // Случайный год
    public static String getShiftedYear(int yearCount){
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    // Карта с номером в 15 символов
    public static Card getNumberCard15Symbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        String number = faker.number().digits(15);
        return new Card(number, month, year, "Denisova Ekaterina", cvv);
    }

    // Карта, которой нет в базе данных
    public static Card getCardNotInDatabase() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("1444444444444444", month, year, "Denisova Ekaterina", cvv);
    }

    // Карта с месяцем в 1 символ
    public static Card getCardMonth1Symbol() {
        Faker faker = new Faker();
        String month = faker.number().digit();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    // Карта с месяцем более 12
    public static Card getCardMonthOver12() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "15", year, "Denisova Ekaterina", cvv);
    }

    // Карта с месяцем 00
    public static Card getCardMonth00ThisYear() {
        Faker faker = new Faker();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "00", year, "Denisova Ekaterina", cvv);
    }

    // Карта с годом в 1 символ
    public static Card getCardYear1Symbol() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = faker.number().digit();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    // Карта с годом в будущем более чем на 6 лет
    public static Card getCardYearOverThisYearOn6() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(6);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    // Карта с годом меньше текущего
    public static Card getCardYearUnderThisYear() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    // Карта с годом 00
    public static Card getCardYear00() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, "00", "Denisova Ekaterina", cvv);
    }

    // Карта с 1 символом CVV
    public static Card getCardCvv1Symbol() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(1);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    // Карта с 2 символами CVV
    public static Card getCardCvv2Symbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(2);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina", cvv);
    }

    // Карта с одним словом в имении владельца
    public static Card getCardHolder1Word() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Ekaterina", cvv);
    }

    // Карта с именем владельца на кириллице
    public static Card getCardHolderCirillic() {
        Faker faker = new Faker(new Locale("ru"));
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Денисова Екатерина", cvv);
    }

    // Карта с номером в имени владельца
    public static Card getCardHolderNumeric() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova " + faker.number().digit(), cvv);
    }

    // Карта с спец. символами в имени
    public static Card getCardSpecialSymbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Denisova Ekaterina %$*!&", cvv);
    }
}
