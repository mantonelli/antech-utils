package br.com.antech.crypto;

import java.security.NoSuchAlgorithmException;

public class HashFactory {

    public static final String CRYPTO_SHA1   = "SHA-1";
    public static final String CRYPTO_SHA256 = "SHA-256";
    public static final String CRYPTO_MD5    = "MD5";
    public static final String CRYPTO_BCRYPT = "BCRYPT";
    public static final String CRYPTO_SCRYPT = "SCRYPT";
    public static final String CRYPTO_PBKDF2 = "PBKDF2";

    public static HashGenerator getInstance(String algorithm) throws NoSuchAlgorithmException {
        if (algorithm.equals(CRYPTO_MD5)) {
            return new MD5HashGenerator();
        } else if(algorithm.equals(CRYPTO_SHA1)) {
            return new SHA1HashGenerator();
        } else if(algorithm.equals(CRYPTO_SHA256)) {
            return new SHA256HashGenerator();
        } else if(algorithm.equals(CRYPTO_PBKDF2)) {
            return new PBKDF2HashGenerator();
        }

        throw new NoSuchAlgorithmException();
    }
}