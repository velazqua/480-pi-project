package pirmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*  
 *  *  Remote Interface for robotic controls 
 *  */ 

public interface RaspiRobotInterface extends Remote{ 
  /* 
   * *  Remotely invocable methods 
   * */ 

  public String moveForward() throws RemoteException; 
  public String moveForward(int duration) throws RemoteException,InterruptedException; 
  public String moveReverse() throws RemoteException; 
  public String moveReverse(int duration) throws RemoteException,InterruptedException; 
  public String moveLeft() throws RemoteException; 
  public String moveLeft(int duration) throws RemoteException,InterruptedException; 
  public String moveRight() throws RemoteException; 
  public String moveRight(int duration) throws RemoteException,InterruptedException; 
  public String stop() throws RemoteException; 
}
