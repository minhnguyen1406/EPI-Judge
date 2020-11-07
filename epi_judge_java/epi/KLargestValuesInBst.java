package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

public class KLargestValuesInBst {
  //h is the height of the tree, k is the kth input
  //Optimal solution, O(h+k) time and O(h) space
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    Deque<BstNode<Integer>> stack = new ArrayDeque<>();
    List<Integer> result = new ArrayList<>();
    int count = 0;
    while ((tree != null || !stack.isEmpty()) && k != count) {
      while (tree != null) {
        stack.push(tree);
        tree = tree.right;
      }
      tree = stack.pop();
      result.add(tree.data);
      count++;
      tree = tree.left;
    }
    return result;
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "KLargestValuesInBst.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
