/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.Servidor.InterfaceServ;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Geovane Ferreira
 */
public class Cliente {

    
    public Cliente(Registry locateRegistry)
    {
        try 
        {
            InterfaceServ refServ = (InterfaceServ) locateRegistry.lookup("001");
            System.out.println(refServ.Hello("Sistemas distribuidos"));
        } 
        catch (RemoteException ex) 
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (NotBoundException ex) 
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
