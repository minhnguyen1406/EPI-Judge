package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")
  // n is the number of elements
  //Optimal solution, O(logn) time and O(1) space
  public static int searchFirstOfK(List<Integer> A, int k) {
      int left = 0, right = A.size() - 1, result = -1;
      while (left <= right){
        int mid = left + (right - left) / 2;
        if (A.get(mid) < k )
          left = mid + 1;
        else if (A.get(mid) == k){
          result = mid;
          right = mid - 1;
        }
        else{
          right = mid - 1;
        }
      }
      return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
