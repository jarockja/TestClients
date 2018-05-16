public class ConstructorTest {

  public static void main(String[] args) {
    try {
      Object instance = Base64Test.class.getDeclaredConstructor(null).newInstance();
      System.out.println("Instance: " + instance);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
