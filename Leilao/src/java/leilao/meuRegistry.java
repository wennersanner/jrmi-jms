/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class meuRegistry {
    
    private Registry loRegistry;
    
    public meuRegistry()
    {
        try {
            loRegistry=LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            Logger.getLogger(meuRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    Registry getRegistry()
    {
        return loRegistry;
    }
}
