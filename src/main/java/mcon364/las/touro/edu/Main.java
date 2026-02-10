package mcon364.las.touro.edu;

import java.util.List;
import java.util.Optional;

public class Main {
    static Optional<String> getUserName(String envVar) {
        var userName = System.getenv(envVar);
        return Optional.ofNullable(userName);
    }

   static String getGreeting(String envVar) {
        var userNameOpt = getUserName(envVar);
        var builder = new StringBuilder("Hello, ");
        if (userNameOpt.isEmpty())
            builder.append("there!");
        else
            builder.append(userNameOpt.get());
        return builder.toString();
    }

    public static int processValues(List<List<Integer>> values) {
        int rows = 0;
        outer:
        for(List<Integer> value : values) {
            inner:
            for(Integer i : value) {
                if(i==0)
                    continue outer;
                if(i==99)
                    break outer;
                rows++;
            }
        }
        return rows;
    }
    public static void main(String[] args) {
        getGreeting("USERNAME");
        getGreeting("NO_SUCH_VAR");
        List<List<Integer>> list = List.of(
                List.of(5, 10, 15),     // Processes completely
                List.of(20, 0, 25),     // Finds 0, skips to next list
                List.of(30, 35, 40),    // Processes completely
                List.of(45, 99, 50),    // Finds 99, terminates everything
                List.of(55, 60, 65)     // Never reached
        );
         processValues(list);
    }
}
