/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tiago
 */
public class acompanhamentoThread extends Thread implements Runnable{

    private JTextArea jTextArea1;
    private JTextField jTextField1,jTextField2,jTextField3;
    
    public acompanhamentoThread(JTextArea jTextArea1,JTextField jTextField1,JTextField jTextField2,JTextField jTextField3)
    {
        this.jTextArea1=jTextArea1;
        this.jTextField1=jTextField1;
        this.jTextField2=jTextField2;
        this.jTextField3=jTextField3;
    }
    
    @Override
    public void run() {
       meuRegistry mRegistry = new meuRegistry();
        ProdutoLeilao pl = new ProdutoLeilao(1, Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField1.getText()), jTextField2.getText(), Integer.parseInt(jTextField3.getText()));
        Leiloeiro leiloeiro;
        try {
            leiloeiro = new Leiloeiro(pl, mRegistry, "001");
            while (true) {
             //   leiloeiro.publicarLeilao();
         jTextArea1.append(leiloeiro.publicarLeilao());
         jTextArea1.setCaretPosition(jTextArea1.getText().length());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Form_Leiloeiro3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Form_Leiloeiro3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
