/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

/**
 *
 * @author geovanevinicius
 */
public class ClasseServenteInterfaceLeilao extends UnicastRemoteObject implements InterfaceCliente{

    private ClienteLeilao clienteLeilao;
    
    public ClasseServenteInterfaceLeilao(ClienteLeilao clienteLeilao) throws RemoteException{
        this.clienteLeilao=clienteLeilao;
    }
    
    @Override
    public void notificacao(String notificacao) throws RemoteException{
        clienteLeilao.notificacao(notificacao);
        System.out.println("NOTIFICACAO : " + notificacao);
    }

    @Override
    public void fimLeilao(String fimLeilao) throws RemoteException{
        JOptionPane.showMessageDialog(null, fimLeilao);  
        System.out.println(fimLeilao);
        

    }
    
}
