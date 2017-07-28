import javax.xml.bind.DatatypeConverter;

public class Base64Test {

  public static void main(String[] args) {
    String input = "<html><title>ION</title><body>Test<BR></body></html>";
    System.out.println("converting String: " + input);
    String str = DatatypeConverter.printBase64Binary(input.getBytes());
    System.out.println("Base64-encoded: " + str);
    String res = new String(DatatypeConverter.parseBase64Binary(str));
    System.out.println("Decoded String: " + res);
  }

}
