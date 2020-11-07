package epi;

import java.util.ArrayDeque;
import java.util.Deque;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  //n is the number of node in the binary tree
  //Optimal solution, O(n) time and O(n) space
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
    BinaryTreeNode<Integer> pre = null;
    while (tree != null || !stack.isEmpty()){
      while(tree != null){
        stack.push(tree);
        tree = tree.left;
      }
      tree = stack.pop();
      if (pre != null && tree.data < pre.data)
        return false;
      pre = tree;
      tree = tree.right;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
