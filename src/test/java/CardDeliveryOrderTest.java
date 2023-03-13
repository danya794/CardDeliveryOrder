import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryOrderTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        String planningDate = generateDate(4);
        open("http://localhost:9999/");
        $(By.cssSelector("[data-test-id='city'] .input__control")).setValue("Брянск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//input[@name='name']").setValue("Иванов Сидоров-Можанский");
        $(By.cssSelector("[data-test-id='phone'] .input__control")).setValue("+79161234455");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//*[contains(@class,'notification__content')]").should(appear, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
