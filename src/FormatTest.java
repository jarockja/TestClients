import java.util.regex.Pattern;

public class FormatTest {

  private static Pattern lgNummerPattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{4})(\\d)");

  public static void main(String[] args) {
    formatString("1234567890");
    formatString("0123456789");
    formatString("012345678");
    formatString("abcdefghih");
    formatString("123");
    formatString("abc");
  }

  private static void formatString(String input) {
    System.out.println("input: " + input + ", result: '" + lgNummerPattern.matcher(input).replaceAll("$1-$2-$3/$4") + "'");
  }
}
