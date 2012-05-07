/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author tiago
 */
public class ClasseServenteLeiloeiro extends UnicastRemoteObject implements InterfaceLeiloeiro{

    public ClasseServenteLeiloeiro() throws RemoteException
    {
        
    }
    @Override
    public void darLance(int lance) throws RemoteException {
        
    }

    
    
}
