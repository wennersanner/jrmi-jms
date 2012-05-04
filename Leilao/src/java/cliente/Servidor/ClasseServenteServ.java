/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.Servidor;

import java.rmi.RemoteException;

/**
 *
 * @author tiago
 */
public class ClasseServenteServ /*extends UnicastRemoteObject*/ implements InterfaceServ
{

    @Override
    public String Hello(String texto) throws RemoteException {
        return texto;
    }

    }
