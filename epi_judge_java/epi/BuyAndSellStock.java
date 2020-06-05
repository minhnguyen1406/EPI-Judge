package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  //n is the size of the array
  //Brute force algorithm O(n^2) time
  /*public static double computeMaxProfit(List<Double> prices) {
    double max = 0.0;
    for (int i = 0; i < prices.size(); i++){
      for (int j = i + 1; j < prices.size(); j++){
        if ((prices.get(j) - prices.get(i)) > max){
          max = (prices.get(j) - prices.get(i));
        }
      }
    }
    return max;
  }*/

  //Optimal algorithm O(n) time
  /*public static double computeMaxProfit(List<Double> prices) {
    double minPrice = Double.MAX_VALUE, maxProfit = 0.0;
    for (int i = 0; i < prices.size(); i++){
        maxProfit = Math.max(maxProfit, prices.get(i) - minPrice);
        minPrice = Math.min(minPrice, prices.get(i));
    }
    return maxProfit;
  }*/
  //Kadane algorithm O(n) time
  public static double computeMaxProfit(List<Double> prices) {
    double maxCur = 0.0, maxSoFar = 0.0;
    for(int i = 1; i < prices.size(); i++) {
        maxCur = Math.max(0, maxCur += prices.get(i) - prices.get(i-1));
        maxSoFar = Math.max(maxCur, maxSoFar);
    }
    return maxSoFar;
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
