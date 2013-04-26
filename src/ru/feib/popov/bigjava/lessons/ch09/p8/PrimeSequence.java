package ru.feib.popov.bigjava.lessons.ch09.p8;

import java.util.ArrayList;
/**
 *
 * @author popov
 */
public class PrimeSequence implements Sequence {
    private ArrayList<Integer> primes;
    
    public PrimeSequence() {
        primes = new ArrayList<>();
    }
    
    @Override
    public int next() {
        boolean isPrime = false;
        int currentInt = 
                ((primes.isEmpty()) ? 0 : primes.get(primes.size() - 1));
        
        //Find next prime number
        while(!isPrime) {
            currentInt++;
            
            isPrime = true; //Assume it's prime. Let's check it.
            for (int i = 0; i<primes.size(); i++) {
                if (primes.get(i) != 1 && currentInt % primes.get(i) == 0) {
                    /* if we can divide without remainder for something 
                     * except 1 - it's not prime, go next number
                     */
                    isPrime = false; 
                    break;
                }
            }
        }
        primes.add(currentInt);
        
        return currentInt;
    }
    
    @Override
    public boolean hasNext() {return true;}    
}
