/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.model.entities.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;

/**
 * Class to encrypt and decrypt strings 
 * Uses the library Apache Codec http://commons.apache.org/codec/
 * @author Artyom
 */
public class StringCrypter {
 
    /**
     * 
     * Default constructor. Creates StringCrypter with a DESSecretKey 
     * With the default value.
     */
    public StringCrypter() {
        this(new byte[]{1, 9, 9, 4, 0, 6, 0, 8});
    }
     
    /**
     * 
     * Simplified constructor. Creates StringCrypter with a 
     * DESSecretKey (encryption algorithm DES) with the value of the key. 
     * 
     * @param key must have a length of 8 bytes.
     */
    public StringCrypter(byte[] key) {
        try {
            updateSecretKey(new DESSecretKey(key));
        } catch (InvalidKeyException ex){
            throw new IllegalArgumentException(ex.getMessage());
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /**
     * 
     * @param key
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException 
     */
    public StringCrypter(SecretKey key) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {
        updateSecretKey(key);
    }
 
    /**
     * To update secret key.
     * 
     * @param key
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException 
     */
    private void updateSecretKey(SecretKey key) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {
        ecipher = Cipher.getInstance(key.getAlgorithm());
        dcipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }
 
    /**
     * Class which is the key for encryption / decryption.
     */
    public static class DESSecretKey implements SecretKey {
 
        private final byte[] key;
 
        /**
         * 
         * @param key must have a length of 8 bytes.
         */
        public DESSecretKey(byte[] key) {
            this.key = key;
        }
 
        @Override
        public String getAlgorithm() {
            return "DES";
        }
 
        @Override
        public String getFormat() {
            return "RAW";
        }
 
        @Override
        public byte[] getEncoded() {
            return key;
        }
    }
 
    private Cipher ecipher;
    private Cipher dcipher;
 
    /**
     * Encryption function
     *
     * @param str plaintext string
     * @return encrypted string in Base64 format
     */
    public String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return Base64.encodeBase64String(enc);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 
    /**
     * Decryption function
     *
     * @param str encrypted string in Base64 format
     * @return the decoded string
     * 
     */
    public String decrypt(String str) {
        try {
            byte[] dec = Base64.decodeBase64(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
