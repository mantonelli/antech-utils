/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.antech.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Matheus Antonelli
 */
public class PBKDF2HashGenerator extends HashGenerator {

    private int iteration;
    private int keyLength;

    public PBKDF2HashGenerator() {
        this.keyLength = 64;
        this.iteration = 1000;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration() {
        return this.iteration;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public int getKeyLength() {
        return this.keyLength;
    }

    @Override
    public boolean check(String passwd, String salt, String hashedString) {
        if(hashedString.equals(hash(passwd, salt))) {
            return true;
        }
        return false;
    }

    @Override
    public String hash(String passwd, String salt) {
        PBEKeySpec spec = new PBEKeySpec(
                passwd.toCharArray(),
                salt.getBytes(),
                getIteration(),
                getKeyLength());

        SecretKeyFactory key = null;
        try {
            key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PBKDF2HashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] hashedString = null;
        try {
            hashedString = key.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PBKDF2HashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dumpPasswordString(hashedString);

    }
}
