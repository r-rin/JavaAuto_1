import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String messageToHash = "Just a simple message";
        System.out.printf(
                "Message, which will be encoded: %s%n",
                messageToHash
        );

        testMessageDigest(messageToHash);
        testSecureRandom(messageToHash);
        testBadClass();
        testGoodClass();

        /* Additional */
        testSaltedHash(messageToHash);
    }

    private static void testGoodClass() {
        System.out.println("\n\n--- GOOD CLASS TEST");

        Cat cat1 = new Cat();
        Cat cat2 = new Cat(12, "Kitty", 5.1, true);
        Cat cat2Copy = new Cat(12, "Kitty", 5.1, true);
        Cat cat3 = new Cat(12, "Kitty", 5.1, false);
        Cat cat4 = new Cat(6, "Poppy", 8.6, true);

        HashMap<Cat, Integer> hashMap = new HashMap<>();
        hashMap.put(cat1, 1);
        System.out.println(hashMap);
        hashMap.put(cat2, 2);
        System.out.println(hashMap);
        hashMap.put(cat2Copy, 21);
        System.out.println(hashMap);
        hashMap.put(cat3, 3);
        System.out.println(hashMap);
        hashMap.put(cat4, 4);
        System.out.println(hashMap);
    }

    private static void testBadClass() {
        System.out.println("\n\n--- BAD CLASS TEST");

        Ket ket1 = new Ket();
        Ket ket2 = new Ket(12, "Kitty", 5.1, true);
        Ket ket2Copy = new Ket(12, "Kitty", 5.1, true);
        Ket ket3 = new Ket(12, "Kitty", 5.1, false);
        Ket ket4 = new Ket(6, "Poppy", 8.6, true);

        HashMap<Ket, Integer> hashMap = new HashMap<>();
        hashMap.put(ket1, 1);
        System.out.println(hashMap);
        hashMap.put(ket2, 2);
        System.out.println(hashMap);
        hashMap.put(ket2Copy, 21);
        System.out.println(hashMap);
        hashMap.put(ket3, 3);
        System.out.println(hashMap);
        hashMap.put(ket4, 4);
        System.out.println(hashMap);
    }

    private static void testSecureRandom(String messageToHash) {
        System.out.println("\n\n--- SECURE RANDOM");

        String[] algorithms = new String[]{"SHA1PRNG", "PKCS11", "NativePRNG", "Windows-PRNG"};
        int hashLength = 10;

        Arrays.stream(algorithms).forEach(
                algorithm -> {
                    try {
                        for(int i = 0; i < 3; i++) {
                            System.out.printf(
                                    "Hashed with %s message: %s%n",
                                    algorithm,
                                    SecureRandomHash.getHash(messageToHash, hashLength, algorithm)
                            );
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
        );
    }

    private static void testSaltedHash(String messageToHash) {
        SaltedHash saltedHashGen = new SaltedHash();
        String otherMessage = "Wassup! How are you doin'?";

        String saltedOriginal = saltedHashGen.getSaltedHash(messageToHash);
        String saltedOther = saltedHashGen.getSaltedHash(otherMessage);

        System.out.println("\n\n--- SALTED HASHES");

        System.out.printf(
                "otherMessage text: %s%n",
                otherMessage
        );

        System.out.printf(
                "Salted hash of original message: %s%n",
                saltedOriginal
        );

        System.out.printf(
                "Salted hash of otherMessage: %s%n",
                saltedOther
        );

        try {
            System.out.printf(
                    "Verifying saltedOriginal with original text: %s%n",
                    saltedHashGen.verify(saltedOriginal, messageToHash)
            );

            System.out.printf(
                    "Verifying saltedOriginal with otherMessage: %s%n",
                    saltedHashGen.verify(saltedOriginal, otherMessage)
            );

            System.out.printf(
                    "Verifying saltedOther with otherMessage: %s%n",
                    saltedHashGen.verify(saltedOther, otherMessage)
            );

            System.out.printf(
                    "Verifying saltedOriginal with original text: %s%n",
                    saltedHashGen.verify(saltedOther, messageToHash)
            );
        } catch (BadHashFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void testMessageDigest(String messageToHash) {
        System.out.println("\n--- MESSAGE DIGEST");

        System.out.printf(
                "MD5 hash result: %s%n",
                MessageDigestHash.getHash(messageToHash, "MD5")
        );
        System.out.printf(
                "SHA-1 hash result: %s%n",
                MessageDigestHash.getHash(messageToHash, "SHA-1")
        );
        System.out.printf(
                "SHA-256 hash result: %s%n",
                MessageDigestHash.getHash(messageToHash, "SHA-256")
        );
    }

}