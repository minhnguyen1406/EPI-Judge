package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")
  //n is the length of string s
  /*Reasons:  First, we perform n multiply-and-adds to get x from s.
              Then, we perform log(b2)x multiply and modulus-and-division to get the result
              The value x is upper-bouned by b1^n, log(b2)(b1^n) = nlog(b2)b1*/
  //Optimal algorithm O(n(1+log(b2)b1)) time
  public static String convertBase(String numAsString, int b1, int b2) {
    boolean isNegative = numAsString.startsWith("-");
    int numAsInt = 0;
    if (isNegative) {
      numAsInt = conversionToDecimalInt(numAsString, b1, 1);
    } else {
      numAsInt = conversionToDecimalInt(numAsString, b1, 0);
    }
    if (numAsInt == 0) {
      return "0";
    } else {
      StringBuilder sb = new StringBuilder();
      int remainder = 0;
      while (numAsInt != 0) {
        remainder = numAsInt % b2;
        if (Math.abs(remainder) >= 10) {
          sb.append((char) (remainder - 10 + 'A'));
        } else {
          sb.append((char) (remainder + '0'));
        }
        numAsInt /= b2;
      }
      return sb.append(isNegative ? "-" : "").reverse().toString();
    }
  }

  private static int conversionToDecimalInt(String numAsString, int b1, int i) {
    int result = 0;
    for (; i < numAsString.length(); i++) {
      result = result * b1;
      if (Character.isDigit(numAsString.charAt(i))) {
        result = result + (int) numAsString.charAt(i) - (int) '0';
      } else {
        result = result + (int) numAsString.charAt(i) - (int) 'A' + 10;
      }
    }
    return result;
  }

  ////Optimal algorithm O(n(1+log(b2)b1)) time
  /*public static String convertBase(String numAsString, int b1, int b2) {

    boolean isNegative = numAsString.startsWith("-");
    int numAsInt =
        numAsString.substring(isNegative ? 1 : 0)
            .chars()
            .reduce(0,
                    (x, c)
                        -> x * b1 +
                               (Character.isDigit(c) ? c - '0' : c - 'A' + 10));
    return (isNegative ? "-" : "") +
        (numAsInt == 0 ? "0" : constructFromBase(numAsInt, b2));
  }*/

  private static String constructFromBase(int numAsInt, int base) {
    return numAsInt == 0
        ? ""
        : constructFromBase(numAsInt / base, base) +
              (char)(numAsInt % base >= 10 ? 'A' + numAsInt % base - 10
                                           : '0' + numAsInt % base);
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "ConvertBase.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
