/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author geovanevinicius
 */
public interface InterfaceCliente extends Remote{
    
    void notificacao(String notificacao) throws RemoteException;
    void fimLeilao(String fimLeilao) throws RemoteException; 
    
}
