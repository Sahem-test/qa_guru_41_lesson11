package guru.qa.classWork;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTestsClassWork {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browserVersion = "128.0";
        Configuration.pageLoadStrategy = "eager";

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @Test
    void FillPracticeFormPositiveTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("test@mail.com");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("8985426455");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1976");
        $(".react-datepicker__day--010").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbies-checkbox-1").click();
        $("#uploadPicture").uploadFromClasspath("test.txt");
        $("#currentAddress").setValue("new address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-body").shouldHave(text("Ivan Ivanov"));
        $(".modal-body").shouldHave(text("test@mail.com"));
        $(".modal-body").shouldHave(text("Male"));
        $(".modal-body").shouldHave(text("8985426455"));
        $(".modal-body").shouldHave(text("10 November,1976"));
        $(".modal-body").shouldHave(text("Maths"));
        $(".modal-body").shouldHave(text("Sports"));
        $(".modal-body").shouldHave(text("test.txt"));
        $(".modal-body").shouldHave(text("new address"));
        $(".modal-body").shouldHave(text("NCR Delhi"));
    }
    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
