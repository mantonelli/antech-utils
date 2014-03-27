/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.antech.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mantonelli
 */
class SHA256HashGenerator extends HashGenerator {

    MessageDigest md;
    
    public SHA256HashGenerator() {
        try {
            md = MessageDigest.getInstance(HashFactory.CRYPTO_SHA256);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHA256HashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean check(String passwd, String salt, String hashedString) {
        if(hashedString.equals(hash(passwd, salt))) {
            return true;
        }
        return false;
    }

    public String hash(String passwd, String salt) {
        md.reset();
        md.update(salt.getBytes());
        md.update(passwd.getBytes());
        
        return dumpPasswordString(md.digest());
    }
    
    
    
}
