package UniqueName;


import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

public class Uname {

    public static String getUniqueString (@NonNull List<String> strings)  {

        String [] uname = strings.stream().filter(e -> Collections.frequency(strings, e) == 1).
                collect(Collectors.joining(" ")).split(" ");

        return  uname[0];

    }
}
