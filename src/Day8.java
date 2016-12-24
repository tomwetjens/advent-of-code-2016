import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day8 {

    private static final int ROWS = 6;
    private static final int COLS = 50;

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day8.txt"));

//            List<String> lines = Arrays.asList(
//                    "rect 3x2",
//                    "rotate column x=1 by 1",
//                    "rotate row y=0 by 4",
//                    "rotate column x=1 by 1"
//            );

            int[] screen = new int[COLS * ROWS];

            for (String line : lines) {
                String[] commands = line.split(" ");

//                printScreen(screen);
//                System.out.println(line);

                if ("rect".equals(commands[0])) {
                    String[] dimensions = commands[1].split("x");
                    rect(screen, Integer.valueOf(dimensions[0]), Integer.valueOf(dimensions[1]));
                } else if ("rotate".equals(commands[0])) {
                    int index = Integer.valueOf(commands[2].split("=")[1]);
                    int amount = Integer.valueOf(commands[4]);

                    if ("column".equals(commands[1])) {
                        rotateCol(screen, index, amount);
                    } else {
                        rotateRow(screen, index, amount);
                    }
                }
            }

            printScreen(screen);

            long count = Arrays.stream(screen)
                    .filter(pixel -> pixel != 0)
                    .count();

            System.out.println("Pixels on: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printScreen(int[] screen) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(screen[COLS * row + col] != 0 ? "#" : " ");
            }
            System.out.println();
        }
    }

    private static void rotateRow(int[] screen, int row, int right) {
        for (int n = 0; n < right; n++) {
            int last = screen[COLS * row  + COLS - 1];
            for (int col = COLS - 1; col > 0; col--) {
                screen[COLS * row + col] = screen[COLS * row + col - 1];
            }
            screen[COLS * row] = last;
        }
    }

    private static void rotateCol(int[] screen, int col, int down) {
        for (int n = 0; n < down; n++) {
            int last = screen[COLS * (ROWS - 1) + col];
            for (int row = ROWS - 1; row > 0; row--) {
                screen[row * COLS + col] = screen[COLS * (row - 1) + col];
            }
            screen[col] = last;
        }
    }

    private static void rect(int[] screen, int cols, int rows) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                screen[row * COLS + col] = 1;
            }
        }
    }

}
