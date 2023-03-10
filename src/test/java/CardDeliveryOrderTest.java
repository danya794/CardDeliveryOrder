import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryOrderTest {
    @Test
    void shouldDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $(By.cssSelector("[data-test-id='city'] .input__control")).setValue("Брянск");
        $(By.cssSelector("[data-test-id='date'] .input__control")).setValue("2023-03-13");
        $x("//input[@name='name']").setValue("Иванов Сидоров-Можанский");
        $(By.cssSelector("[data-test-id='phone'] .input__control")).setValue("+79161234455");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//*[contains(@class,'notification__title')]").should(appear, Duration.ofSeconds(15));


    }

}
