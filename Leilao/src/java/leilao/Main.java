/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author geovanevinicius
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, RemoteException, AlreadyBoundException, NamingException, JMSException {
        // TODO code application logic here
        //      LocateRegistry.createRegistry(1099);
        meuRegistry mRegistry = new meuRegistry();
        try {
            ClienteLeilao cl = new ClienteLeilao(mRegistry);
            Thread.sleep(5000);
            cl.darNovoLance(50);
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
