import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;

public class TestDateTimeSort {

  public static void main(String[] args) {
    TestDateTimeSort client = new TestDateTimeSort();
    client.testSort();
  }

  private void testSort() {
    Nutzer nutzer1 = new Nutzer(new DateTime(2015, 1, 1, 0, 0), "3");
    Nutzer nutzer2 = new Nutzer(new DateTime(2016, 1, 2, 0, 0), "4");
    Nutzer nutzer3 = new Nutzer(new DateTime(2018, 1, 3, 0, 0), "5");
    Nutzer nutzer4 = new Nutzer(new DateTime(2014, 1, 4, 0, 0), "2");
    Nutzer nutzer5 = new Nutzer(null, "1");

    List<Nutzer> nutzerList = newArrayList(nutzer1, nutzer2, nutzer3, nutzer4, nutzer5);
    Optional<Nutzer> nutzer = nutzerList.stream().sorted(comparing(Nutzer::getAuszug, nullsFirst(naturalOrder())).reversed()).findFirst();
    if (nutzer.isPresent()) {
      System.out.println("Nutzer with Folgenummer " + nutzer.get().getFolgeNummer() + " and auszug: " + nutzer.get().getAuszug());
    } else {
      System.out.println("No nutzer found...");
    }
  }


  class Nutzer {
    private DateTime auszug;
    private String folgeNummer;

    public Nutzer(DateTime auszug, String folgeNummer) {
      this.auszug = auszug;
      this.folgeNummer = folgeNummer;
    }

    public DateTime getAuszug() {
      return auszug;
    }

    public String getFolgeNummer() {
      return folgeNummer;
    }
  }
}
