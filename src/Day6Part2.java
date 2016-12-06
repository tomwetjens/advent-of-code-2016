import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day6Part2 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day6.txt"));

            int[][] counts = new int[8]['z'+1];

            lines.stream()
                    .map(String::toCharArray)
                    .forEach(letters -> {
                        for (int i = 0; i < letters.length; i++) {
                            counts[i][letters[i]]++;
                        }
                    });

            for (int i = 0; i < counts.length; i++) {
                char leastFrequent = 0;
                counts[i][leastFrequent] = Integer.MAX_VALUE;

                for (char letter = 'a'; letter <= 'z'; letter++) {
                    if (counts[i][letter] > 0 && counts[i][letter] < counts[i][leastFrequent]) {
                        leastFrequent = letter;
                    }
                }
                System.out.print(leastFrequent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
