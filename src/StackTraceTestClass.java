public class StackTraceTestClass {

  public static void main(String[] args) {
    System.getProperties().list(System.out);
    //System.out.println("Line-Terminator:" + System.getProperty("line.terminator"));
    StackTraceTestClass.alwaysThrowException();
  }

  private static void alwaysThrowException() {
    throw new NullPointerException("NullPointer!");
  }
}
