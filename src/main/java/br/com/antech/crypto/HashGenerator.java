package br.com.antech.crypto;

public abstract class HashGenerator {
    
    public abstract boolean check(String passwd, String salt, String hashedString);
    public abstract String  hash(String passwd, String salt);
        
    protected static String dumpPasswordString(byte[] passwd) {
            
        StringBuffer sb = new StringBuffer(passwd.length);
        for (int i = 0; i < passwd.length; i++) {
            int v = passwd[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        
        return sb.toString();

    }
}
