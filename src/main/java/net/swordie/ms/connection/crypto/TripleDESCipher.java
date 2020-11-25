package net.swordie.ms.connection.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 *
 * @author PacketBakery
 */
public class TripleDESCipher {

    public byte[] aKey = new byte[24];
    public Key pKey;

    public TripleDESCipher(byte[] aKey) {
        System.arraycopy(aKey, 0, this.aKey, 0, aKey.length);
        this.pKey = new SecretKeySpec(aKey, "DESede");
    }

    public byte[] Encrypt(byte[] aData) throws Exception {
        Cipher pCipher = Cipher.getInstance("DESede");
        pCipher.init(Cipher.ENCRYPT_MODE, this.pKey);
        return pCipher.doFinal(aData);
    }

    public byte[] Decrypt(byte[] aData) throws Exception {
        Cipher pCipher = Cipher.getInstance("DESede");
        pCipher.init(Cipher.DECRYPT_MODE, this.pKey);
        return pCipher.doFinal(aData);
    }
}
