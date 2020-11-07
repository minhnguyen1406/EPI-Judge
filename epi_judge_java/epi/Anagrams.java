package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Anagrams {
  //n is the number of elments in the dictionary, k is the maximum length of a string in the dictionary
  //Optiomal solution with sorting, O(nklogk) time and O(nk) space
  public static List<List<String>> findAnagrams(List<String> dictionary) {
    Map<String, List<String>> hashMap = new HashMap<>();
    for (int i = 0; i < dictionary.size(); i++) {
      char[] chars = dictionary.get(i).toCharArray();
      Arrays.sort(chars);
      String key = String.valueOf(chars);
      hashMap.putIfAbsent(key, new ArrayList<String>());
      hashMap.get(key).add(dictionary.get(i));
    }
    return hashMap.values().stream().filter(group -> group.size() >= 2).collect(Collectors.toList());
  }

  @EpiTestComparator
  public static boolean comp(List<List<String>> expected, List<List<String>> result) {
    if (result == null) {
      return false;
    }
    for (List<String> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<String> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "Anagrams.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
