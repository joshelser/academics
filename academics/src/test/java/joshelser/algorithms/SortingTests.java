package joshelser.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SortingTests {
  List<String> list1, emptyList, singleList, doubleList;
  List<Integer> bigList;
  
  @Before
  public void setup() {
    list1 = Arrays.asList("k", "b", "a", "q", "3", "c", "i", "s", "o", "p", "t", "r", "e");
    emptyList = new ArrayList<String>(0);
    singleList = Arrays.asList("a");
    doubleList = Arrays.asList("b", "a");
    
    makeList();
  }
  
  public void makeList() {
    bigList = new ArrayList<Integer>((int) Math.pow(2, 14));
    Random r = new Random(8359183759l);
    for (int i = 0; i < Math.pow(2, 22); i++) {
      bigList.add(r.nextInt((int) Math.pow(2, 22)));
    }
  }
  
  @Test
  public void selectionsort() {
    checkList(Sorting.selectionSort(list1));
    checkList(Sorting.selectionSort(emptyList));
    checkList(Sorting.selectionSort(singleList));
    checkList(Sorting.selectionSort(doubleList));
  }
  
  @Test
  public void mergesort() {
    checkList(Sorting.mergeSort(list1));
    checkList(Sorting.mergeSort(emptyList));
    checkList(Sorting.mergeSort(singleList));
    checkList(Sorting.mergeSort(doubleList));
  }
  
  @Test
  public void quicksort() {
    checkList(Sorting.quickSort(list1));
    checkList(Sorting.quickSort(emptyList));
    checkList(Sorting.quickSort(singleList));
    checkList(Sorting.quickSort(doubleList));
  }
  
  @Test
  public void quicksortinplace() {
    checkList(Sorting.quickSortInPlace(list1));
    checkList(Sorting.quickSortInPlace(emptyList));
    checkList(Sorting.quickSortInPlace(singleList));
    checkList(Sorting.quickSortInPlace(doubleList));
  }
  
  @Test
  public void benchmark() {
    List<Integer> testList = new ArrayList<Integer>(bigList);
    
    System.gc();
    Long now = System.currentTimeMillis();
    checkList(Sorting.mergeSort(testList));
    System.out.println("Mergesort: " + (System.currentTimeMillis() - now) / (double) 1000);
    
    /*testList = new ArrayList<Integer>(bigList);
    now = System.currentTimeMillis();
    checkList(Sorting.quickSort(testList));
    System.out.println("Quicksort: " + (System.currentTimeMillis() - now) / (double) 1000);
    */
    
    testList = new ArrayList<Integer>(bigList);

    System.gc();
    now = System.currentTimeMillis();
    checkList(Sorting.quickSortInPlace(testList));
    System.out.println("Quicksort inplace: " + (System.currentTimeMillis() - now) / (double) 1000);
  }
  
  @Test
  public void benchmarkQuick() {
    List<Integer> testList = new ArrayList<Integer>(bigList);
    int totalInPlace = 0, totalRandPartition = 0;
    int numTests = 10;
    
    for (int i = 0; i < numTests; i++) {
      System.gc();
      Long now = System.currentTimeMillis();
      checkList(Sorting.quickSortInPlace(testList));
      Long then = System.currentTimeMillis();
      totalInPlace += (then - now);
      System.out.println("Quicksort inplace: " + (then - now) / (double) 1000);
      
      
      testList = new ArrayList<Integer>(bigList);
      
      System.gc();
      now = System.currentTimeMillis();
      checkList(Sorting.quickSortInPlaceRandPartition(testList));
      then = System.currentTimeMillis();
      totalRandPartition += (then - now);
      System.out.println("Quicksort inplace rand partition: " + (then - now) / (double) 1000);
      
      makeList();
      testList = new ArrayList<Integer>(bigList);
      System.out.println();
    }

    System.out.println("\nQuicksort inplace total: " + (totalInPlace) / (double) 1000 / (double) numTests);
    System.out.println("Quicksort inplace rand partition total: " + (totalRandPartition) / (double) 1000 / (double) numTests);
    
  }
  
  private <T extends Comparable<T>> void checkList(List<T> sorted) {
    //System.out.println(sorted);
    
    Iterator<T> iter = sorted.iterator();
    
    if (iter.hasNext()) {
      T prev = iter.next();
      
      while (iter.hasNext()) {
        T cur = iter.next();
        Assert.assertTrue(prev + " is not less than or equal to " + cur, prev.compareTo(cur) <= 0);
        prev = cur;
      }
    }
  }
}
