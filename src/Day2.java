import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {


    private static final String[] KEYPAD = {
            "  1  ",
            " 234 ",
            "56789",
            " ABC ",
            "  D  "
    };

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day2.txt"));

            AtomicInteger currentX = new AtomicInteger(0);
            AtomicInteger currentY = new AtomicInteger(2);

            StringBuilder code = new StringBuilder();

            lines.stream().map(line -> {
                line.chars().forEach(dir -> {
                    switch (dir) {
                        case 'U':
                            currentY.updateAndGet(y -> isButton(currentX.get(), y - 1) ? y - 1 : y);
                            break;
                        case 'D':
                            currentY.updateAndGet(y -> isButton(currentX.get(), y + 1) ? y + 1 : y);
                            break;
                        case 'L':
                            currentX.updateAndGet(x -> isButton(x - 1, currentY.get()) ? x - 1 : x);
                            break;
                        case 'R':
                            currentX.updateAndGet(x -> isButton(x + 1, currentY.get()) ? x + 1 : x);
                            break;
                        default: throw new IllegalArgumentException("unknown direction: " + dir);
                    }

                    System.out.println((char) dir + ": now at x=" + currentX.get() + ", y=" + currentY.get());
                });

                return KEYPAD[currentY.get()].charAt(currentX.get());
            }).forEach(code::append);

            System.out.println("Bath room code: " + code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isButton(int x, int y) {
        return y >= 0 && y < KEYPAD.length
                && x >= 0 && x < KEYPAD[y].length()
                && KEYPAD[y].charAt(x) != ' ';
    }
}
