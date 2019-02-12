import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TokenWriter {

  public static void main(String[] args) {
    String fileName = getFileName(args);
    if (StringUtils.isNotEmpty(fileName) && Files.exists(Paths.get(fileName))) {
      processFile(fileName);
    } else {
      System.out.println("File '" + fileName + "' not found...");
      printUsage();
    }
  }

  private static void processFile(String fileName) {
    Path inFile = Paths.get(fileName);
    Path outFile = Paths.get(fileName + ".new");
    try (BufferedReader reader = Files.newBufferedReader(inFile);
         BufferedWriter writer = Files.newBufferedWriter(outFile)) {
      String lgNummer;
      long counter = 0;
      while ((lgNummer = reader.readLine()) != null) {
        if (StringUtils.isNotEmpty(lgNummer) && lgNummer.length() == 10) {
          counter++;
          String token = JWTTestClient.getToken(lgNummer, "", null, false, false);
          writer.write(lgNummer);
          writer.write(" ");
          writer.write(token);
          writer.write("\r\n");
        }
      }
      System.out.println("File '" + fileName + "' successfully processed. " + counter + " lines written...");
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
      ex.printStackTrace();
    }
  }

  private static String getFileName(String[] args) {
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.equals("-f") && args.length > i + 1) {
        return args[i + 1];
      }
    }
    return null;
  }

  private static void printUsage() {
    System.out.println("Usage:");
    System.out.println(" java -cp . TokenWriter -t c:\\test.csv");
  }
}
