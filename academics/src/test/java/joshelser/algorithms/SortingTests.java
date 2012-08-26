package joshelser.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SortingTests {
  List<String> list1, emptyList, singleList, doubleList;
  
  @Before
  public void setup() {
    list1 = Arrays.asList("k", "b", "a", "q", "3", "c", "s", "i", "o", "p", "t", "r", "e");
    emptyList = new ArrayList<String>(0);
    singleList = Arrays.asList("a");
    doubleList = Arrays.asList("b", "a");
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
  
  private <T extends Comparable<T>> void checkList(List<T> sorted) {
    System.out.println(sorted);
    
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
