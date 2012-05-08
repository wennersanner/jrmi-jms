/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public class ClienteLeilao {

    private meuRegistry meuRegistry;
    private InterfaceLeiloeiro interfaceleiloeiro;
   
    public ClienteLeilao(meuRegistry meuRegistry) throws NamingException, JMSException {
        this.meuRegistry = meuRegistry;
        
    }

    public void darNovoLance(String meuNome,String nome,int lance) {
        try {
            interfaceleiloeiro = (InterfaceLeiloeiro) meuRegistry.getRegistry().lookup(nome);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteLeilao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteLeilao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            interfaceleiloeiro.darLance(meuNome,lance);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteLeilao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPreco() throws RemoteException {
            return interfaceleiloeiro.getPrecoAtual();
    }
    
    
}