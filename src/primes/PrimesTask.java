package primes;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import task.Task;

public class PrimesTask implements Task<BigInteger> {
  
  public final static BigInteger TWO = new BigInteger("2");
  
  protected final Set<BigInteger> sieve = new HashSet<BigInteger>();
  
  protected final int size;

  protected int found = 0; 
  
  protected BigInteger last = BigInteger.ONE;
  
  public static boolean isPrime(BigInteger i) {
    if (i.compareTo(TWO) < 0) return false;
    BigInteger half = i.divide(TWO);
    for (BigInteger k = TWO; k.compareTo(half) <= 0; k = k.add(BigInteger.ONE)) {
      if (i.mod(k).equals(BigInteger.ZERO)) {
        return false;
      }
    }
    return true;
  }
  
  public PrimesTask(int size) {
    this.size = size;
  }
  
  public boolean hasNext() {
    return found < size;
  }

  public BigInteger next() {
    BigInteger next = last.add(BigInteger.ONE);
    while (! isPrime(next)) {
      next = next.add(BigInteger.ONE);
    }
    last = next;
    found ++;
    return next;
  }

  public int size() {
    return size;
  }
}
