/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, RemoteException, AlreadyBoundException, NamingException, JMSException {
        //Inicializa o menu principal
        Form_MenuInicial fmi=new Form_MenuInicial();
        fmi.setVisible(true);
        
//                meuRegistry mRegistry = new meuRegistry();
//        try {
//            ClienteLeilao cl = new ClienteLeilao(mRegistry);
//            Thread.sleep(7500);
//            cl.darNovoLance(15);
//        } catch (NamingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (JMSException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
