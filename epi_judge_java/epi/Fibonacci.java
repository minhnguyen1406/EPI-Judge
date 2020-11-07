package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Fibonacci {

  @EpiTest(testDataFile = "fibonacci.tsv")

  public static int fibonacci(int n) {
    if (n <= 1)
      return n;
    int fnMinus2 = 0;
    int fnMinus1 = 1;
    for (int i = 2; i <= n; i++){
      int f = fnMinus2 + fnMinus1;
      fnMinus2 = fnMinus1;
      fnMinus1 = f;
    }
    return fnMinus1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Fibonacci.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
