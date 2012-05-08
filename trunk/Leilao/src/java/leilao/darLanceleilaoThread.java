/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.NamingException;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
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
public class darLanceleilaoThread extends Thread implements Runnable,MessageListener {

    private JTextField jTextField1;
    private JTextArea jTextArea;
    private String factoryName = "ConnectionFactory";
    private String topicName = "topic/LeilaoStatus";
    private TopicConnection connect;
    
    public darLanceleilaoThread(JTextField jTextField1,JTextArea jTextArea) throws NamingException, JMSException
    {
        this.jTextField1=jTextField1;
         Context jndiContext = new InitialContext();
        TopicConnectionFactory factory = (TopicConnectionFactory) jndiContext.lookup(factoryName);
        Topic topic = (Topic) jndiContext.lookup(topicName);
        this.connect = factory.createTopicConnection();
        TopicSession session = connect.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(this);
        connect.start();
        this.jTextArea=jTextArea;
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

    @Override
    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        String text;
        try {
            text = textMsg.getText();
            jTextArea.append(text+"\n");
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}
