import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;
import static org.apache.commons.lang3.ObjectUtils.compare;

public class TestClass {

  public static void main(String[] args) {

    List<Nutzer> nutzerList = Arrays.asList(
      new Nutzer(1, DateTime.parse("2013-01-01")),
      new Nutzer(2, null),
      new Nutzer(3, DateTime.parse("2015-01-01")),
      new Nutzer(4, DateTime.parse("2014-01-01")),
      new Nutzer(5, null)
    );

    System.out.println("With comparator:");
    sortWithComparator(nutzerList);
    System.out.println("With ObjectUtils:");
    sortWithObjectUtils(nutzerList);
  }

  private static void sortWithComparator(List<Nutzer> nutzerList) {
    Collections.sort(nutzerList, (nutzer1, nutzer2) -> {
      if (nutzer1.getAuszugsDatum() == null ) {
        return -1;
      } else if (nutzer2.getAuszugsDatum() == null) {
        return 1;
      } else {
        return nutzer2.getAuszugsDatum().compareTo(nutzer1.getAuszugsDatum());
      }
    });
    nutzerList.forEach(nutzer -> System.out.println(nutzer));
  }

  private static void sortWithObjectUtils(List<Nutzer> nutzerList) {
    sort(nutzerList, (nutzer1, nutzer2) -> compare(nutzer1.getAuszugsDatum(), nutzer2.getAuszugsDatum()));
    nutzerList.forEach(nutzer -> System.out.println(nutzer));
  }


  static class Nutzer {

    private int id;
    private DateTime auszugsDatum;

    public Nutzer(int id, DateTime auszugsDatum) {
      this.id = id;
      this.auszugsDatum = auszugsDatum;
    }

    public int getId() {
      return id;
    }

    public DateTime getAuszugsDatum() {
      return auszugsDatum;
    }

    @Override
    public String toString() {
      return "Nutzer{" +
        "id=" + id +
        ", auszugsDatum=" + auszugsDatum +
        '}';
    }
  }
}
