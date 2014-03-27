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
 * @author Matheus Antonelli
 */
class MD5HashGenerator extends HashGenerator {

    MessageDigest md;
    
    public MD5HashGenerator() {
        try {
            md = MessageDigest.getInstance(HashFactory.CRYPTO_MD5);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5HashGenerator.class.getName()).log(Level.SEVERE, null, ex);
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
