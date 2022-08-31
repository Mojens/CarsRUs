package dat3.experiments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSanitizerTest {

  @Test
  void simpleSanitizeTest() {
    String result1 = SimpleSanitizer.simpleSanitize("Hello <b>World</b>","b");
    String result2 = SimpleSanitizer.simpleSanitize("Hello <span>World</span>","span");
    assertEquals("Hello World",result1);
    assertEquals("Hello World",result2);
  }

}