/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.antech;

import br.com.antech.crypto.HashFactory;
import br.com.antech.crypto.HashGenerator;
import br.com.antech.crypto.PBKDF2HashGenerator;
import br.com.antech.crypto.Salt;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mantonelli
 */
public class HashGeneratorTest {
    
    String salt;
    
    @Before
    public void setup() {
        try {
            salt = Salt.generate(32);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testMD5Hash() {
        try {
            HashGenerator hg = HashFactory.getInstance(HashFactory.CRYPTO_MD5);
            String passwd = hg.hash("password", salt);
            assertTrue(hg.check("password", salt, passwd));
            assertFalse(hg.check("fsdfsdfdsf", salt, passwd));
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.INFO, passwd);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testSHA1Hash() {
        try {
            HashGenerator hg = HashFactory.getInstance(HashFactory.CRYPTO_SHA1);
            String passwd = hg.hash("password", salt);
            assertTrue(hg.check("password", salt, passwd));
            assertFalse(hg.check("fsdfsdfdsf", salt, passwd));
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.INFO, passwd);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testSHA256Hash() {
        try {
            HashGenerator hg = HashFactory.getInstance(HashFactory.CRYPTO_SHA256);
            String passwd = hg.hash("password", salt);
            assertTrue(hg.check("password", salt, passwd));
            assertFalse(hg.check("fsdfsdfdsf", salt, passwd));
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.INFO, passwd);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testPBKDF2Hash() {
        try {
            PBKDF2HashGenerator hg = (PBKDF2HashGenerator) HashFactory.getInstance(HashFactory.CRYPTO_PBKDF2);
            hg.setIteration(5000);
            hg.setKeyLength(256);
            
            String passwd = hg.hash("password", salt);
            assertTrue(hg.check("password", salt, passwd));
            assertFalse(hg.check("fsdfsdfdsf", salt, passwd));
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.INFO, passwd);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
}