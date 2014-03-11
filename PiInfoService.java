/*
 * Pi RMI.
 * © G J Barnard 2013 - Attribution-NonCommercial-ShareAlike 3.0 Unported - http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB.
 */
package pirmi;

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
 * @author G J Barnard
 */
public class PiInfoService extends UnicastRemoteObject implements PiInfo
{

    public PiInfoService(int port) throws RemoteException
    {
        super(port);
    }

    /*
     * Methods to get the following:
     * java.version
     * os.arch
     * os.name
     * os.version
     * sun.arch.data.model
     * sun.cpu.endian
     * user.name
     * user.home
     * user.dir
     */

    @Override
    public String getNoise (int seconds) throws RemoteException
    {
      // record mp3
      System.out.println("Recording audio now");
      try {
        Process p = Runtime.getRuntime().exec("sox -t alsa plughw:1 recording.mp3 trim 0 " + seconds);
      }
      catch (IOException e) {
        e.printStackTrace();
      }

      try {
        Thread.sleep((seconds+1)*1000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }

      SoundReporter reporter = new SoundReporter("recording.mp3");

      // Clean up
      try {
        //Process p = Runtime.getRuntime().exec("rm -f recording.mp3");
        Process p = Runtime.getRuntime().exec("echo hello");
      }
      catch (IOException e) {
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
