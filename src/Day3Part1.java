import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3Part1 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day3.txt"));

            int count = 0;

            for (String line : lines) {
                String[] numbers = line.trim().split(" +");

                if (isPossible(
                        Integer.valueOf(numbers[0]),
                        Integer.valueOf(numbers[1]),
                        Integer.valueOf(numbers[2]))) {
                    count++;
                }
            }

            System.out.println("Possible triangles: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPossible(int a, int b, int c) {
        return a + b > c && b + c > a && c + a > b;
    }
}
