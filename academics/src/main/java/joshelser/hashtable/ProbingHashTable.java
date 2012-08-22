package joshelser.hashtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class ProbingHashTable<K,V> implements HashTable<K,V> {
  private static final int INIT_CAPACITY = 64;
  
  private Entry<K,V>[] entries;
  private int size = 0;
  private float loadFactor = 0.8f;
  
  public abstract int probe(K key);
  public abstract int probe(K key, V value);
    
  public ProbingHashTable() {
    this(INIT_CAPACITY);
  }
  
  public ProbingHashTable(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
    }

    this.entries = new Entry[initialCapacity];
  }

  public boolean put(K key, V value) {
    if ((this.size / this.entries.length) > this.loadFactor) {
      resize();
    }
    
    int index = probe(key, value);
    
    // K,V already exist
    if (null != this.entries[index]) {
      return false;
    }
    
    this.entries[index] = new Entry(key, value);
    
    this.size++;
    
    return true;
  }
  
  public V get(K key) {
    int index = probe(key);
    
    return this.entries[index].getValue();
  }
  
  public V remove(K key) {
    int index = probe(key);
    
    if (null != this.entries[index] && !this.entries[index].getKey().equals(key)) {
      return null;
    }
    
    V value = this.entries[index].getValue();
    this.entries[index] = null;
    
    this.size--;
    
    return value;
  }
  
  public int size() {
    return this.size;
  }
  
  public Set<K> keySet() {
    HashSet<K> keySet = new HashSet<K>();
    
    for (int i = 0; i < this.entries.length; i++) {
      if (null != this.entries[i]) {
        keySet.add(this.entries[i].getKey());
      }
    }
    
    return keySet;
  }
  
  public Collection<V> values() {
    ArrayList<V> values = new ArrayList<V>(size());
    
    for (int i = 0; i < this.entries.length; i++) {
      if (null != this.entries[i]) {
        values.add(this.entries[i].getValue());
      }
    }
    
    return values;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    
    for (int i = 0; i < this.entries.length; i++) {
      sb.append(this.entries[i]).append(", ");
    }
    
    if (this.entries.length > 0) {
      sb.setLength(sb.length() - 2);
    }
    
    sb.append("]");
    
    return sb.toString();
  }
  
  public String objdump() {
    StringBuilder sb = new StringBuilder("[");
    
    for (int i = 0; i < this.entries.length; i++) {
      if (null == this.entries[i]) {
        sb.append("null, ");
      } else {
        sb.append(this.entries[i]).append(", ");
      }
    }
    
    if (this.entries.length > 0) {
      sb.setLength(sb.length() - 2);
    }
    
    sb.append("]");
    
    return sb.toString();
  }
  
  private void resize() {
    Entry<K,V>[] oldEntries = this.entries;
    this.entries = new Entry[this.entries.length * 2];
    this.size = 0;
    
    for (int i = 0; i < oldEntries.length; i++) {
      if (null != oldEntries[i]) {
        put(oldEntries[i].getKey(), oldEntries[i].getValue());
      }
    }
  }
  
  protected Entry<K,V>[] getEntries() {
    return this.entries;
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
