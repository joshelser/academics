package joshelser.hashtable;

import java.util.Set;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

public class HashTableTest {
  public HashTableTest() {}
  
  @Test
  public void LinkedListHashTableTest() {
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
  public void LinearHashTableTest() {
    HashTable<String,String> hash = new LinearProbingHashTable<String,String>(4);
    
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
  public void resizeLinarBucket() {
    HashTable<String,String> hash = new LinearProbingHashTable<String,String>(1);
    
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
  public void testLLVariedSizes() {
    System.out.println("Capacity: " + 64 + ", bucketCapacity: " + 8);
    testHash(new LinkedListHashTable<Integer,UUID>(64, 8));
    System.out.println("Capacity: " + 128 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(128, 8));
    System.out.println("Capacity: " + 256 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(256, 8));
    System.out.println("Capacity: " + 512 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(512, 8));
    System.out.println("Capacity: " + 1024 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(1024, 8));

    System.out.println("Capacity: " + 64 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(64, 64));
    System.out.println("Capacity: " + 128 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(128, 64));
    System.out.println("Capacity: " + 256 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(256, 64));
    System.out.println("Capacity: " + 512 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(512, 64));
    System.out.println("Capacity: " + 1024 + ", bucketCapacity: " + 64);
    testHash(new LinkedListHashTable<Integer,UUID>(1024, 64));
  }

  
  @Test
  public void testLinearVariedSizes() {
    System.out.println("LinearProbingHashTable - Capacity: " + 64);
    testHash(new LinearProbingHashTable<Integer,UUID>(64));
    System.out.println("LinearProbingHashTable - Capacity: " + 128);
    testHash(new LinearProbingHashTable<Integer,UUID>(128));
    System.out.println("LinearProbingHashTable - Capacity: " + 256);
    testHash(new LinearProbingHashTable<Integer,UUID>(256));
    System.out.println("LinearProbingHashTable - Capacity: " + 512);
    testHash(new LinearProbingHashTable<Integer,UUID>(512));
    System.out.println("LinearProbingHashTable - Capacity: " + 1024);
    testHash(new LinearProbingHashTable<Integer,UUID>(1024));
  }
  
  public void testHash(HashTable<Integer,UUID> hash) {
    Long now = System.currentTimeMillis();
    loadHash(hash);
    removeHash(hash);
    Long then = System.currentTimeMillis();
    
    //System.out.println("Entries: " + hash.size());
    Assert.assertEquals(0, hash.size());
    
    System.out.println("Duration: " + (then - now) / 1000.0);
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
