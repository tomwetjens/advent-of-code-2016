import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day4Part1 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day4.txt"));

            int sumSectors = 0;

            for (String line : lines) {
                String[] chunks = line.split("[\\[\\]\\-]");

                String checksum = chunks[chunks.length-1];
                int sector = Integer.valueOf(chunks[chunks.length-2]);

                // count occurrences of letters
                Map<Character, Integer> letters = new HashMap<>();
                for (int i = 0; i < chunks.length-2; i++) {
                    String chunk = chunks[i];

                    for (int j = 0; j < chunk.length(); j++) {
                        letters.compute(chunk.charAt(j), (k, v) -> v != null ? v + 1 : 1);
                    }
                }

                // sort letters by occurrence, ties broken alphabetically
                List<Map.Entry<Character, Integer>> sortedLetters = new ArrayList<>(letters.entrySet());
                sortedLetters.sort(Map.Entry.<Character, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()));

//                System.out.println("For " + line + " the letter counts are: " + sortedLetters);

                // calculate checksum for five most common letters
                StringBuilder calculatedChecksum = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    calculatedChecksum.append(sortedLetters.get(i).getKey());
                }

//                System.out.println("For " + line + " the calculated checksum is: " + calculatedChecksum);

                // check if it is a real room
                if (calculatedChecksum.toString().equals(checksum)) {
                    sumSectors += sector;

                    StringBuilder decrypted = new StringBuilder();
                    for (int i = 0; i < chunks.length-2; i++) {
                        String chunk = chunks[i];

                        for (int j = 0; j < chunk.length(); j++) {
                            int index = chunk.charAt(j) - 'a';
                            int rotated = (index + sector) % 26;
                            decrypted.append((char) ('a' + rotated));
                        }

                        decrypted.append(' ');
                    }

                    System.out.println("Line " + line + " decrypted as: " + decrypted);
                }
            }

            System.out.println("Sum of sector IDs of real rooms: " + sumSectors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
