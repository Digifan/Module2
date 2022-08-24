package UniqueName;

import org.junit.jupiter.api.Test;

import java.util.List;

import static UniqueName.Uname.getUniqueString;
import static org.junit.jupiter.api.Assertions.*;

class UnameTest {

    @Test
    void getUniqueStringTest() {
        List<String> names = List.of( "john", "doe", "doe", "tom", "john");
        assertEquals("tom", getUniqueString(names));
    }
}