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
            salt = "D8539db3A749008C6EC0A02761579e00";
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.INFO, "Salt: " + salt);
    }
    
    @Test
    public void testMD5Hash() {
        try {
            HashGenerator hg = HashFactory.getInstance(HashFactory.CRYPTO_MD5);
            String passwd = hg.hash("password", salt);
            
            assertEquals(passwd, "2902d55ea95c929d95673dbd2c6fcf66");
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
            
            assertEquals(passwd, "c95f337cf9d09e1bec924c0ed5a7d635a55a6fe9");
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
            
            assertEquals(passwd, "a26a9165e80abb3e8783522c74333bdc583edc8189cef752615368e03470d9a2");
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
            
            assertEquals(passwd, "ebe617db7ae591a2dddf61cec1aeaeab114313ca4ac3d82ed446bf3f406cc70e");
            assertTrue(hg.check("password", salt, passwd));
            assertFalse(hg.check("fsdfsdfdsf", salt, passwd));
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.INFO, passwd);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
}