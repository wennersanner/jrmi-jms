/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author tiago
 */
public interface InterfaceLeiloeiro extends Remote{
    
    void darLance(int lance) throws RemoteException;
}
