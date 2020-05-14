package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  //Brute force algorithm O(2^n) time
  /*public static long reverse(int x) {
    String str = Integer.toString(x);
    String result;
    if (x < 0){
      result = "-";
      for (int i = str.length() - 1; i > 0; i--){
        result += str.charAt(i);
      }
    }
    else{
      result = "";
      for (int i = str.length() - 1; i >= 0; i--){
        result += str.charAt(i);
      }
    }
    return Long.parseLong(result);
  }*/
  //Optimal algorithm O(n) time
  public static long reverse(int x){
    long result = 0;
    while (x != 0){
      result = (result * 10) + (x % 10);
      x /= 10;
    }
    return result;
  }
  
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
