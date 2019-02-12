
public class RegExTest {

  public static void main(String[] args) {
    String text1 = "ion.summary.freigabe.pruefungen.abweichende-nutzer.title-text-1;\"Es gibt <span class='bold'>Abweichungen</span> bei Ihren eingegebenen&nbsp;\";\"_FR_ Es gibt <span class='bold'>Abweichungen</span> bei Ihren eingegebenen&nbsp;\"";
    String text2 = "ion.buttons.done;\"Fertig\";";
    System.out.println("Tersting String: " + text1);
    testString(text1);
    System.out.println("Tersting String: " + text2);
    testString(text2);
  }

  private static void testString(String text) {
    for (String val: text.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")) {
      System.out.println("value='" + val.replaceAll("\"(.+)\"", "$1") + "'");
    }
  }
}


