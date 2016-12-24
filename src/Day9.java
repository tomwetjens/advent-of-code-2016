import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day9 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day9.txt"));

            lines.forEach(line -> {
                long length = decompressedLength(line.toCharArray(), 0, line.length());

                System.out.println("Decompressed length: " + length);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long decompressedLength(char[] chars, int offset, int len) {
        long result = 0;

        boolean marker = false;
        int pos = 0;
        int repeatLen = 0;

        int i = offset;
        while (i < offset + len) {
            if (chars[i] == '(') {
                marker = true;
                pos = i;
            } else if (marker) {
                if (chars[i] == 'x') {
                    repeatLen = Integer.valueOf(new String(chars, pos + 1, i - pos - 1));
                    pos = i;
                } else if (chars[i] == ')') {
                    int repeats = Integer.valueOf(new String(chars, pos + 1, i - pos - 1));

                    result += decompressedLength(chars, i+1, repeatLen) * repeats;

                    i += repeatLen;

                    marker = false;
                }
            } else {
                result++;
            }

            i++;
        }

        return result;
    }

}
