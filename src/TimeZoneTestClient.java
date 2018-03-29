import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class TimeZoneTestClient {

  public static void main(String[] args) {

    LocalDateTime localDateTime = new LocalDateTime(DateTimeZone.forID("Europe/Berlin"));
    System.out.println("server localDateTime : "
      + localDateTime.toDateTime(DateTimeZone.getDefault()).toDate());
    System.out.println("user's localDateTime : "
      + localDateTime.toDateTime(DateTimeZone.forID("Europe/Berlin"))
      .toDate());
    System.out.println("DateTime: " + new LocalDateTime(DateTimeZone.forID("Europe/Berlin")).toDateTime());
  }
}
