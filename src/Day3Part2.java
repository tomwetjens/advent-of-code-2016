import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3Part2 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day3.txt"));

            int count = 0;

            for (int i = 0; i < lines.size(); i+=3) {
                String[] line1 = lines.get(i).trim().split(" +");
                String[] line2 = lines.get(i+1).trim().split(" +");
                String[] line3 = lines.get(i+2).trim().split(" +");

                for (int j = 0; j < line1.length; j++) {
                    if (isPossible(
                            Integer.valueOf(line1[j]),
                            Integer.valueOf(line2[j]),
                            Integer.valueOf(line3[j]))) {
                        count++;
                    }
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
