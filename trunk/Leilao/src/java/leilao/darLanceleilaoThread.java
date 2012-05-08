/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import javax.swing.JTextField;
import javax.jms.*;
import javax.naming.NamingException;
/**
 *
 * @author tiago
 */
public class darLanceleilaoThread extends Thread implements Runnable{

    private JTextField jTextField1,edt_codProduto,edt_nome;
    private ClienteLeilao cl;
    
    public darLanceleilaoThread(JTextField jTextField1,JTextField edt_codProduto,JTextField edt_nome) throws NamingException, JMSException
    {
        this.jTextField1=jTextField1;
        meuRegistry mRegistry = new meuRegistry();
        cl = new ClienteLeilao(mRegistry);
        this.edt_codProduto=edt_codProduto;
        this.edt_nome=edt_nome;
    }
    
    @Override
    public void run() {
            cl.darNovoLance(edt_nome.getText(),edt_codProduto.getText(),Integer.parseInt(jTextField1.getText()));
      }

}
