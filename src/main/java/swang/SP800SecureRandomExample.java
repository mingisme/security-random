package swang;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.bouncycastle.crypto.prng.FixedSecureRandom;

import java.security.SecureRandom;
import java.util.Base64;

public class SP800SecureRandomExample {
    public static void main(String[] args) {
        byte[] seed = new byte[128];
        SecureRandom basic = new SecureRandom();
        basic.nextBytes(seed);
        SP800SecureRandomBuilder sp800SecureRandomBuilder = new SP800SecureRandomBuilder(new FixedSecureRandom(seed), false);
        SP800SecureRandom random = sp800SecureRandomBuilder.buildHMAC(new HMac(new SHA256Digest()), seed, false);
        for (int i = 0; i < 100; i++) {
            int j = random.nextInt();
            System.out.println(j);
            byte[] bytes = new byte[10];
            random.nextBytes(bytes);
            String s = Base64.getEncoder().encodeToString(bytes);
            System.out.println(s + " ("+s.length()+")");
        }

    }
}
