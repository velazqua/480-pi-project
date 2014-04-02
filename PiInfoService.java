import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;

/**
 * This is the class that implements the service served by the server for the
 * client.  It extends UnicastRemoteObject -
 * http://docs.oracle.com/javase/7/docs/api/java/rmi/server/UnicastRemoteObject.html
 * - so that will serve RMI requests and therefore requires to be processed by
 * the 'rmic' tool to create the 'stub'used by the client as the reference to
 * the server - see:
 * http://docs.oracle.com/javase/7/docs/technotes/tools/windows/rmic.html.
 * 
 */
public class PiInfoService extends UnicastRemoteObject implements PiInfo
{

    public PiInfoService(int port) throws RemoteException
    {
        super(port);
    }

    @Override
    public String getNoiseInterval (int seconds, int numIntervals) throws RemoteException
    {
      String jsonString = "{";
      for (int i = 1; i <= numIntervals; i++) {
        String result = this.getNoise(seconds);
        jsonString += " { \"interval\" : " + i + ", \"object\" : ";
        jsonString += result;
        jsonString += " }";
      }
      jsonString += "}";
      return jsonString;
    }

    @Override
    public String getNoise (int seconds) throws RemoteException
    {
      // record mp3
      System.out.println("Recording audio now");
      try {
        Process p = Runtime.getRuntime().exec("sox -t alsa plughw:1 recording.mp3 trim 0 " + seconds);
      }
      catch (IOException e) {
        System.out.println("Problem recording audio");
        e.printStackTrace();
      }

      try {
        Thread.sleep((seconds+1)*1000);
      }
      catch (InterruptedException e) {
        System.out.println("Problem sleeping");
        e.printStackTrace();
      }

      SoundReporter reporter = new SoundReporter("recording.mp3");

      // Clean up
      try {
        //Process p = Runtime.getRuntime().exec("rm -f recording.mp3");
        Process p = Runtime.getRuntime().exec("echo hello");
      }
      catch (IOException e) {
        System.out.println("Problem deleting");
        e.printStackTrace();
      }
      return reporter.getJson();
    }

    @Override
    public String runSoundTest() throws RemoteException
    {
      System.out.println("Running sound test...");
      String output = null;
      try {
        Process p = Runtime.getRuntime().exec("speaker-test -c2 -t wav");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
          System.out.println(line);
          output += line;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return output;
    }
}
