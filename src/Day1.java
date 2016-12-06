import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {

    private static final int MAX_BLOCKS_X = 999;
    private static final int MAX_BLOCKS_Y = 999;

    private static final int CENTER_X = MAX_BLOCKS_X / 2;
    private static final int CENTER_Y = MAX_BLOCKS_Y / 2;

    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get("day1.txt")));
            String[] instructions = input.split(", ");

            int[][] visits = new int[MAX_BLOCKS_X][MAX_BLOCKS_Y];

            char direction = 'N';
            int x = 0;
            int y = 0;
            visits[CENTER_X + x][CENTER_Y + y] = 1;

            boolean easterBunnyHQ = false;

            for (int i = 0; i < instructions.length; i++) {
                char turn = instructions[i].charAt(0);
                int blocks = Integer.valueOf(instructions[i].substring(1).trim());

                int dx = 0;
                int dy = 0;

                switch (turn) {
                    case 'L':
                        switch (direction) {
                            case 'N':
                                direction = 'W';
                                dx = -1;
                                break;
                            case 'S':
                                direction = 'E';
                                dx = 1;
                                break;
                            case 'E':
                                direction = 'N';
                                dy = 1;
                                break;
                            case 'W':
                                direction = 'S';
                                dy = -1;
                                break;
                        }
                        break;
                    case 'R':
                        switch (direction) {
                            case 'N':
                                direction = 'E';
                                dx = 1;
                                break;
                            case 'S':
                                direction = 'W';
                                dx = -1;
                                break;
                            case 'E':
                                direction = 'S';
                                dy = -1;
                                break;
                            case 'W':
                                direction = 'N';
                                dy = 1;
                                break;
                        }
                        break;
                }

                // now visit all blocks for instruction
                int destx = x + dx * blocks;
                int desty = y + dy * blocks;

                while (x != destx || y != desty) {
                    x += dx;
                    y += dy;

                    int currentVisits = ++visits[CENTER_X + x][CENTER_Y + y];

                    if (!easterBunnyHQ && currentVisits == 2) {
                        System.out.println("Easter Bunny HQ is at " + x + "," + y + ", " + (Math.abs(x) + Math.abs(y)) + " blocks away");

                        easterBunnyHQ = true;
                    }
                }
            }

            System.out.println("Manhattan distance: " + (Math.abs(x) + Math.abs(y)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
