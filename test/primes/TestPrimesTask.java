package primes;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

public class TestPrimesTask extends TestCase {

  public void testIsPrime() {
    assertFalse(PrimesTask.isPrime(new BigInteger("-2")));
    assertFalse(PrimesTask.isPrime(new BigInteger("-1")));
    assertFalse(PrimesTask.isPrime(new BigInteger("0")));
    assertFalse(PrimesTask.isPrime(new BigInteger("1")));
    assertTrue(PrimesTask.isPrime(new BigInteger("2")));
    assertTrue(PrimesTask.isPrime(new BigInteger("3")));
    assertFalse(PrimesTask.isPrime(new BigInteger("4")));
    assertTrue(PrimesTask.isPrime(new BigInteger("5")));
    assertFalse(PrimesTask.isPrime(new BigInteger("6")));
    assertTrue(PrimesTask.isPrime(new BigInteger("7")));
    assertFalse(PrimesTask.isPrime(new BigInteger("8")));
    assertFalse(PrimesTask.isPrime(new BigInteger("9")));
    assertFalse(PrimesTask.isPrime(new BigInteger("10")));
    assertTrue(PrimesTask.isPrime(new BigInteger("11")));
  }
  
  public void testFirstFive() {
    PrimesTask task = new PrimesTask(5);
    List<Integer> primes = new LinkedList<Integer>();
    while (task.hasNext()) {
      primes.add(task.next().intValue());
    }
    assertEquals(Arrays.asList(new Integer[] { 2, 3, 5, 7, 11}), primes);
  }
}
