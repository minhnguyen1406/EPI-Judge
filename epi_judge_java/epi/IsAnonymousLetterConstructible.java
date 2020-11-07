package epi;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsAnonymousLetterConstructible {
  // m is the length of the string magazine, n is the length of string ransom
  // note, k is the number of unique characters across both the ransom note and
  // magazine
  // Brute-force solution, O(mn) time and O(m) space
  // @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
  // public static boolean isLetterConstructibleFromMagazine(String letterText,
  // String magazineText) {
  // for (char c : letterText.toCharArray()){
  // int index = magazineText.indexOf(c);
  // if (index == -1)
  // return false;
  // magazineText = magazineText.substring(0, index) +
  // magazineText.substring(index + 1);
  // }
  // return true;
  // }
  private static Map<Character, Integer> makeCountsMap(String s) {
    Map<Character, Integer> counts = new HashMap<>();
    for (char c : s.toCharArray()) {
      int currentCount = counts.getOrDefault(c, 0);
      counts.put(c, currentCount + 1);
    }
    return counts;
  }

  //Optimal solution (2 hash maps), O(m) time and O(k) space
  // @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
  // public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
  //   if (letterText.length() > magazineText.length()) {
  //     return false;
  //   }
  //   Map<Character, Integer> letterTextCounts = makeCountsMap(letterText);
  //   Map<Character, Integer> magazineTextCounts = makeCountsMap(magazineText);
  //   for (char c : letterTextCounts.keySet()) {
  //     int countInMagazineText = magazineTextCounts.getOrDefault(c, 0);
  //     int countInLetterText = letterTextCounts.get(c);
  //     if (countInMagazineText < countInLetterText) {
  //       return false;
  //     }
  //   }
  //   return true;
  // }


  //Optimal solution (1 hash map), O(m) time and O(k) space
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
  public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
    if (letterText.length() > magazineText.length()) {
      return false;
    }
    Map<Character, Integer> magazineTextCounts = makeCountsMap(magazineText);
    for (char c : letterText.toCharArray()) {
      int countInMagazineText = magazineTextCounts.getOrDefault(c,0);
      if (countInMagazineText == 0)
        return false;
      magazineTextCounts.put(c, countInMagazineText - 1);
    }
    return true;
  }
  
  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "IsAnonymousLetterConstructible.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
