package epi;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorWithParent {
  // h is the height of the binary tree
  // Brute force solution, using HashMap O(h) time and O(h) space
  // public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
  // BinaryTree<Integer> node1) {
  // Map<BinaryTree<Integer> , Boolean> ancestors = new
  // HashMap<BinaryTree<Integer>, Boolean>();
  // while (node0 != null){
  // ancestors.put(node0, true);
  // node0 = node0.parent;
  // }
  // while (node1 != null){
  // if (ancestors.containsKey(node1)){
  // return node1;
  // }
  // node1 = node1.parent;
  // }
  // return null;
  // }

  // Optimal solution O(h) time, O(1) space
  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {

    int depth0 = getDepth(node0), depth1 = getDepth(node1);
    // Makes node0 as the deeper node in order to simplify the code.
    if (depth1 > depth0) {
      BinaryTree<Integer> temp = node0;
      node0 = node1;
      node1 = temp;
    }
    // Ascends from the deeper node.
    int depthDiff = Math.abs(depth0 - depth1);
    while (depthDiff-- > 0) {
      node0 = node0.parent;
    }

    // Now ascends both nodes until we reach the LCA.
    while (node0 != node1) {
      node0 = node0.parent;
      node1 = node1.parent;
    }
    return node0;
  }

  public static int getDepth(BinaryTree<Integer> node) {
    int depth = 0;
    while (node != null) {
      node = node.parent;
      depth++;
    }
    return depth;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree, Integer key0, Integer key1)
      throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "LowestCommonAncestorWithParent.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
