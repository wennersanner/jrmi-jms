/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.Servidor;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class Servidor {
    
    private Registry locateRegistry;
    
    public Servidor(Registry locateRegistry)
    {
        InterfaceServ refServ=new ClasseServenteServ();
        try 
        {
            locateRegistry.bind("001", refServ);
        }
        catch (RemoteException ex) 
        {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (AlreadyBoundException ex) 
        {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
