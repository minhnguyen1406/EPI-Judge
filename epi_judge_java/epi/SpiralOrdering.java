package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")
  //n is number of elements in the 2-D array
  //Optimal algorithm O(n) time
  /*public static List<Integer> matrixInSpiralOrder(List<List<Integer>> matrix) {
    List<Integer> spiralOrder = new ArrayList <Integer>();
    if (matrix.size() == 0) return spiralOrder;
    if (matrix.size() == 0){
        return spiralOrder;
    }
    int rowFirst = 0;
    int colFirst = 0;
    int rowLast = matrix.size() - 1;
    int colLast = matrix.get(0).size() - 1;

    while (rowFirst <= rowLast && colFirst <= colLast){
        //Right
        for (int i = colFirst; i <= colLast; i++){
            spiralOrder.add(matrix.get(rowFirst).get(i));
        }
        rowFirst++;

        //Down
        for (int i = rowFirst; i <= rowLast; i++){
            spiralOrder.add(matrix.get(i).get(colLast));
        }
        colLast--;

        if (rowFirst <= rowLast){
            //Left
            for (int i = colLast; i >= colFirst; i--){
                spiralOrder.add(matrix.get(rowLast).get(i));
            }
        } 
        rowLast--;

        if (colFirst <= colLast){
            //Up
            for (int i = rowLast; i >= rowFirst; i--){
                spiralOrder.add(matrix.get(i).get(colFirst));
            }   
        }
        colFirst++;
    }
    return spiralOrder;
  }*/
  
  //Another approach O(n) time
  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> matrix) {
    List<Integer> spiralOrder = new ArrayList <Integer>();
    if (matrix.size() == 0) return spiralOrder;
    final int [][] SHIFT = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    int dir = 0, x = 0, y = 0;
    int n = matrix.size();
    boolean[][] seen = new boolean[n][n];
    for (int i = 0; i < n * n; i++){
        spiralOrder.add(matrix.get(x).get(y));
        seen[x][y] = true;
        int nextX = x + SHIFT[dir][0], nextY = y + SHIFT[dir][1];
        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || seen[nextX][nextY]){
            dir = (dir + 1) % 4;
            nextX = x + SHIFT[dir][0];
            nextY = y + SHIFT[dir][1];
        }
        x = nextX;
        y = nextY;
    }
    return spiralOrder;
  }
  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "SpiralOrdering.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }

}
