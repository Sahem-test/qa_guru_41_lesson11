package guru.qa.homeWork;


import guru.qa.TestBase;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static guru.qa.testData.TestData.*;
import static io.qameta.allure.Allure.step;

@Story("Registration form")
public class PracticeFormForJenkinsTests extends TestBase {


    @Test
    @DisplayName("Fill registration form, positive test")
    void fillPracticeFormPositiveTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Fill registration form", () -> {
            practiceFormPages.setFirstName(userName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .setDateOfBirth(day, month, year)
                    .setSubjects(subjects)
                    .setHobbies(hobby)
                    .uploadPicture(uploadPicture)
                    .setCurrentAddress(currentAddress)
                    .setStateAndCite(state, city)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldBeVisible()
                    .checkGreetingTextModalWindow()
                    .checkResult("Student Name", userName + " " + lastName)
                    .checkResult("Student Email", userEmail)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", userNumber)
                    .checkResult("Date of Birth", "1976-11-10")
                    .checkResult("Subjects", subjects)
                    .checkResult("Hobbies", hobby)
                    .checkResult("Picture", uploadPicture)
                    .checkResult("Address", currentAddress)
                    .checkResult("State and City", state + " " + city);
        });
    }

    @Test
    @DisplayName("Minimal required fields, positive test")
    void minimalRequiredFieldsPositiveTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Fill required fields", () -> {
            practiceFormPages.setFirstName(userName)
                    .setLastName(lastName)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldBeVisible()
                    .checkGreetingTextModalWindow()
                    .checkResult("Student Name", userName + " " + lastName)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", userNumber);
        });
    }

    @Test
    @DisplayName("Fill less then minimal required fields, negative test")
    void lessMinimalRequiredFieldsNegativeTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Don't fill in enough fields", () -> {
            practiceFormPages.setFirstName(userName)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldNotBeVisible();
        });
    }

    @Test
    @DisplayName("Phone has less required signs, negative test")
    void lessMinimalSignPhoneNegativeTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Fill registration form", () -> {
            practiceFormPages.setFirstName(userName)
                    .setLastName(lastName)
                    .setGender(gender)
                    .setUserNumber(wrongNumber)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldNotBeVisible();
        });
    }

    @Test
    @DisplayName("Empty form, negative test")
    void EmptyFormTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Submit form", () -> {
            practiceFormPages.submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldNotBeVisible()
                    .requirementFillFormTest();
        });

    }

}
