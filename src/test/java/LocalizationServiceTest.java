import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

class LocalizationServiceTest {

    @Test
    @DisplayName("Проверка возвращаемого текста")
    void testTextReturn() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        String actual = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals(expected, actual);
    }
}