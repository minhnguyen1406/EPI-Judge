package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.

  // k is the order of the largest element in an array, n is the number of
  // elements

  // Brute-force solution, O(nlogn) time and O(n) space
  // @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  // public static int findKthLargest(int k, List<Integer> A) {
  // Collections.sort(A);
  // return A.get(A.size() - k);
  // }

  // minHeap solution, O(nlogk) time and O(k) space
  // @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  // public static int findKthLargest(int k, List<Integer> A) {
  // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  // for (int i = 0; i < A.size(); i++){
  // minHeap.offer(A.get(i));
  // if (minHeap.size() > k )
  // minHeap.poll();
  // }
  // return minHeap.peek();
  //

  //Optimal solution, O(n) time and O(1) space
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {

    return findKth(A, k, (a, b) -> Integer.compare(b, a));
  }

  public static int findKthSmallest(int k, List<Integer> A) {
    return findKth(A, k, (a, b) -> Integer.compare(a, b));
  }

  public static int findKth(List<Integer> A, int k, Comparator<Integer> cmp) {
    int left = 0, right = A.size() - 1;
    Random r = new Random(0);
    while (left <= right) {
      // Generates a random integer in [left, right].
      int pivotIdx = r.nextInt(right - left + 1) + left;
      int newPivotIdx = partitionAroundPivot(left, right, pivotIdx, A, cmp);
      if (newPivotIdx == k - 1) {
        return A.get(newPivotIdx);
      } else if (newPivotIdx > k - 1) {
        right = newPivotIdx - 1;
      } else { // newPivotIdx < k - 1.
        left = newPivotIdx + 1;
      }
    }

    throw new NoSuchElementException("no k-th node in array A");
  }

  private static int partitionAroundPivot(int left, int right, int pivotIdx, List<Integer> A, Comparator<Integer> cmp) {
    int pivotValue = A.get(pivotIdx);
    int newPivotIdx = left;

    Collections.swap(A, pivotIdx, right);
    for (int i = left; i < right; ++i) {
      if (cmp.compare(A.get(i), pivotValue) < 0) {
        Collections.swap(A, i, newPivotIdx++);
      }
    }
    Collections.swap(A, right, newPivotIdx);
    return newPivotIdx;
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "KthLargestInArray.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
