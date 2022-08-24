package DateParsing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static DateParsing.DateFormat.dateFormat;
import static org.junit.jupiter.api.Assertions.*;

class DateFormatTest {
    List<String> dates =
            List.of("2022/04/05", "05/04/2022", "04-05-2022", "30-02-2022", "1-04/2022", "05.04.2022");
    @Test
    void dateFormatTest() {
        ArrayList<String> expected = (ArrayList<String>) List.of("20220405", "20220405", "20220405");
        assertEquals(expected, dateFormat(dates));

    }
}