import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final String HASH_ALGORITHM = "SHA-512";
    private static final int SALT_LENGTH = 16;

    // Generate a random salt
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash the password with the salt
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        digest.update(Base64.getDecoder().decode(salt));
        byte[] hashedBytes = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    // Verify the password
    public static boolean verifyPassword(String password, String salt, String expectedHash) throws NoSuchAlgorithmException {
        String hash = hashPassword(password, salt);
        return hash.equals(expectedHash);
    }

    public static void main(String[] args) {
        try {
            String password = "securePassword123";
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            System.out.println("Password: " + password);
            System.out.println("Salt: " + salt);
            System.out.println("Hashed Password: " + hashedPassword);

            // Verification
            boolean isPasswordValid = verifyPassword(password, salt, hashedPassword);
            System.out.println("Is password valid? " + isPasswordValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
