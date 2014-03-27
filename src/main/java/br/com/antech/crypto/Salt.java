/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.antech.crypto;

import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class Salt {

    private static int length = 0;

    public static String generate(int length) throws NoSuchAlgorithmException, NoSuchProviderException {
        
        Salt.length = length;
        
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

        if (length == 0) {
            throw new InvalidParameterException();
        }

        byte[] salt = new byte[length];
        sr.nextBytes(salt);

        return dumpSaltString(salt);
    }

    private static String dumpSaltString(byte[] salt) {
        StringBuffer sb = new StringBuffer(salt.length);
        for (int i = 0; i < salt.length; i++) {
            int v = salt[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            
            String c = Integer.toHexString(v);
            sb.append((Math.random() > 0.5) ? c : c.toUpperCase());
        }
        return sb.substring(0, Salt.length);

    }
}
