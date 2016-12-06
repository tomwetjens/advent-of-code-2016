import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Day5Part2 {

    public static void main(String[] args) {
        try {
//            final String door = "abc";
            final String door = "ojvtpuvg";

            int index = 0;

            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int passwordLength = 8;
            char[] password = new char[passwordLength];

            for (int i = 0; i < passwordLength; i++) {
                String input;
                String hash;
                int position;
                do {
                    input = door + index;
                    hash = DatatypeConverter.printHexBinary(md5.digest(input.getBytes())).toLowerCase();
                    position = Integer.valueOf(Character.toString(hash.charAt(5)), 16);
                    index++;
                } while (!hash.startsWith("00000") || position >= passwordLength || password[position] != '\0');

                char c = hash.charAt(6);
                System.out.println("Interesting hash found for '" + input + "': " + hash + ", position: " + position + ", character: " + c);

                password[position] = c;
            }

            System.out.println("Password: " + new String(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
