import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import org.json.JSONObject;

public class PiSampleClient
{
    public static void main(String[] args) throws Exception
    {
      PiInfo audioClient = (PiInfo) Naming.lookup("rmi://137.207.74.151:2024/PiInfoServer");

      // Record statistic on audio for 5 seconds
      String outputAudio1 = audioClient.getNoise(5);
      System.out.println(outputAudio1);

      JSONObject json1 = new JSONObject(outputAudio1);

      Double amp1 = new Double(json1.get("meanAmplitude").toString());

      System.out.println("Mean amplitude is: " + amp1);
    }
}
