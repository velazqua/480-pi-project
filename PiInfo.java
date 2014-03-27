package pirmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the methods you want your server to provide as a
 * service to the client.
 * 
 * The client will use it as it's RMI connection 'class' and the server
 * will implement it.
 * 
 */
public interface PiInfo extends Remote
{

    public static final String PIINFO_SERVICE = "PiInfoServer";
    public static final int RMIRegistryPort = 2024;
    public static final int ServicePort = 2025;

    public String runSoundTest() throws RemoteException;
    public String getNoise (int seconds) throws RemoteException;
    public String getNoiseInterval (int seconds, int numIntervals) throws RemoteException;
}
