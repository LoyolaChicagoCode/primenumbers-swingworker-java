package primes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker.StateValue;

public class PrimeNumbers extends JFrame {

  static final long serialVersionUID = 123123000;

  private final JTextField howMany = new JTextField(5);
  private final JTextArea result = new JTextArea(5, 10);
  private final JLabel label = new JLabel("Number of primes to compute:");
  private final JButton start = new JButton("start");
  private final JButton cancel = new JButton("cancel");
  private final JPanel progress = new JPanel();
  private final JPanel input = new JPanel();
  private final JProgressBar progressBar = new JProgressBar(0, 100);

  private final PropertyChangeListener progressListener = new PropertyChangeListener() {
    public void propertyChange(PropertyChangeEvent evt) {
      if ("progress".equals(evt.getPropertyName())) {
        progressBar.setValue((Integer) evt.getNewValue());
      }
    }
  };
  
  private final PropertyChangeListener stateListener = new PropertyChangeListener() {
    public void propertyChange(PropertyChangeEvent evt) {
      if ("state".equals(evt.getPropertyName())) {
        StateValue state = (StateValue) evt.getNewValue();
        System.out.println("worker state = " + state);
        if (StateValue.STARTED.equals(state)) {
          start.setEnabled(false);
          cancel.setEnabled(true);
        } else if (StateValue.DONE.equals(state)) {
          worker = null;
          start.setEnabled(true);
          cancel.setEnabled(false);
        }
      }
    }
  }; 
  
  private PrimeNumbersWorker worker;

  public PrimeNumbers() {
    super("Prime Numbers");

    cancel.setEnabled(false);
    
    input.add(label);
    input.add(howMany);
    input.add(start);
    progress.add(progressBar);
    progress.add(cancel);
    getContentPane().add(input, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(result), BorderLayout.CENTER);
    getContentPane().add(progress, BorderLayout.SOUTH);

    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (worker != null) return;
        result.setText("");
        int numberOfPrimes = 0;
        try {
          numberOfPrimes = Integer.parseInt(howMany.getText());
        } catch (NumberFormatException ex) {
          return;
        }
        worker = new PrimeNumbersWorker(result, numberOfPrimes);
        worker.addPropertyChangeListener(progressListener);
        worker.addPropertyChangeListener(stateListener);
        worker.execute();
      }
    });
    
    cancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (worker == null || worker.isDone()) return;
        worker.cancel(true);
        progressBar.setValue(0);
      }
    });
  }
}
