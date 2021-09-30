import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

class GeoServiceTest {

    @Test
    @DisplayName("Проверка определения локации по ip")
    void testLocalbyIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        String ip = "172.0.32.11";
        String expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15).getStreet();
        String actual = geoService.byIp(ip).getStreet();
        Assertions.assertEquals(expected, actual);
    }
}