import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Objects;

public class TestDateTime {

  public static void main(String[] args) {
    testDateTime(null, null, null);
    testDateTime("2016-01-01", null, "2016-01-01");
    testDateTime(null, "2016-01-01", "2016-01-02");
    testDateTime("2016-01-01", "2016-01-01", "2016-01-02");
    testDateTime("2016-01-01", "2015-01-01", "2016-01-01");
    testDateTime("2016-01-01", "2016-03-01", "2016-03-02");

    System.out.println(DateTime.parse("2015-06-27").getMillis());
    System.out.println(DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC().parseDateTime("2015-06-27").getMillis());
    System.out.println(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2015-06-27 12:30:31").getMillis());
  }

  private static void testDateTime(String aaz, String einzug, String expected) {

    DateTime aazDate = aaz == null ? null : DateTime.parse(aaz);
    DateTime einzugDate = einzug == null ? null : DateTime.parse(einzug);
    DateTime expectedDate = expected == null ? null : DateTime.parse(expected);
    DateTime computedDate = getLatestDateTime(aazDate, einzugDate);

    if (Objects.equals(computedDate, expectedDate)) {
      System.out.println("got expected result: " + expectedDate);
    } else {
      throw new IllegalArgumentException("Wrong result! Expected: " + expectedDate + ", got: " + computedDate);
    }
  }


  private static DateTime getLatestDateTime(DateTime anfangAZ, DateTime einzugsDatum) {

    if (anfangAZ != null &&  einzugsDatum != null) {
      return einzugsDatum.equals(anfangAZ) || einzugsDatum.isAfter(anfangAZ) ? einzugsDatum.plusDays(1) : anfangAZ;
    } else if (anfangAZ != null) {
      return anfangAZ;
    } else if (einzugsDatum != null) {
      return einzugsDatum.plusDays(1);
    } else {
      return null;
    }
  }
}
