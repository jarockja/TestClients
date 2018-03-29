
public class PropertyTest {

  public static void main(String[] args) {
    System.out.println("Ein kleiner Test für Einstellungen...:");
    System.out.println("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));
    System.out.println("file.encoding: " + System.getProperty("file.encoding"));
  }
}
