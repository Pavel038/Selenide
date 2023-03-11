import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChromeTest {

    @Test
    void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Владикавказ");
        $x("//input[@placeholder='Дата встречи']").doubleClick();
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue("14.03.2023");
        $("[data-test-id='name'] input").setValue("Иван Петров-Иванов");
        $("[data-test-id='phone'] input").setValue("+79996788965");
        $("label[data-test-id='agreement']").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $x("//div[@data-test-id='notification']").should(Condition.visible, Duration.ofSeconds(15));
        String expected = "Встреча успешно забронирована на 14.03.2023";
        String actual = $x("//div[@class='notification__content']").getText();
        assertEquals(expected,actual);

    }

}
