package dat3.experiments;

import java.util.Arrays;

//Never use anything in here for real
public class SimpleSanitizer {
    public static String simpleSanitize (String s, String targetTag){
    String target = "<"+targetTag+">";
    String targetEnd = "</"+targetTag+">";
      return s.replace(target,"").replace(targetEnd,"");

    }
}

