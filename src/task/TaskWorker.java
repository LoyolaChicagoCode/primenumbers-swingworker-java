package task;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

public abstract class TaskWorker<V> extends SwingWorker<List<V>, V> {

  protected Task<V> task;
  
  public TaskWorker(Task<V> task) { 
    this.task = task;
  }

  @Override
  @SuppressWarnings("unchecked")
  protected List<V> doInBackground() {
    List<V> result = new LinkedList<V>();
    while (task.hasNext() && ! isCancelled()) {
      V value = task.next();
      result.add(value);
      publish(value);
      setProgress(100 * result.size() / task.size());
    }
    return result;
  }

  // this forwarding method is a workaround for this problem 
  // http://weblogs.java.net/blog/forax/archive/2006/07/swingworkers_pr.html
  // (required for Java SE 6 Release 1 DP6 on OS X) 
  // the override is commented out to avoid errors with newer versions
  // @Override
  protected final void process(V... chunks) {
    process(Arrays.asList(chunks));
  }
  
  // @Override
  protected abstract void process(List<V> chunks);
}
