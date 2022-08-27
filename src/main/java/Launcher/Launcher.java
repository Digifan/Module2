package Launcher;

import Dijkstra.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static DateParsing.DateFormat.dateFormat;
import static UniqueName.Uname.getUniqueString;


public class Launcher {
    private static final Logger logger = LoggerFactory.getLogger (Launcher.class);

    public static void main(String[] args) throws IOException {


        //task1
        logger.info("task1 started");
        List<String> dates = List.of("2022/04/05", "05/04/2022", "04-05-2022", "30-02-2022", "1-04/2022", "05.04.2022");
        ArrayList<String> localDate = dateFormat(dates);
        System.out.println("Initial string dates: " + dates);
        System.out.println(localDate);
        System.out.println();
        logger.info("task1 finished");

        //task2
        logger.info("task2 started");
        List <String> names = List.of( "john", "greg", "doe", "doe", "tom", "john");
        System.out.print("First unique name from the list " + names + " is: ");
        System.out.println(getUniqueString(names));
        System.out.println();
        logger.info("task2 finished");

        //task3
        logger.info("task3 started");
        Graph.start();
        logger.info("task3 finished");

    }
}
