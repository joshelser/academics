package joshelser.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorting {
  private static final Random rand = new Random(89357891735981l);
  
  public static <T extends Comparable<T>> List<T> selectionSort(List<T> list) {
    if (list.size() < 2) {
      return list;
    }
    
    List<T> data = new ArrayList<T>(list);
    
    for (int i = 0; i < data.size(); i++) {
      T min = null;
      int minIndex = -1;
      for (int j = i; j < data.size(); j++) {
        if (min == null || data.get(j).compareTo(min) < 0) {
          min = data.get(j);
          minIndex = j;
        }
      }
      
      if (minIndex > i) {
        T tmp = data.get(i);
        data.set(i, data.get(minIndex));
        data.set(minIndex, tmp);
      }
      
    }
    
    return data;
  }
  
  public static <T extends Comparable<T>> List<T> mergeSort(List<T> data) {
    // Already sorted
    if (data.size() < 2) {
      return data;
    }
    
    List<T> left = new ArrayList<T>(data.subList(0, (data.size() / 2)));
    List<T> right = new ArrayList<T>(data.subList((data.size() / 2), data.size()));
    
    List<T> sortedLeft = mergeSort(left);
    List<T> sortedRight = mergeSort(right);
    
    return merge(sortedLeft, sortedRight);
  }

  private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
    List<T> ret = new ArrayList<T>(left.size() + right.size());
    
    int lIndex = 0, rIndex = 0;
    for (; lIndex < left.size() && rIndex < right.size();) {
      int cmp = left.get(lIndex).compareTo(right.get(rIndex));
      if (cmp < 0) { 
        ret.add(left.get(lIndex));
        lIndex++;
      } else {
        ret.add(right.get(rIndex));
        rIndex++;
      }
    }
    
    while (lIndex < left.size()) {
      ret.add(left.get(lIndex));
      lIndex++;
    }
    
    while (rIndex < right.size()) {
      ret.add(right.get(rIndex));
      rIndex++;
    }
    
    return ret;
  }
  
  public static <T extends Comparable<T>> List<T> quickSort(List<T> list) {
    return quickSort(list, (list.size()/ 2));
  }

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

  public static <T extends Comparable<T>> List<T> quickSortInPlace(List<T> list) {
    if (list.size() < 2) {
      return list;
    }
    
    quickSortInPlace(list, 0, list.size() - 1);
    
    return list;
  }

  public static <T extends Comparable<T>> List<T> quickSortInPlaceRandPartition(List<T> list) {
    if (list.size() < 2) {
      return list;
    }
    
    quickSortInPlace(list, 0, list.size() - 1);
    
    return list;
  }
  
  private static <T extends Comparable<T>> void quickSortInPlaceRandPartition(List<T> data, int begin, int end) {
    if (end <= begin) {
      return;
    }
    
    // Account for non-inclusivity of the end range for Random#nextInt
    int pivot = rand.nextInt((end + 1) - begin) + begin;
    
    int newPivot = partition(data, begin, end, pivot);
    
    quickSortInPlaceRandPartition(data, begin, newPivot - 1);
    
    quickSortInPlaceRandPartition(data, newPivot + 1, end);

    return;
  }
  
  private static <T extends Comparable<T>> void quickSortInPlace(List<T> data, int begin, int end) {
    if (end <= begin) {
      return;
    }
    
    int pivot = (end + begin) / 2;
    
    int newPivot = partition(data, begin, end, pivot);
    
    quickSortInPlace(data, begin, newPivot - 1);
    
    quickSortInPlace(data, newPivot + 1, end);

    return;
  }
  
  private static <T extends Comparable<T>> int partition(List<T> data, int begin, int end, int pivot) {
    // Move the pivot value to the end
    T pivotVal = data.get(pivot);
    data.set(pivot, data.get(end));
    data.set(end, pivotVal);
    
    int newPivot = begin;
    for (int i = begin; i < end; i++) {
      if (data.get(i).compareTo(pivotVal) < 0) {
        // swap
        T tmp = data.get(newPivot);
        data.set(newPivot, data.get(i));
        data.set(i, tmp);
        
        newPivot++;
      }
    }
    
    // Swap back the pivot
    data.set(end, data.get(newPivot));
    data.set(newPivot, pivotVal);
    
    return newPivot;
  }
}