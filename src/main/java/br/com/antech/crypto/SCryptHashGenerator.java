/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.antech.crypto;

/**
 *
 * @author Matheus Antonelli
 */
public class SCryptHashGenerator extends HashGenerator {

    private int iteration;
    private int keyLength;

    public SCryptHashGenerator() {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String hash(String passwd, String salt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
