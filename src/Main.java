import java.io.FileInputStream;
import java.security.MessageDigest;

public class Main {

    public static String sha256(String path) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hash = digest.digest();

        StringBuilder result = new StringBuilder();
        for (byte b : hash) {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage:");
            System.out.println("java Main <file>");
            return;
        }

        try {
            String hash = sha256(args[0]);

            System.out.println("File: " + args[0]);
            System.out.println("SHA-256:");
            System.out.println(hash);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
