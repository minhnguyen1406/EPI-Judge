package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.List;
public class RefuelingSchedule {
  private static final int MPG = 20;

  // gallons[i] is the amount of gallons in city i, and distances[i] is the distance
  // city i to the next city.
  // Brute-force solution, O(n^2) time and O(1) space
  // public static int findAmpleCity(List<Integer> gallons, List<Integer> distances) {
  //       int n = gallons.size();
  //       int totalTank = 0, curTank = 0;
  //       int starting = 0;
  //       for (int i = 0; i < n; i++){
  //           starting = i;
  //           int cost = distances.get(i) / MPG;
  //           distances.set(i, cost);
  //           if (gallons.get(i) >= distances.get(i)){
  //               totalTank = gallons.get(i);
  //               int j = starting;
  //               for (int k = 0; k < n; k++){
  //                   curTank = totalTank - distances.get(j);
  //                   if (curTank < 0){
  //                       starting = -1;
  //                       break;
  //                   }
  //                   if (j == n-1){
  //                       j = 0;
  //                       totalTank = curTank + gallons.get(j);
  //                   }
  //                   else{
  //                       totalTank = curTank + gallons.get(j+1);
  //                       j++;
  //                   }
  //               }
  //               if (starting != -1) {
  //                   return starting;
  //               }
  //           }
  //       }
  //       return -1;
  // }

  //Optimal solution, One-pass by keep tracking of surplus and deficit of gas available, O(n) time and O(1) space
  public static int findAmpleCity(List<Integer> gallons, List<Integer> distances) {
    int n = gallons.size();
    int surplus = 0, deficit = 0, starting = 0;
    for (int i = 0; i < n; i++){
        distances.set(i, distances.get(i) / MPG);
        surplus += gallons.get(i) - distances.get(i);
        if (surplus < 0){
            starting = i+1;
            deficit += surplus;  
            surplus = 0;
        }
    }
    return (surplus + deficit >= 0) ? starting : -1;  
  }
  @EpiTest(testDataFile = "refueling_schedule.tsv")
  public static void findAmpleCityWrapper(TimedExecutor executor,
                                          List<Integer> gallons,
                                          List<Integer> distances)
      throws Exception {
    int result = executor.run(() -> findAmpleCity(gallons, distances));
    final int numCities = gallons.size();
    int tank = 0;
    for (int i = 0; i < numCities; ++i) {
      int city = (result + i) % numCities;
      tank += gallons.get(city) * MPG - distances.get(city);
      if (tank < 0) {
        throw new TestFailure(String.format("Out of gallons on city %d", city));
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RefuelingSchedule.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
