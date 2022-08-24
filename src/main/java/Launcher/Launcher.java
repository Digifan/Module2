package Launcher;

import java.util.ArrayList;
import java.util.List;

import static DateParsing.DateFormat.dateFormat;
import static UniqueName.Uname.getUniqueString;


public class Launcher {

    public static void main(String[] args) {
        //task1
        List<String> dates = List.of("2022/04/05", "05/04/2022", "04-05-2022", "30-02-2022", "1-04/2022", "05.04.2022");
        ArrayList<String> localDate = dateFormat(dates);
        System.out.println(localDate);

        //task2
        List <String> names = List.of("greg", "john", "doe", "doe", "tom", "john");
        System.out.println(getUniqueString(names));

    }
}
