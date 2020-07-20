package epi;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class IsListCyclic {
  //Hash table algorithm - O(n) time but with O(n) space
  /*
   * public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
   * Set<ListNode<Integer>> visited = new HashSet<ListNode<Integer>>();
   * 
   * ListNode<Integer> iter = head; while(iter != null){ if
   * (visited.contains(iter)){ return iter; } visited.add(iter); iter = iter.next;
   * } return null; }
   */

  //Brute force algorithm O(n^2) time, with O(1) space
  /*
   * public static ListNode<Integer> hasCycle (ListNode<Integer> head){
   * ListNode<Integer> iterOuter = head; int nodeVisitedByOuter = 0; while
   * (iterOuter != null){ iterOuter = iterOuter.next; nodeVisitedByOuter++;
   * 
   * int x = nodeVisitedByOuter; ListNode<Integer> iterInner = head; int
   * noOfTimeCurrentNodeIsVisited = 0; while (x > 0){ iterInner = iterInner.next;
   * if (iterInner == iterOuter){ noOfTimeCurrentNodeIsVisited++; } if
   * (noOfTimeCurrentNodeIsVisited == 2){ return iterInner; } x--; }
   * 
   * } return null; }
   */
  //Floyd's Tortoise and Hare algorithm O(n) time and O(1) space
  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
   ListNode<Integer> fast = head;
   ListNode<Integer> slow = head;
   while (fast != null && fast.next!= null){
     fast = fast.next.next;
     slow = slow.next;
     if (fast == slow){
        ListNode<Integer> slow2 = head;
        while (slow != slow2){
          slow = slow.next;
          slow2 = slow2.next;
        }
        return slow2;
     }
   }
   return null;
  }

  @EpiTest(testDataFile = "is_list_cyclic.tsv")
  public static void HasCycleWrapper(TimedExecutor executor, ListNode<Integer> head, int cycleIdx) throws Exception {
    int cycleLength = 0;
    if (cycleIdx != -1) {
      if (head == null) {
        throw new RuntimeException("Can't cycle empty list");
      }
      ListNode<Integer> cycleStart = null, cursor = head;
      while (cursor.next != null) {
        if (cursor.data == cycleIdx) {
          cycleStart = cursor;
        }
        cursor = cursor.next;
        if (cycleStart != null) {
          cycleLength++;
        }
      }
      if (cursor.data == cycleIdx) {
        cycleStart = cursor;
      }
      if (cycleStart == null) {
        throw new RuntimeException("Can't find a cycle start");
      }
      cursor.next = cycleStart;
      cycleLength++;
    }

    ListNode<Integer> result = executor.run(() -> hasCycle(head));

    if (cycleIdx == -1) {
      if (result != null) {
        throw new TestFailure("Found a non-existing cycle");
      }
    } else {
      if (result == null) {
        throw new TestFailure("Existing cycle was not found");
      }

      ListNode<Integer> cursor = result;
      do {
        cursor = cursor.next;
        cycleLength--;
        if (cursor == null || cycleLength < 0) {
          throw new TestFailure("Returned node does not belong to the cycle or is not the closest node to the head");
        }
      } while (cursor != result);

      if (cycleLength != 0) {
        throw new TestFailure("Returned node does not belong to the cycle or is not the closest node to the head");
      }
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "IsListCyclic.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
