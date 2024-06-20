import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomHash {

    public static String getHash(String message, int hashLength, String algo) throws NoSuchAlgorithmException {

        SecureRandom secureRandom = SecureRandom.getInstance(algo);
        secureRandom.setSeed(message.getBytes());
        byte[] randomBytes = new byte[hashLength];
        secureRandom.nextBytes(randomBytes);

        return DataConverter.bytesToHex(randomBytes);
    }
}
