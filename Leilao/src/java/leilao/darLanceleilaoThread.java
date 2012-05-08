/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.JTextField;

/**
 *
 * @author tiago
 */
public class darLanceleilaoThread extends Thread implements Runnable {

    private JTextField jTextField1;
    
    public darLanceleilaoThread(JTextField jTextField1)
    {
        this.jTextField1=jTextField1;
    }
    
    @Override
    public void run() {
        meuRegistry mRegistry = new meuRegistry();
        try {
            ClienteLeilao cl = new ClienteLeilao(mRegistry);
            try {
                Thread.sleep(7500);
            } catch (InterruptedException ex) {
                Logger.getLogger(darLanceleilaoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.darNovoLance(Integer.parseInt(jTextField1.getText()));
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
