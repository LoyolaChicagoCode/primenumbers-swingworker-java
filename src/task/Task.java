package task;

public interface Task<V> {
  V next();
  boolean hasNext();
  int size();
}
