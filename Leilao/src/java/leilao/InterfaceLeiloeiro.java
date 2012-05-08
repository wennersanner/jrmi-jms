/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public interface InterfaceLeiloeiro extends Remote{
    
    void darLance(String nome,int lance) throws RemoteException;
    int getPrecoAtual() throws RemoteException;
}
