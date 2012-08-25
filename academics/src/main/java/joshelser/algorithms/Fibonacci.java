package joshelser.algorithms;

public class Fibonacci {
  public static int fibonacci(int i) {
    if (i == 0) {
      return 0;
    } if (i == 1) {
      return 1;
    }
    
    return fibonacci(i-2) + fibonacci(i-1);
  }
  
  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(fibonacci(i));
    }
  }
}
