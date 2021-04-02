
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestS {

    @Test
    public void shouldSubmitRightRequest() {
        open("http://0.0.0.0:9999/");
        $("[data-test-id=name] input").setValue("Петрова Анна-Мария");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".paragraph").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldReturnErrorWhenNameIsINLatin() {
        open("http://0.0.0.0:9999/");
        $("[data-test-id=name] input").setValue("Vasya");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldReturnErrorWhenNumberWithoutPlus() {
        open("http://0.0.0.0:9999/");
        $("[data-test-id=name] input").setValue("Петрова Анна-Мария");
        $("[data-test-id=phone] input").setValue("77777777777");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldReturnErrorWithoutAgreement() {
        open("http://0.0.0.0:9999/");
        $("[data-test-id=name] input").setValue("Петрова Анна-Мария");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $(".button").click();
        $(".checkbox__text").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));

    }

    @Test
    public void shouldReturnErrorWithoutName() {
        open("http://0.0.0.0:9999/");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldReturnErrorWithoutPhone() {
        open("http://0.0.0.0:9999/");
        $("[data-test-id=name] input").setValue("Петрова Анна-Мария");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}