package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    if (A == null || B == null) return -1;
    int n = A.length();
    int m = B.length();
    if (n == 0) return m;
    if (m == 0) return n;

    int[][] array = new int[n + 1][m + 1];
    for (int i = 0; i < n + 1; i++){
      array[i][0] = i;
    }

    for (int i = 0; i < m + 1; i++){
      array[0][i] = i;
    }

    for (int i = 1; i < n + 1; i++){
      for (int j = 1; j < m + 1; j++){
        int top = array[i-1][j];
        int left = array[i][j-1];
        int topLeft = array[i-1][j-1];
        if (A.charAt(i - 1) != B.charAt(j -1)){
          array[i][j] = 1 + Math.min(top, Math.min(left, topLeft));
        }
        else{
          array[i][j] = array[i-1][j-1];
        }
      }
    }
    return array[n][m];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
