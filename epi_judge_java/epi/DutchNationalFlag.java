package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }
  //n is size of the array
  //Brute force algorithm O(n^2) time
  /*public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    for (int i = 0; i < A.size(); i++){
      for (int j = i + 1; j < A.size(); j++){
        if (A.get(j).ordinal() < pivot.ordinal()){
          Collections.swap(A, i, j);
          break;
        }
      }
    }
    for (int i = A.size() - 1; i >= 0; i--){
      for (int j = i-1; j >= 0; j--){
        if (A.get(j).ordinal() > pivot.ordinal()){
          Collections.swap(A, i, j);  
          break;
        }
      }
    }
  }*/
  //Optimal algorithm O(n) time, O(1) space
  /*public static void dutchFlagPartition(int pivotIndex, List <Color> A){
    Color pivot = A.get(pivotIndex);
    int smaller = 0;
    for (int i = 0; i < A.size(); i++){
      if (A.get(i).ordinal() < pivot.ordinal()){
        Collections.swap(A, smaller++, i);
      }
    } 
    int larger = A.size() - 1;
    for (int i = A.size() - 1; i >= 0; i--){
      if (A.get(i).ordinal() > pivot.ordinal()){
        Collections.swap(A, larger--, i);
      }
    }
  }*/

  //More optimal algorithm O(n) time, O(1) space (unclassified elements)
  public static void dutchFlagPartition(int pivotIndex, List <Color> A){
    Color pivot = A.get(pivotIndex);
    /** 
     * Keep the following invariants during partitioning:
     * bottom group: A.sublist(0, smaller).
     * middle group: A.sublist(smaller, equal).
     * unclassified group: A.sublist(equal, larger).
     * top group: A.sublsit(larger, A.size()).
    */
    int smaller = 0, equal = 0, larger = A.size();
    //Keep iteraing as long as there is an unclassified class
    while (equal < larger){
      //A.get(equal) is the incoming unclassified element.
      if (A.get(equal).ordinal() < pivot.ordinal()){
        Collections.swap(A, smaller++, equal++);
      }
      else if (A.get(equal).ordinal() == pivot.ordinal()){
        equal++;
      }
      else{ //A.get(equal) > pivot
        Collections.swap(A, equal, --larger);
      }
    }
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
