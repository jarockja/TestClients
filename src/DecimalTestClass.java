import java.math.BigDecimal;

/**
 * Created by jarockja on 24.01.2017.
 */
public class DecimalTestClass {

    public static void main(String[] argv){

      // octal int
      int intValue = 034;  // 28 in decimal
      int six = 06; // Equal to decimal 6
      int seven = 07; // Equal to decimal 7
      int eight = 010; // Equal to decimal 8
      int nine = 011; // Equal to decimal 9
      // hex int
      int hex10 = 0x10;
      int hex16 = 0x16;
      int hex20 = 0x20;
      int hex32 = 0x32;
      int hex33 = 0x33;
      int hexFF = 0xFF;

      System.out.println(new Value("034", intValue));
      System.out.println(new Value("06", six));
      System.out.println(new Value("07", seven));
      System.out.println(new Value("010", eight));
      System.out.println(new Value("011", nine));

      System.out.println(new Value("0x10", hex10));
      System.out.println(new Value("0x16", hex16));
      System.out.println(new Value("0x20", hex20));
      System.out.println(new Value("0x32", hex32));
      System.out.println(new Value("0x33", hex33));
      System.out.println(new Value("0xFF", hexFF));

      System.out.println(String.format("Kein %s-Objekt fuer Liegenschaftsnummer %d gefunden!", "SA727", 123456789L));
      System.out.println(String.format("Kein %s-Objekt fuer Liegenschaftsnummer %s gefunden!", "SA727", "123456789"));
      System.out.println(String.format("%d%02d", 201L, 1L));
      System.out.println(String.format("%d%02d", 201L, 11L));
      System.out.println(String.format("%04d/%d", 1L, 0L));
      System.out.println(String.format("%04d/%d", 1L, 1L));

      System.out.println(String.format("Scale: %f=%d", 12.0, BigDecimal.valueOf(12.12).scale()));
      System.out.println(String.format("Scale: %f=%d", 12.12, BigDecimal.valueOf(12.12).scale()));
      System.out.println(String.format("Scale: %f=%d", 12.12988776463, BigDecimal.valueOf(12.12988776463).scale()));

    }

  }

class Value {
  private String strVal;
  private int intVal;

  public Value(String strVal, int intVal) {
    this.strVal = strVal;
    this.intVal = intVal;
  }

  @Override
  public String toString() {
    return "Value{" +
      "strVal='" + strVal + '\'' +
      ", intVal=" + intVal +
      '}';
  }
}
