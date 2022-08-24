package Launcher;

import java.util.ArrayList;
import java.util.List;

import static DateParsing.DateFormat.dateFormat;


public class Launcher {

    public static void main(String[] args) {
        List<String> dates = List.of("2022/04/05", "05/04/2022", "04-05-2022", "30-02-2022", "1-04/2022", "05.04.2022");
        ArrayList<String> localDate = dateFormat(dates);

        System.out.println(localDate);
    }
}
