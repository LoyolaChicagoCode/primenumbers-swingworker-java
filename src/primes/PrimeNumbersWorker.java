package primes;

import java.math.BigInteger;
import java.util.List;

import javax.swing.JTextArea;

import task.TaskWorker;

public class PrimeNumbersWorker extends TaskWorker<BigInteger> {

  protected JTextArea textArea;
  
  public PrimeNumbersWorker(JTextArea textArea, int numbersToFind) { 
    super(new PrimesTask(numbersToFind));
    this.textArea = textArea;
  }
  
  @Override
  protected void process(List<BigInteger> chunks) {
    for (BigInteger number : chunks) {
      textArea.append(number.intValue() + "\n");
    }
  }
}
