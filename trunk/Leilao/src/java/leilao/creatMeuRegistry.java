/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author geovanevinicius
 */
public class creatMeuRegistry {
    
            public static void main(String[] args) throws RemoteException
            {
                LocateRegistry.createRegistry(1098);
                while(true){}
            }
    
}
