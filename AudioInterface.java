package pirmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AudioInterface extends Remote {
  public Object StartRecord() throws RemoteException; /* will return a json string, use prefered json lib to parse it */
  public Object getMetadata() throws RemoteException; /* will return a json string, return metadata for the last seconds of recording */
  public byte[] StopRecord() throws RemoteException; /* Provide the file in bytes */
}
