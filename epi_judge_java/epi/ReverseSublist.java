package epi;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")
  //s is the start node, f is the finish node (inclusive)
  //Optimal solution O(f-s+1)
  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> dummyHead = new ListNode<Integer>(0, L);
    ListNode<Integer> subListHead = dummyHead;
    int orderCount = 1;
    while (orderCount < start){
      subListHead = subListHead.next;
      orderCount++;
    }
    
    ListNode<Integer> iter = subListHead.next;
    while (start < finish){
      ListNode<Integer> temp = iter.next;
      iter.next = temp.next;
      temp.next = subListHead.next;
      subListHead.next = temp;
      start++;
    }
    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",  
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
