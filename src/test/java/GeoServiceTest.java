import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

class GeoServiceTest {

    public static Stream<Arguments> factory() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0).toString()),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15).toString()),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32).toString()),
                Arguments.of("172.3.32.112", new Location("Moscow", Country.RUSSIA, null, 0).toString()),
                Arguments.of("96.55.115.149", new Location("New York", Country.USA, null, 0).toString())
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка определения локации по ip")
    @MethodSource("factory")
    void testLocalbyIp(String ip, String expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        String actual = geoService.byIp(ip).toString();
        Assertions.assertEquals(expected, actual);
    }
}