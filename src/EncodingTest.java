import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;

public class EncodingTest {

  public static void main(String[] args) {
    String text = "Mit freundlichen Grüßen, <BR> Özgür Ösd€mir und André";
    String base64Default = getBytes(text);
    String base64Utf8 = getBytes(text, "UTF-8");
    System.out.println("Default Charset name        :  " + Charset.defaultCharset().name());
    System.out.println("Example text                :  " + text + "\n");
    System.out.println("Base64 with default charset :  " + base64Default);
    System.out.println("Encoded default charset     :  " + new String(DatatypeConverter.parseBase64Binary(base64Default)) + "\n");
    System.out.println("Base64 with UTF-8           :  " + base64Utf8);
    System.out.println("Encoded utf-8               :  " + new String(DatatypeConverter.parseBase64Binary(base64Utf8)));
  }

  private static String getBytes(String input) {
    return DatatypeConverter.printBase64Binary(input.getBytes());
  }

  private static String getBytes(String input, String charset) {
    return DatatypeConverter.printBase64Binary(input.getBytes(Charset.forName(charset)));
  }
}
