/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.antech.crypto;

import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * @author Matheus Antonelli
 * 
 * <p>
 * Generates salt to be used in hash creation
 * </p>
 * 
 * <p>
 * Example 1: <br/>
 * String salt = Salt.generate(32);
 * System.out.println(salt);
 * <br/><br/>
 * Output: 832609c6bb488064010879B814C895d1
 * </p>
 * 
 * <p>
 * Example 2: <br/>
 * String salt = Salt.generate(64);
 * System.out.println(salt);
 * <br/><br/>
 * Output: A407fb4C86b2D2dec3084dD89f7060328d36AD86d083DF796351e39Fd5C698b9
 * </p>
 * 
 * <p>
 * Example 3: <br/>
 * String salt = Salt.generate(128);
 * System.out.println(salt);
 * <br/><br/>
 * Output: 30105f01D6d41A21fa0aA0313b08d8FD4F6C59fe40D66B31aa0b9e66061FB2b6047200908876E229f728CD2b18B16Db4303293ccAEc1eb751ba9f5851225D0c7
 * </p>
 * 
 */
public class Salt {

    private static int length = 0;

    /**
     * 
     * @param length Salt length
     * @return Salt
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException 
     */
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
