import java.rmi.Naming;
import java.lang.Thread;

public class RaspiRobotClient{
/*
*    * Client program for the RaspiRobot
*       */
public static void main (String[] argv) {
  try {
    //The IP address given in this line is static, as can be accessed provided you are on the CS-WL-2
    //      //network
    RaspiRobotInterface robot =
      (RaspiRobotInterface) Naming.lookup ("rmi://192.168.74.31:1099/RaspiRobot");
    System.out.println (robot.moveForward());
    Thread.sleep(3000);
    System.out.println (robot.stop());
    } catch (Exception e) {
      System.out.println ("RaspiRobotClient exception: " + e);
    }
  }
}
