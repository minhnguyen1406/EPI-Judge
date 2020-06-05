package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
// n is the number of bits in the integer type
//Brute force algorithm O(2^n) time
  /*public static double power(double x, int y) {
    if (x == 0){
      return 0.0;
    }
    if (x == 1 ){
      return 1.0;
    }
    double result = 1.0;

    if (y > 1) {
      for (int i = 0; i < y; i++) {
        result *= x;
      }
    }
    else{
      if (y == 1){
        return x;
      }
      else{
        if (y == 0) {
          return 1.0;
        }
        else {
          for (int i = Math.abs(y); i > 0; i--) {
            result *= result x;
          }
          result = 1 / result;
        }
      }
    }
    return result;
  }*/
  //Optimal algorithm O(n) time
  public static double power (double x, int y){
    double result = 1.0;
    if (y < 0){
      y = -y;
      x = 1.0 / x;
    }
    while (y != 0){
      if ((y & 1) != 0){
        result *= x;
      }
      x *= x;
      y >>>= 1;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
