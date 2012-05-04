/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author tiago
 */
public interface InterfaceServ extends Remote{
        
         String Hello(String texto) throws RemoteException;
   //      Registry getSN() throws RemoteException;
         
}
