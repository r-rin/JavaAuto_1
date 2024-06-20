import java.security.SecureRandom;

public class SaltedHash {

    private SecureRandom secureRandom;

    public SaltedHash(byte[] seed) {
        secureRandom = new SecureRandom(seed);
    }

    public SaltedHash() {
        secureRandom = new SecureRandom();
    }

    public String getSaltedHash(String message) {
        String salt = generateSalt();
        StringBuilder saltedMessage = new StringBuilder();
        saltedMessage.append(message).append(salt);
        
        String hashedMessage = MessageDigestHash.getHash(saltedMessage.toString(), "SHA-256");
        StringBuilder hash = new StringBuilder();
        hash.append(hashedMessage).append('.').append(salt);
        
        return hash.toString();
    }

    public boolean verify(String hash, String textToCheck) throws BadHashFormatException {
        String[] hashParts = hash.split("\\.");
        if (hashParts.length != 2) throw new BadHashFormatException("Wrong hash provided");

        String hashContent = hashParts[0];
        String salt = hashParts[1];

        StringBuilder saltedInput = new StringBuilder();
        saltedInput.append(textToCheck).append(salt);
        String hashedInput = MessageDigestHash.getHash(saltedInput.toString(), "SHA-256");

        return hashContent.equals(hashedInput);
    }

    private String generateSalt() {
        byte[] randomData = new byte[5];
        secureRandom.nextBytes(randomData);
        return DataConverter.bytesToHex(randomData);
    }
}

class BadHashFormatException extends Exception{
    public BadHashFormatException(String message) {
        super(message);
    }
}
