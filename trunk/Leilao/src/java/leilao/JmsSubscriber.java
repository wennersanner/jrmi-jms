/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

/**
 *
 * @author geovanevinicius
 */

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsSubscriber implements MessageListener 
{
    private TopicConnection connect;

    public JmsSubscriber(String factoryName, String topicName)
    throws JMSException, NamingException 
    {
        Context jndiContext = new InitialContext(); 
        TopicConnectionFactory factory = (TopicConnectionFactory) jndiContext.lookup(factoryName);
        Topic topic = (Topic) jndiContext.lookup(topicName); 
        this.connect = factory.createTopicConnection(); 
        TopicSession session = connect.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = session.createSubscriber(topic); 
        subscriber.setMessageListener(this);
        connect.start();
    }
    public void onMessage(Message message) 
    {
        try {
            TextMessage textMsg = (TextMessage) message; String text = textMsg.getText(); 
            System.out.println(text);
        } 
        catch (JMSException ex) 
        {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        if(args.length != 2)
            new JmsSubscriber("ConnectionFactory","topic/LeilaoStatus");
        else
            new JmsSubscriber(args[0], args[1]);
    } 
}