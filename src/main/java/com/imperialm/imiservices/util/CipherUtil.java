package com.imperialm.imiservices.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Both parties must agree upon a secret key (KEY) and initialization
 * vector (IV).
 *
 * @see http://download.oracle.com/javase/6/docs/api/javax/crypto/spec/IvParameterSpec.html
 * @see http://download.oracle.com/javase/6/docs/api/javax/crypto/spec/SecretKeySpec.html
 */
public class CipherUtil {
    private final String CHARACTER_ENCODING = "UTF-8";
    private final String KEY = "N1P8Bwv5971CU4j55Oh34cHa";
    private final String IV = "D5q4F6T2";
    private final byte[] KEY_BYTES = KEY.getBytes(CHARACTER_ENCODING);
    private final byte[] IV_BYTES = IV.getBytes(CHARACTER_ENCODING);

    private Cipher encryptCipher;
    private Cipher decryptCipher;

    /**
     * Triple DES Encryption
     *
     * @see http://en.wikipedia.org/wiki/Triple_DES
     * @see http://download.oracle.com/javase/6/docs/api/javax/crypto/CipherSpi.html
     */
    public CipherUtil() throws Exception {
        try {
            SecretKey key = new SecretKeySpec(KEY_BYTES, "DESede");
            IvParameterSpec iv = new IvParameterSpec(IV_BYTES);

            // algorithm/mode/padding
            this.encryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            this.encryptCipher.init(Cipher.ENCRYPT_MODE, key, iv);

            this.decryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            this.decryptCipher.init(Cipher.DECRYPT_MODE, key, iv);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    synchronized public String encrypt(String dataString) throws Exception {
        try {
            byte[] dataBytes = dataString.getBytes(CHARACTER_ENCODING);
            byte[] encryptedBytes = this.encryptCipher.doFinal(dataBytes);
            String encodedEncryptedString = new String(Base64.encodeBase64(encryptedBytes));

            return encodedEncryptedString;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    synchronized public String decrypt(String encodedEncryptedString) throws Exception {
        try {
            byte[] encryptedBytes = Base64.decodeBase64(encodedEncryptedString.getBytes());
            byte[] dataBytes = this.decryptCipher.doFinal(encryptedBytes);
            String dataString = new String(dataBytes, CHARACTER_ENCODING);

            return dataString;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
