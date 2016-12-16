import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day7Part1 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day7.txt"));

            long count = lines.stream()
                    .filter(Day7Part1::supportsTLS)
                    .count();

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean supportsTLS(String ip) {
        char[] chars = ip.toCharArray();

        boolean tls = false;
        boolean betweenBrackets = false;

        for (int i = 3; i < chars.length; i++) {
            char firstLetter = chars[i - 3];
            char secondLetter = chars[i - 2];
            char thirdLetter = chars[i - 1];
            char lastLetter = chars[i];

            boolean abba = firstLetter == lastLetter &&
                    secondLetter == thirdLetter &&
                    thirdLetter != lastLetter;

            betweenBrackets |= lastLetter == '[';
            betweenBrackets &= lastLetter != ']';

            if (abba && betweenBrackets) {
                return false;
            }

            tls |= abba;
        }

        return tls;
    }
}
