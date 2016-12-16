import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7Part2 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day7.txt"));

            long count = lines.stream()
                    .filter(Day7Part2::supportsSSL)
                    .count();

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean supportsSSL(String ip) {
        Set<String> abas = new HashSet<>();
        Set<String> babs = new HashSet<>();

        char[] chars = ip.toCharArray();

        boolean betweenBrackets = false;

        for (int i = 2; i < chars.length; i++) {
            switch (chars[i]) {
                case '[':
                    betweenBrackets = true;
                    break;
                case ']':
                    betweenBrackets = false;
                    break;
                default:
                    if (chars[i - 2] == chars[i] && chars[i - 1] != chars[i]) {
                        if (betweenBrackets) {
                            babs.add(ip.substring(i - 2, i + 1));
                        } else {
                            abas.add(ip.substring(i - 2, i + 1));
                        }
                    }
            }
        }

        return abas.stream().anyMatch(aba ->
                babs.contains(new String(new char[]{aba.charAt(1), aba.charAt(0), aba.charAt(1)})));
    }
}
