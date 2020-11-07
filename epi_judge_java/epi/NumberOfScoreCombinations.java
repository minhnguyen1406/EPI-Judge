package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class NumberOfScoreCombinations {
  //n is the number of play scores, s is the final score
  //Optimal solution, dynammic programming (bottom up), O(sn) time and O(s) space
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
    int[] array = new int[finalScore + 1];
    array[0] = 1;

    for (Integer score : individualPlayScores){
      for (int i = score; i < finalScore + 1; i++){
        array[i] += array[i - score];
      }
    }
    return array[finalScore];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
