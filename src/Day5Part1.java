import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.*;

public class Day5Part1 {

    public static void main(String[] args) {
        try {
            //final String door = "abc";
            final String door = "ojvtpuvg";

            int index = 0;

            MessageDigest md5 = MessageDigest.getInstance("MD5");

            StringBuilder password = new StringBuilder();

            for (int i = 0; i < 8; i++) {
                String input;
                String hash;
                do {
                    input = door + index;
                    hash = DatatypeConverter.printHexBinary(md5.digest(input.getBytes())).toLowerCase();
                    index++;
                } while (!hash.startsWith("00000"));

                System.out.println("Interesting hash found for '" + input + "': " + hash);

                password.append(hash.charAt(5));
            }

            System.out.println("Password: " + password.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
