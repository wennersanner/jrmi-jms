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

public class JmsPublisher 
{
    private TopicPublisher publisher;
    private TopicSession session;
    private TopicConnection connect;
    public JmsPublisher(String factoryName, String topicName)
    throws JMSException, NamingException 
    {
        Context jndiContext = new InitialContext(); 
        TopicConnectionFactory factory = (TopicConnectionFactory)
        jndiContext.lookup(factoryName);
        Topic topic = (Topic) jndiContext.lookup(topicName); 
        this.connect = factory.createTopicConnection(); 
        this.session = connect.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
        this.publisher = session.createPublisher(topic);
    }
    public void publish(String message) throws JMSException { 
        TextMessage textMsg = this.session.createTextMessage();
        textMsg.setText(message); 
        System.out.println("PUBLISHING MESSAGE: "+ message); 
        this.publisher.publish(textMsg);
    }
    public void close() throws JMSException {
            this.connect.close();
        }
    public static void main(String[] args) throws Exception {
        JmsPublisher publisher;
        if(args.length != 2)
            publisher = new JmsPublisher("ConnectionFactory", "topic/LeilaoStatus");
        else
            publisher = new JmsPublisher(args[0], args[1]);
            publisher.publish("001 Mouse 10/0 1"); 
            publisher.close();
            System.exit(0);
        }
}