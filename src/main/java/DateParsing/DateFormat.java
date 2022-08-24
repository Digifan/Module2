package DateParsing;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateFormat {

    private static final List<DateTimeFormatter> dateFormats = new ArrayList<>() {
        {
            add(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            add(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            add(DateTimeFormatter.ofPattern("M/dd/yyyy"));
            add(DateTimeFormatter.ofPattern("dd.M.yyyy"));
        }
    };
        public static ArrayList<String> dateFormat(List<String> dates) {

            ArrayList<String> localDate = new ArrayList<>();

            for (String date : dates) {
                LocalDate ld = null;
                for (DateTimeFormatter dtf : dateFormats) {

                    try {
                        ld = LocalDate.parse(date, dtf);
                    } catch (DateTimeParseException ignored) {  }
                    if (ld != null  && ld.equals(LocalDate.of(2022, Month.APRIL, 5))) {
                        localDate.add(ld.format(DateTimeFormatter.BASIC_ISO_DATE));
                        break;
                    }
                }
            }
        return localDate;
    }
}



