# Дипломный проект профессии «Тестировщик»

### Тестирование сервиса покупки тура "Путешествие дня"

[Ссылка на дипломное задание](https://github.com/netology-code/qa-diploma).

## Тестовая документация
1. [План тестирования](https://github.com/urii9158/Diplom-qa-82/blob/main/documents/Plan.md);
1. [Отчёт по итогам тестирования](https://github.com/urii9158/Diplom-qa-82/blob/main/documents/Report.md);
1. [Отчет по итогам автоматизации](https://github.com/urii9158/Diplom-qa-82/blob/main/documents/Summary.md);

## Запуск приложения
### Подготовительный этап
1. Установить и запустить IntelliJ IDEA;
1. Установать и запустить Docker Desktop;
1. Скопировать репозиторий [по ссылке](https://github.com/urii9158/Diplom-qa-82).
1. Открыть проект в IntelliJ IDEA.

### Запуск тестового приложения
1. Запустить MySQL, PostgreSQL, NodeJS командой:
   ```
   docker-compose up
   ```
2. В новой вкладке терминала запустить тестируемое приложение:
   ```
   java -jar artifacts/aqa-shop.jar
   ```

3. Убедиться в готовности системы. Приложение должно быть доступно по ссылке:
 * [http://localhost:8080/](http://localhost:8080/)


### Запуск тестов
В новой вкладке терминала запустить тесты:
   ```
   ./gradlew clean test --info
   ```

## Формирование отчёта о тестировании
### Предусмотрено формирование отчётности через Allure.

Для этого в новой вкладке терминала нужно ввести команду:
```
./gradlew allureserve
```
