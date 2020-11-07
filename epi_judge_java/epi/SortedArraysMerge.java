package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
  // n is the number of elements, k is the number of input sequences
  // Brute force solution, O(nlogn) time and O(n) space
  // @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  // public static List<Integer> mergeSortedArrays(List<List<Integer>>
  // sortedArrays) {
  // List<Integer> combinedList = new ArrayList<Integer>();
  // for (int i = 0; i < sortedArrays.size(); i++) {
  // List<Integer> singleList = sortedArrays.get(i);
  // for (int j = 0; j < singleList.size(); j++) {
  // combinedList.add(singleList.get(j));
  // }
  // }
  // Collections.sort(combinedList);
  // return combinedList;
  // }

  // Optimal solution, O(nlogk) time and O(k) space
  private static class ArrayEntry {
    public Integer value;
    public Integer arrayId;

    public ArrayEntry(Integer value, Integer arrayId) {
      this.value = value;
      this.arrayId = arrayId;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
    for (List<Integer> array : sortedArrays) {
      iters.add(array.iterator());
    }
    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(),
        (o1, o2) -> Integer.compare(o1.value, o2.value));
    for (int i = 0; i < iters.size(); i++) {
      if (iters.get(i).hasNext())
        minHeap.add(new ArrayEntry(iters.get(i).next(), i));
    }

    List<Integer> results = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      ArrayEntry headEntry = minHeap.poll();
      results.add(headEntry.value);
      if (iters.get(headEntry.arrayId).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(), headEntry.arrayId));
      }
    }
    return results;
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "SortedArraysMerge.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
