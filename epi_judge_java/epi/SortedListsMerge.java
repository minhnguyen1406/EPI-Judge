package epi;


import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  // @include
  //n and m are the lengths of each two inputs lists.
  /*Brute-force algorithm O((n+m)^2) time: combine two lists into one result list and sort the result list 
    using insertion sort*/
  /*public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
    if (L1 == null) {
      return L2;
    }
    if (L2 == null) {
      return L1;
    }
    ListNode<Integer> temp = L1;
    while (temp.next != null) {
      temp = temp.next;
    }
    temp.next = L2;
    return insertionSort(L1);
  }

  public static ListNode<Integer> insertionSort(ListNode<Integer> L) {
    ListNode<Integer> dummyHead = new ListNode<>(0, L);
    ListNode<Integer> iter = L;

    while (iter != null && iter.next != null) {
        if (iter.data > iter.next.data) {
            ListNode<Integer> target = iter.next, pre = dummyHead;
            while (pre.next.data < target.data) {
                pre = pre.next;
            }
            ListNode<Integer> temp = pre.next;
            pre.next = target;
            iter.next = target.next;
            target.next = temp;
        } else {
            iter = iter.next;
        }
    }
    return dummyHead.next;
}*/

  /*Optimal algorithm O(n+m) time: traverse two lists, always choose the node with smaller key and continue traversing from*/
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
    ListNode<Integer> dummyHead = new ListNode<Integer>(0, null);
    ListNode<Integer> cur = dummyHead;
    while (L1 != null && L2 != null){
      if (L1.data < L2.data){
        cur.next = L1;
        L1 = L1.next;
      }
      else{
        cur.next = L2;
        L2 = L2.next;
      }
      cur = cur.next;
    }
    cur.next = L1 != null ? L1 : L2;
    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "SortedListsMerge.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
