package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")
  //k is the input
  //Optimal solution using binary search, O(logk) time and O(1) space
  public static int squareRoot(int k) {
    long start = 0, end = k;
    while (start <= end){
      long mid = start + (end - start)/2;
      long midSquare = mid * mid;
      if (midSquare <= k)
        start = mid + 1;
      else  
        end = mid - 1;
    }
    return (int) start -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
