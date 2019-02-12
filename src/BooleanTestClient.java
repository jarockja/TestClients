public class BooleanTestClient {

  public static void main(String[] args) {
    System.out.println(" true |  true = " + !(true && true));
    System.out.println("false |  true = " + !(false && true));
    System.out.println(" true | false = " + !(true && false));
    System.out.println("false | false = " + !(false && false));
  }
}
