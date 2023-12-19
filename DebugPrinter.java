// Utility class to print debug messages from various thread to the console 

import java.time.LocalTime;

public class DebugPrinter {

    public  static synchronized void PrintDebug(String debugString) { // print debug messages
        System.out.println(LocalTime.now() + ":\t" + debugString);
    }

}