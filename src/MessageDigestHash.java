import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestHash {
    public static String getHash(String messageToHash, String hashingAlg) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(hashingAlg);
        } catch (NoSuchAlgorithmException exception) {
            System.err.println(exception.getMessage());
            return "0";
        }

        byte[] encodedHash = messageDigest.digest(
                messageToHash.getBytes(StandardCharsets.UTF_8)
        );

        return DataConverter.bytesToHex(encodedHash);
    }
}
