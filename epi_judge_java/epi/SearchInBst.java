package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchInBst {
  
  //h is the tree height, n is the number of nodes
  //Optimal solution, O(h) and O(logn) time, O(1) space
  public static BstNode<Integer> searchBST(BstNode<Integer> tree, int key) {
    while (tree != null && tree.data != key){
      tree = key < tree.data ? tree.left : tree.right;
    }
    return tree;
  }
  @EpiTest(testDataFile = "search_in_bst.tsv")
  public static int searchBSTWrapper(BstNode<Integer> tree, int key) {
    BstNode<Integer> result = searchBST(tree, key);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
