import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    try {
      byte[] fileBytes = Files.readAllBytes(Paths.get("./sample/hello.class"));
      ByteHolder bhold = new ByteHolder(fileBytes);
      Parser parser = new Parser(bhold);
      parser.parse_magic();
      parser.parse_version();
      parser.parse_constantPool();
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }
}
