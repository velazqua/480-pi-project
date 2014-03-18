/*
 * Pi RMI.
 * © G J Barnard 2013 - Attribution-NonCommercial-ShareAlike 3.0 Unported - http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB.
 */
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
 * @author G J Barnard
 */
public interface PiInfo extends Remote
{

    public static final String PIINFO_SERVICE = "PiInfoServer";
    public static final int RMIRegistryPort = 2024;
    public static final int ServicePort = 2025;

    public String runSoundTest() throws RemoteException;
    public String getNoise (int seconds) throws RemoteException;
    public String getNoiseInterval (int seconds, int numIntervals) throws RemoteException;

    // TO DO:
    //public JSONObject getNoiseAtTime (int seconds, Date date) throws RemoteException;
}
