/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
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
    public static void main(String[] args) throws InterruptedException, RemoteException, AlreadyBoundException {
        // TODO code application logic here
        meuRegistry mRegistry=new meuRegistry();
        ProdutoLeilao pl=new ProdutoLeilao(1, 19, 10, "Mouse", 60);
        Leiloeiro leiloeiro=new Leiloeiro(pl, mRegistry, "001");
        leiloeiro.publicarLeilao();
//        try {
//            ClienteLeilao cl=new ClienteLeilao(mRegistry);
////            while(true)
////            {
////            cl.darNovoLance(12);}
//        } catch (NamingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (JMSException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
