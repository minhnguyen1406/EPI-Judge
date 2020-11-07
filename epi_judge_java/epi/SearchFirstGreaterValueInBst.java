package epi;

import java.util.ArrayDeque;
import java.util.Deque;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchFirstGreaterValueInBst {
  //h is the height of the tree
  // Optimal solution, O(h) time and O(1) space
  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree, Integer k) {
    BstNode<Integer> result = null;
    while (tree != null){
      if (tree.data > k){
        result = tree;
        tree = tree.left;
      }
      else tree = tree.right;
    }
    return result;
  }
  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
