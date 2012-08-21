package joshelser.hashtable;

import java.util.Set;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

public class HashTableTest {
  public HashTableTest() {}
  
  @Test
  public void runHashTableTest() {
    HashTable<String,String> hash = new LinkedListHashTable<String,String>(4, 4);
    
    Assert.assertTrue("hash.put('key', 'value')", hash.put("key", "value"));
    
    Assert.assertFalse("hash.put('key', 'value')", hash.put("key", "value"));
    
    Assert.assertTrue("hash.put('key1', 'value1')", hash.put("key1", "value1"));
    Assert.assertTrue("hash.put('key2', 'value2')", hash.put("key2", "value2"));
    Assert.assertTrue("hash.put('key3', 'value3')", hash.put("key3", "value3"));
    Assert.assertTrue("hash.put('key4', 'value4')", hash.put("key4", "value4"));
    Assert.assertTrue("hash.put('key5', 'value5')", hash.put("key5", "value5"));
    Assert.assertTrue("hash.put('key6', 'value6')", hash.put("key6", "value6"));
    Assert.assertTrue("hash.put('key7', 'value7')", hash.put("key7", "value7"));
    Assert.assertTrue("hash.put('key8', 'value8')", hash.put("key8", "value8"));
    Assert.assertTrue("hash.put('key9', 'value9')", hash.put("key9", "value9"));
    
    hash.remove("key");    
    hash.remove("key4");    
    hash.remove("key8");
  }
  
  @Test
  public void resizeBucket() {
    HashTable<String,String> hash = new LinkedListHashTable<String,String>(1, 8);
    
    hash.put("a", "a");
    hash.put("b", "a");
    hash.put("c", "a");
    hash.put("d", "a");
    hash.put("e", "a");
    hash.put("f", "a");
    hash.put("g", "a");
    hash.put("h", "a");
    hash.put("i", "a");
    hash.put("j", "a");
    
  }
  
  @Test
  public void testVariedSizes() {
    testHash(64, 8);
    testHash(128, 8);
    testHash(256, 8);
    testHash(512, 8);
    testHash(1024, 8);

    testHash(64, 32);
    testHash(128, 32);
    testHash(256, 32);
    testHash(512, 32);
    testHash(1024, 32);

    testHash(64, 64);
    testHash(128, 64);
    testHash(256, 64);
    testHash(512, 64);
    testHash(1024, 64);
  }
  
  public void testHash(int capacity, int bucket) {
    HashTable<Integer,UUID> hash = new LinkedListHashTable<Integer,UUID>(capacity, bucket);
    
    Long now = System.currentTimeMillis();
    loadHash(hash);
    removeHash(hash);
    Long then = System.currentTimeMillis();
    
    //System.out.println("Entries: " + hash.size());
    Assert.assertEquals(0, hash.size());
    
    System.out.println("Capacity: " + capacity + ", bucketCapacity: " + bucket + ", duration: " + (then - now) / 1000.0);
  }
  
  public void loadHash(HashTable<Integer,UUID> hash) {
    for (int i = 0; i < 250000; i++) {
      if (i % 10000 == 0) {
        //System.out.println(hash.size());
      }
      hash.put(i, UUID.randomUUID());
    }
  }
  
  public void removeHash(HashTable<Integer,UUID> hash) {
    while (0 < hash.size()) {
      int count = 0;
      
      Set<Integer> keys = hash.keySet();
      for (Integer key : keys) {
        hash.remove(key);
        
        if (50000 == count) {
          //System.out.println("Removed 50000 entries: " + hash.size() + " entries left");
          break;
        }
        
        count++;
      }
    }
  }
}
