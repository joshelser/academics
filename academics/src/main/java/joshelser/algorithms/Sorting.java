package joshelser.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Sorting {
  public static <T extends Comparable<T>> List<T> selectionSort(List<T> list) {
    if (list.size() < 2) {
      return list;
    }
    @SuppressWarnings("unchecked")
    T[] orig = (T[]) list.toArray();
    T[] data = (T[]) new Comparable[list.size()];
    
    System.arraycopy(orig, 0, data, 0, list.size());
    
    for (int i = 0; i < data.length; i++) {
      T min = null;
      int minIndex = -1;
      for (int j = i; j < data.length; j++) {
        if (min == null || data[j].compareTo(min) < 0) {
          min = data[j];
          minIndex = j;
        }
      }
      
      if (minIndex > i) {
        T tmp = data[i];
        data[i] = data[minIndex];
        data[minIndex] = tmp;
      }
      
    }
    
    ArrayList<T> ret = new ArrayList<T>(data.length);
    for (int i = 0; i < data.length; i++) {
      ret.add(data[i]);
    }
    
    return ret;
  }
  
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
    if (list.size() < 2) {
      return list;
    }
    
    T[] copy = (T[]) list.toArray();
    T[] data = (T[]) new Comparable[list.size()];
    
    System.arraycopy(copy, 0, data, 0, list.size());
    
    T[] sorted = mergeSort(data);
    
    List<T> ret = new ArrayList<T>(sorted.length);
    for (int i = 0; i < sorted.length; i++) {
      ret.add(sorted[i]);
    }
    
    return ret;
  }
  
  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> T[] mergeSort(T[] data) {
    // Already sorted
    if (data.length < 2) {
      return data;
    }
    
    T[] left = (T[]) new Comparable[data.length / 2];
    T[] right = (T[]) new Comparable[data.length - left.length];
    
    System.arraycopy(data, 0, left, 0, left.length);
    System.arraycopy(data, left.length, right, 0, right.length);
    
    T[] sortedLeft = mergeSort(left);
    T[] sortedRight = mergeSort(right);
    
    return merge(sortedLeft, sortedRight);
  }
  
  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
    T[] ret = (T[]) new Comparable[left.length + right.length];
    
    int retIndex = 0, lIndex = 0, rIndex = 0;
    for (; lIndex < left.length && rIndex < right.length; retIndex++) {
      int cmp = left[lIndex].compareTo(right[rIndex]);
      if (cmp < 0) { 
        ret[retIndex] = left[lIndex];
        lIndex++;
      } else {
        ret[retIndex] = right[rIndex];
        rIndex++;
      }
    }
    
    while (lIndex < left.length) {
      ret[retIndex] = left[lIndex];
      retIndex++;
      lIndex++;
    }
    
    while (rIndex < right.length) {
      ret[retIndex] = right[rIndex];
      retIndex++;
      rIndex++;
    }
    
    return ret;
  }
  
  //TODO Change this to be constant in memory consumption
  public static <T extends Comparable<T>> List<T> quickSort(List<T> list) {
    return quickSort(list, (list.size()/ 2));
  }

  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> List<T> quickSort(List<T> data, int pivot) {
    if (data.size() < 2) {
      return data;
    }
    
    T pivotVal = data.get(pivot);
    List<T> less = new ArrayList<T>(), more = new ArrayList<T>();
    
    more.add(pivotVal);
    
    for (int i = 0; i < pivot; i++) {
      if (data.get(i).compareTo(pivotVal) < 0) {
        less.add(data.get(i));
      } else {
        more.add(data.get(i));
      }
    }
    
    for (int i = pivot + 1; i < data.size(); i++) {
      if (data.get(i).compareTo(pivotVal) < 0) {
        less.add(data.get(i));
      } else {
        more.add(data.get(i));
      }
    }
    
    
    return concat(quickSort(less, less.size() / 2), quickSort(more, more.size() / 2));
  }
  
  private static <T extends Comparable<T>> List<T> concat(List<T> low, List<T> high) {
    List<T> ret = new ArrayList<T>(low.size() + high.size());
    
    ret.addAll(low);
    ret.addAll(high);
    
    return ret;
  }
}











