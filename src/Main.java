import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    System.out.println("TEST: " + 151231 + ", " + ((long) 151231 / 100));
    String tmp = "test";
    String[] test = {"test", "", null, "test2", "", "test3", "", null};

    // Arrays.stream(test).filter(data -> data != tmp).forEach(System.out::println);

    // Arrays.stream(test).filter(data -> data != null).filter(data -> data != "").forEach(result -> System.out.println(result));

    List<Book> books = Arrays.asList(
        new Book("Fortran", "Ferdinand", 1957),
        new Book("Java in 3 Tagen", "Anton", 2005),
        new Book("Java in 4 Tagen", "Berta", 2005),
        new Book("Filter-Map-Reduce mit Lambdas", "Cäsar", 2014));
    String s = books.stream()
      .filter(b -> b.year >= 2004 )
      .map(b -> b.author )
      //.reduce("", ( s1, s2 ) -> (s1.isEmpty()) ? s2 : s1 + ", " + s2 );
      //.reduce((s1, s2) -> s2).orElse(null);
      .reduce((s1, s2) -> {
        System.out.println("s1: " + s1 + ", s:2: " + s2);
        return "x";
      }).orElse(null);
    System.out.println(s);
  }

  static class Book {
    String title;
    String author;
    int year;

    Book(String title, String author, int year) {
      this.title = title;
      this.author = author;
      this.year = year;
    }
  }
}
