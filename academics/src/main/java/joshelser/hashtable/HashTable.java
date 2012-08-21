package joshelser.hashtable;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

public interface HashTable<K,V> {
    public V get(K key);
    
    public boolean put(K key, V value);

    public V remove(K key);
    
    public int size();

    public Set<K> keySet();

    public Collection<V> values();
    
    public String objdump();
}
