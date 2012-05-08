/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

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
public class ClienteLeilao implements MessageListener {

    private String factoryName = "ConnectionFactory";
    private String topicName = "topic/LeilaoStatus";
    private TopicConnection connect;
    private meuRegistry meuRegistry;
    private InterfaceLeiloeiro interfaceleiloeiro;
       
    public ClienteLeilao(meuRegistry meuRegistry) throws NamingException, JMSException {
        Context jndiContext = new InitialContext();
        TopicConnectionFactory factory = (TopicConnectionFactory) jndiContext.lookup(factoryName);
        Topic topic = (Topic) jndiContext.lookup(topicName);
        this.connect = factory.createTopicConnection();
        TopicSession session = connect.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(this);
        connect.start();
        this.meuRegistry=meuRegistry;
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        String text;
        try {
            text = textMsg.getText();
            System.out.println(text);
            String pesquisa = "Mouse";
            if (text.matches(".*" + pesquisa + ".*")) {
                try 
                {
                    interfaceleiloeiro=(InterfaceLeiloeiro) meuRegistry.getRegistry().lookup("001");
                }
                catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
public void darNovoLance(int lance)
{
        try {
            interfaceleiloeiro.darLance(lance);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteLeilao.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}