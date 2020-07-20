package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.stream.*;

public class StringIntegerInterconversion {
  //n is the number of digits of the integer
  //Optimal algorithm O(n) time
  public static String intToString(int x) {
    boolean isNegative = false;
    if (x < 0) {
      isNegative = true;
    }
    StringBuilder sb = new StringBuilder();
    if (x == 0) {
      sb.append('0');
    } else {
      while (x != 0) {
        sb.append((char) ('0' + Math.abs(x % 10)));
        x /= 10;
      }
    }
    return sb.append(isNegative ? "-" : "").reverse().toString();
  }

  //n is the number of characters in a string
  //Optimal algorithm O(n) time
  /*public static int stringToInt(String s) {
    int result = 0;
    if (s.charAt(0) == '-') {
      for (int i = 1; i < s.length(); i++) {
        result = (result * 10) - ((int) s.charAt(i) - (int) ('0'));
      }
    } else {
      if (s.charAt(0) == '+') {
        for (int i = 1; i < s.length(); i++) {
          result = (result * 10) + ((int) s.charAt(i) - (int) ('0'));
        }
      } else {
        for (int i = 0; i < s.length(); i++) {
          result = (result * 10) + ((int) s.charAt(i) - (int) ('0'));
        }
      }
    }
    return result;
  }*/

    public static int stringToInt(String s) {
      int sign = s.charAt(0) == '-' ? -1 : 1;
      IntStream stream = s.substring((s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0).chars();
      int streamReduced = stream.reduce(0, (runningSum, c) -> runningSum * 10 + c - '0'); 
      return sign * streamReduced;
    }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "StringIntegerInterconversion.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
