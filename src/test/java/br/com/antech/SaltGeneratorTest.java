package br.com.antech;

import br.com.antech.crypto.Salt;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SaltGeneratorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSaltWithZeroLength() {
        thrown.expect(InvalidParameterException.class);
        
        try {
            Salt.generate(0);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void testSaltGenerator() {

        String salt = null;
        
        try {
            salt = Salt.generate(32);
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.INFO, salt);
            assertEquals(32, salt.length());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

        try {
            salt = Salt.generate(64);
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.INFO, salt);
            assertEquals(64, salt.length());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

        try {
            salt = Salt.generate(128);
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.INFO, salt);
            assertEquals(128, salt.length());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(SaltGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }
}