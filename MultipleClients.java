import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import org.json.JSONObject;

public class MultipleClients
{
    public static void main(String[] args) throws Exception
    {
        if (true)
        {
            PiInfo audioClient;
            RaspiRobotInterface robot;

            String serverIPAudio1 = "137.207.74.151:2024";
            String robotIP = "192.168.74.31:1099";

            // Connect to the servers
            robot = (RaspiRobotInterface) Naming.lookup("rmi://" + robotIP + "/RaspiRobot");
            audioClient = (PiInfo) Naming.lookup("rmi://"+serverIPAudio1+"/PiInfoServer");

            for (int k = 0; k < 10; k++) {
              // Start recording on audio server 1
              String outputAudio1 = audioClient.getNoise(3);

              JSONObject json1 = new JSONObject(outputAudio1);

              Double amp1 = new Double(json1.get("maxAmplitude").toString());
              System.out.println("At step " + k + " the max amplitude is " + amp1);
              Double threshold = new Double(0.3);
              if (amp1 > threshold) {
                System.out.println("Sound levels are high. Moving robot forwards");
                robot.moveForward();
              }
              else {
                System.out.println("Sound levels are low. Moving robot backwards");
                robot.moveReverse();
              }

              // Move for 2 seconds
              try {
                Thread.sleep(2*1000);
              }
              catch (InterruptedException e) {
                e.printStackTrace();
              }

              robot.stop();
            }
        }
        else
        {
            System.err.println("Usage: java -Djava.security.policy=\"./Policy\" PiSampleClient");
        }
    }
}
