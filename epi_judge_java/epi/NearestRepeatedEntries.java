package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  //n is the number of element in an array, d is the number of distinct entries in the array
  //Brute-force solution, O(n^2) time and O(1) space
  // @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
  // public static int findNearestRepetition(List<String> paragraph) {
  //   int minDistance = Integer.MAX_VALUE;
  //   for (int i = 0; i < paragraph.size(); i++) {
  //     String s = paragraph.get(i);
  //     int j = i + 1;
  //     for (; j < paragraph.size(); j++) {
  //       if (s.equals(paragraph.get(j))) {
  //         minDistance = Math.min(minDistance, j - i);
  //         break;
  //       }
  //     }
  //   }
  //   return minDistance = (minDistance != Integer.MAX_VALUE ? minDistance : -1);
  // }
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
  public static int findNearestRepetition(List<String> paragraph) {
    Map<String, Integer> map = new HashMap<String, Integer>();
    int minDistance = Integer.MAX_VALUE;
    for (int i = 0; i < paragraph.size(); i++){
      String curStr = paragraph.get(i);
      if (map.containsKey(curStr))
        minDistance = Math.min(minDistance, i - map.get(curStr));
      map.put(curStr, i);
    }
    return minDistance = (minDistance != Integer.MAX_VALUE ? minDistance : -1);
}
  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "NearestRepeatedEntries.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
