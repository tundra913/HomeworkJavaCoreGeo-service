import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderTest {

    @Test
    @DisplayName("Проверка, что сообщения отправляются на русском языке(Если IP адрес в России")
    void sendRussianMessageNotStreet() {
        Map<String, String> map = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.32.36"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.36");
        String expected = "Добро пожаловать";
        String actual = messageSender.send(map);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Проверка, что сообщения отправляются на английском языке(Если IP адрес не в России")
    void sendEnglishMessage() {
        Map<String, String> map = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.548.149"))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.548.149");
        String expected = "Welcome";
        String actual = messageSender.send(map);
        Assertions.assertEquals(expected, actual);
    }
}