package primes;

import javax.swing.JFrame;

public class Main {
  static public void main(String[] args) throws Exception {
    JFrame frame = new PrimeNumbers();
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
