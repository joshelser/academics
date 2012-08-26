package joshelser.algorithms;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {
  
  public static <T extends Comparable<T>> int binarySearch(List<T> list, T element) {
    @SuppressWarnings("unchecked")
    T[] arr = (T[]) list.toArray();

    return binarySearchImpl(arr, element, 0, arr.length - 1);
  }
  
  private static <T extends Comparable<T>> int binarySearchImpl(T[] arr, T element, int start, int end) {
    if (end == start && arr[end].equals(element)) {
      return end;
    } else if (end <= start) {
      return -1;
    }
    
    int mid = (end + start) / 2;
    
    int cmp = element.compareTo(arr[mid]);
    
    if (cmp < 0) {
      return binarySearchImpl(arr, element, 0, mid - 1);
    } else if (cmp > 0) {
      return binarySearchImpl(arr, element, mid + 1, end);
    } else {
      return mid;
    }
  }
  
  public static void main(String[] args) {
    List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
    
    System.out.println(binarySearch(list, "1"));
    System.out.println(binarySearch(list, "a"));
    System.out.println(binarySearch(list, "b"));
    System.out.println(binarySearch(list, "c"));
    System.out.println(binarySearch(list, "d"));
    System.out.println(binarySearch(list, "e"));
    System.out.println(binarySearch(list, "f"));
    System.out.println(binarySearch(list, "g"));
    
    list = Arrays.asList("a", "b", "c", "d", "e");
    
    System.out.println(binarySearch(list, "1"));
    System.out.println(binarySearch(list, "a"));
    System.out.println(binarySearch(list, "b"));
    System.out.println(binarySearch(list, "c"));
    System.out.println(binarySearch(list, "d"));
    System.out.println(binarySearch(list, "e"));
    System.out.println(binarySearch(list, "f"));
    System.out.println(binarySearch(list, "g"));
  }
}
