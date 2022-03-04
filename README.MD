Содержит два варианта реализации тестового сценария с BDD и без

* запуск тестов с cucumber ./gradlew cucumberCli
* запуск тестов без cucumber ./gradlew test
* создать Allure отчет ./gradlew allureReport
* или создать и открыть сразу в браузере ю/gradlew allureServe
* файл лога лежит в logs/test.log

1. Слой с тестами, оперирует методами из пейдж объектов. 
   Сдесь же задается агент googlebot.
   1. Cucumber test
       * Фича файлы с геркин описанием лежат в src/test/resources/features
       * Реализация шагов лежит в src/test/java/cucumbersteps
       * Запустить тест из ItelliJ Idea можно в классе src/test/java/tests/CucumberFilterTests
       * Запустить из консоли можно командой ./gradlew cucumberCli
       * Cucumber отчет создается в  src/test/java/target
   2. Тот же тест без использования BDD, но легко читаемый, на мой взгляд более удобный подход
       * Класс с тестом src/test/java/tests/FilterTests, запускать из IDE тут же
       * Запуск из консоли ./gradlew test
   
2. Второй слой, с пейджобъектами  src/test/java/pages
   * содержит локаторы и методы с бизнес действиями
   * здесь же есть аннотации с Allure степов
   
3. Третий слой, большие блоки со страниц вынесены в отдельные классы и 
   композируются внутри пейдж объектов. Лежат в src/test/java/elements
   
