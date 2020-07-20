package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//n is the number of node in the tree
//Optimal algorithm O(n) time and O(n) space
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (root == null)
      return result;

    Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
    queue.add(root);
    int level = 0;
    while (!queue.isEmpty()) {
      result.add(new ArrayList<Integer>());
      int level_length = queue.size();
      for (int i = 0; i < level_length; ++i) {
        BinaryTreeNode<Integer> node = queue.remove();
        result.get(level).add(node.data);

        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }
      level++;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "TreeLevelOrder.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
