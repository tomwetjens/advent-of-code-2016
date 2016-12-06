import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day6Part1 {

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
                char mostFrequent = 0;
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    if (counts[i][mostFrequent] < counts[i][letter]) {
                        mostFrequent = letter;
                    }
                }
                System.out.print(mostFrequent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
