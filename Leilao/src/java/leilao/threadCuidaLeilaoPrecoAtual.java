/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import javax.jms.Message;
import javax.jms.MessageListener;
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
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public class threadCuidaLeilaoPrecoAtual extends Thread implements Runnable,MessageListener{
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    private JTextField jTextField1;
    private JTextArea jTextArea;
    private String factoryName = "ConnectionFactory";
    private String topicName = "topic/LeilaoStatus";
    private TopicConnection connect;
    private ClienteLeilao cl;
    private JTextField jTextField2;
    
    public threadCuidaLeilaoPrecoAtual(JTextArea jTextArea) throws NamingException, JMSException, RemoteException, InterruptedException
    {
         Context jndiContext = new InitialContext();
        TopicConnectionFactory factory = (TopicConnectionFactory) jndiContext.lookup(factoryName);
        Topic topic = (Topic) jndiContext.lookup(topicName);
        this.connect = factory.createTopicConnection();
        TopicSession session = connect.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(this);
        connect.start();
        this.jTextArea=jTextArea;
        meuRegistry mRegistry = new meuRegistry();
        cl = new ClienteLeilao(mRegistry);
        this.jTextField2=jTextField2;
    }
    
    /**
     * Ao receber uma mensgem via JMS, a mesma é enviada a tela do Leilao;
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        String text;
        try {
            text = textMsg.getText();
            //Envia a mesnagem recebida para o form
            jTextArea.append(text+"\n");
            //Barra de rolagem automatica
            jTextArea.setCaretPosition(jTextArea.getText().length());
            
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}
