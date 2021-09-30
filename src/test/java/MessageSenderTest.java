import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderTest {

    MessageSenderImpl messageSender;
    Map<String, String> map;

    @BeforeEach
    public void setUp() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        map = new HashMap<>();
    }

    @Test
    @DisplayName("Проверка, что MessageSender всегда отправляет только русский текст," +
            " если ip относится к российскому сегменту адресов.")
    void sendRussianMessage() {
        map.put("x-real-ip", "172.0.32.11");
        String expected = messageSender.send(map);
        String actual = "Добро пожаловать";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка, что MessageSender всегда отправляет только английский текст," +
            " если ip относится к американскому сегменту адресов.")
    void sendEnglishMessage() {
        map.put("x-real-ip", "96.44.183.149");
        String expected = messageSender.send(map);
        String actual = "Welcome";
        Assertions.assertEquals(expected, actual);
    }
}