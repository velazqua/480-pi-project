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
        if (args.length == 3)
        {
            PiInfo audioClient;
            AudioInterface rmiServer;
            RaspiRobotInterface robot;

            //String serverIPAudio1 = "137.207.74.151:2024";//args[0];
            //String serverIPAudio2 = "198.91.209.29"; //args[1];
            String robotIP = "192.168.74.31:1099"; //args[2];

            // Connect to the servers
            robot = (RaspiRobotInterface) Naming.lookup("rmi://" + robotIP + "/RaspiRobot");
            //rmiServer = (AudioInterface) Naming.lookup("//"+serverIPAudio2+"/Hello");
            //audioClient = (PiInfo) Naming.lookup("rmi://"+serverIPAudio1+"/PiInfoServer");

            for (int k = 0; k < 1 /* 10 */; k++) {
              // Start recording on audio server 1
              //String outputAudio1 = audioClient.getNoise(3);

              // Start recording on audio server 2
              /*
              rmiServer.StartRecord();
              try {
                Thread.sleep(3*1000);
              }
              catch (InterruptedException e) {
                e.printStackTrace();
              }

              rmiServer.StopRecord();
              */
              // Get amplitude from both audio sources
              //JSONObject json1 = new JSONObject(outputAudio1);

              /*
              Object jsonAudioObj = rmiServer.getMetadata();
              JSONObject json2 = new JSONObject(jsonAudioObj.toString());
              */

              //Double amp1 = new Double(json1.get("meanAmplitude").toString());
              //Double amp2 = new Double(json2.get("meanAmplitude").toString());
              //System.out.println("Mean amplitude is: " + amp2);
              Double amp2 = new Double(0.1);
              if (amp2 > 0) {
                robot.moveForward();
              }
              else {
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
            System.err.println("Usage: java -Djava.security.policy=\"./pirmi/Policy\" pirmi.PiInfoClient server name (or server IP)");
        }
    }
}
