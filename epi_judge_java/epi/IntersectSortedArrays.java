package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectSortedArrays {
  // m is number of elements in A, n is the number of elements in B, m^n is the
  // number of unique similar elements in two arrays
  // Brute-force solution, O(mn) time and O(m^n) space
  // @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
  // public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
  // List<Integer> B) {
  // List<Integer> result = new ArrayList<>();
  // for (int i = 0; i < A.size(); i++){
  // if ((i == 0 || !A.get(i).equals(A.get(i-1))) && B.contains(A.get(i)))
  // result.add(A.get(i));
  // }
  // return result;
  // }

  // Binary Search solution, O(mlogn) time and O(m^n) space
  // @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
  // public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
  // List<Integer> B) {
  // List<Integer> result = new ArrayList<>();
  // for (int i = 0; i < A.size(); i++){
  // if ((i == 0 || !A.get(i).equals(A.get(i-1))) && Collections.binarySearch(B,
  // A.get(i)) >=0)
  // result.add(A.get(i));
  // }
  // return result;
  // }

  // Optimal solution, O(m+n) time and O(m^n) space
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
  public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
    List<Integer> result = new ArrayList<>();
    int i = 0, j = 0;
    while (i < A.size() && j < B.size()) {
      if ((A.get(i).equals(B.get(j))) && (i == 0 || !A.get(i).equals(A.get(i - 1)))) {
        result.add(A.get(i));
        i++;
        j++;
      } else if (A.get(i) < B.get(j))
        i++;
      else
        j++;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "IntersectSortedArrays.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
