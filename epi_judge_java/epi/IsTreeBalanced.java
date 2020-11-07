package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  // n is the number of nodes, h is the height of the tree
  // Brute-force algorithm O(n) time and O(n) space

  // public static boolean isBalanced(BinaryTreeNode<Integer> root) {
  //   if (root == null)
  //     return true;
  //   int leftDepth = getHeight(root.left);
  //   int rightDepth = getHeight(root.right);

  //   if (Math.abs(leftDepth - rightDepth) > 1)
  //     return false;
  //   return isBalanced(root.left) && isBalanced(root.right);
  // }

  // public static int getHeight(BinaryTreeNode<Integer> root) {
  //   if (root == null)
  //     return 0;
  //   return 1 + Math.max(getHeight(root.left), getHeight(root.right));
  // }

  // Optimal algorithm O(n) time and O(h) space
  public static boolean isBalanced(BinaryTreeNode<Integer> root) {
    return getHeight(root) != -1;
  }

  public static int getHeight(BinaryTreeNode<Integer> root) {
    if (root == null)
      return 0;

    int left = getHeight(root.left);
    int right = getHeight(root.right);

    if (Math.abs(left - right) > 1 || left == -1 || right == -1)
      return -1;
    return Math.max(left, right) + 1;
  } 

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "IsTreeBalanced.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
