package joshelser.hashtable;


public class QuadraticProbingHashTable<K,V> extends ProbingHashTable<K,V> {
  public QuadraticProbingHashTable() {
    super();
  }
  
  public QuadraticProbingHashTable(int capacity) {
    super(capacity);
  }
  
  public int probe(K key) {
    int index = key.hashCode() % this.getEntries().length; 
    int step = 1;
    
    while (null != this.getEntries()[index] && !this.getEntries()[index].getKey().equals(key)) {
      index = (index + (step * step)) % this.getEntries().length;
      step++;
    }
    
    return index;
  }
  
  public int probe(K key, V value) {
    int index = key.hashCode() % this.getEntries().length; 
    int step = 1;
    
    while (null != this.getEntries()[index] && !this.getEntries()[index].getValue().equals(value)) {
      index = (index + (step * step)) % this.getEntries().length;
      step++;
    }
    
    return index;
  }
}