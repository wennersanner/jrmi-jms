/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author tiago
 */
public class darLanceleilaoThread extends Thread implements Runnable{

    private JTextField jTextField1;
    private ClienteLeilao cl;
    
    public darLanceleilaoThread(JTextField jTextField1) throws NamingException, JMSException
    {
        this.jTextField1=jTextField1;
        meuRegistry mRegistry = new meuRegistry();
        cl = new ClienteLeilao(mRegistry);
    }
    
    @Override
    public void run() {
            cl.darNovoLance(Integer.parseInt(jTextField1.getText()));
      }

}
