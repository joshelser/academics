package joshelser.hashtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LinkedListHashTable<K,V> implements HashTable<K,V> {
  private static final int INIT_CAPACITY = 64;
  private static final int BUCKET_CAPACITY = 8;
  
  private final int bucketCapacity;
  private final int capacity;
  
  private int[] load;
  private Entry<K,V>[][] entries;
  
  public LinkedListHashTable() {
    this(INIT_CAPACITY, BUCKET_CAPACITY);
  }
  
  public LinkedListHashTable(int initialCapacity, int bucketCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
    }
    
    this.entries = new Entry[initialCapacity][]; 
    
    this.load = new int[initialCapacity];
    for (int i = 0; i < this.load.length; i++) {
      this.load[i] = 0;
    }
    
    this.capacity = initialCapacity;
    this.bucketCapacity = bucketCapacity;
  }
  
  public boolean put(K key, V value) {
    int index = key.hashCode() % this.capacity; 
    
    if (null == this.entries[index]) {
      this.entries[index] = new Entry[BUCKET_CAPACITY];
    }
    
    if (null != get(key)) {
      return false;
    }
    
    if (this.load[index] >= this.entries[index].length) {
      Entry[] newEntries = new Entry[this.entries[index].length * 2];
      System.arraycopy(this.entries[index], 0, newEntries, 0, this.load[index]);
      this.entries[index] = newEntries;
    }
    
    this.entries[index][this.load[index]] = new Entry(key, value);
    this.load[index]++;
    
    return true;
  }
  
  public V get(K key) {
    int index = key.hashCode() % this.capacity;
    
    if (null == this.entries[index]) {
      return null;
    }
    
    for (int i = 0; i < this.load[index]; i++) {
      if (this.entries[index][i].getKey().equals(key)) {
        return this.entries[index][i].getValue();
      }
    }
    
    return null;
  }
  
  public V remove(K key) {
    V value = get(key);
    
    if (null != value) {
      int index = key.hashCode() % this.capacity;
      
      if (1 < this.load[index]) {
        int indexToRemove = 0;
        
        // Get the index of the entry to remove
        for (int i = 0; i < this.load[index]; i++) {
          if (this.entries[index][i].getKey().equals(key)) {
            indexToRemove = i;
          }
        }
        
        // Shift entries down
        for (int i = indexToRemove; i < this.load[index] - 1; i++) {
          this.entries[index][i] = this.entries[index][i+1];
        }
      }
      
      // Trim the size by one
      this.load[index] -= 1;
    }
    
    return value;
  }
  
  public int size() {
    int size = 0;
    for (int i = 0; i < this.load.length; i++) {
      size += this.load[i];
    }
    
    return size;
  }
  
  public Set<K> keySet() {
    HashSet<K> keySet = new HashSet<K>();
    
    for (int i = 0; i < this.entries.length; i++) {
      for (int j = 0; j < this.load[i]; j++) {
        keySet.add(this.entries[i][j].getKey());
      }
    }
    
    return keySet;
  }
  
  public Collection<V> values() {
    ArrayList<V> values = new ArrayList<V>(size());
    
    for (int i = 0; i < this.entries.length; i++) {
      for (int j = 0; j < this.load[i]; j++) {
        values.add(this.entries[i][j].getValue());
      }
    }
    
    return values;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    
    for (int i = 0; i < this.entries.length; i++) {
      
      for (int j = 0; j < this.load[i]; j++) {
        sb.append(this.entries[i][j]).append(", ");
      }
    
      if (this.load[i] > 0) {
        sb.setLength(sb.length() - 2);
      }
    }
    
    sb.append("]");
    
    return sb.toString();
  }
  
  public String objdump() {
    StringBuilder sb = new StringBuilder("[");
    
    for (int i = 0; i < this.entries.length; i++) {
      sb.append("[");
      
      for (int j = 0; j < this.load[i]; j++) {
        sb.append(this.entries[i][j]).append(", ");
      }
    
      if (this.load[i] > 0) {
        sb.setLength(sb.length() - 2);
      }
      
      sb.append("], ");
    }
    
    if (this.entries.length > 0) {
      sb.setLength(sb.length() - 2);
    }
    
    sb.append("]");
    
    return sb.toString();
  }
  
  static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    
    /**
     * Creates new entry.
     */
    Entry(K k, V v) {
      value = v;
      key = k;
    }
    
    public final K getKey() {
      return key;
    }
    
    public final V getValue() {
      return value;
    }
    
    public final V setValue(V newValue) {
      V oldValue = value;
      value = newValue;
      return oldValue;
    }
    
    public final boolean equals(Object o) {
      if (!(o instanceof Map.Entry))
        return false;
      Map.Entry e = (Map.Entry)o;
      Object k1 = getKey();
      Object k2 = e.getKey();
      if (k1 == k2 || (k1 != null && k1.equals(k2))) {
        Object v1 = getValue();
        Object v2 = e.getValue();
        if (v1 == v2 || (v1 != null && v1.equals(v2)))
          return true;
      }
      return false;
    }
    
    public final int hashCode() {
      return (key==null   ? 0 : key.hashCode()) ^
          (value==null ? 0 : value.hashCode());
    }
    
    @Override
    public final String toString() {
      return getKey() + "=" + getValue();
    }
  }
}
