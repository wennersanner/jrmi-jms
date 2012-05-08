/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tiago
 */
public class acompanhamentoThread extends Thread implements Runnable {

    private JTextArea jTextArea1;
    private JTextField jTextField1, jTextField2, jTextField3;
    private boolean continuaLeilao=true;

    public acompanhamentoThread(JTextArea jTextArea1, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3) {
        this.jTextArea1 = jTextArea1;
        this.jTextField1 = jTextField1;
        this.jTextField2 = jTextField2;
        this.jTextField3 = jTextField3;
    }

    @Override
    public void run() {
        meuRegistry mRegistry = new meuRegistry();
        ProdutoLeilao pl = new ProdutoLeilao(1, Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField1.getText()), jTextField2.getText(), Integer.parseInt(jTextField3.getText()));
        Leiloeiro leiloeiro;
        int aux=1;
        try {
            leiloeiro = new Leiloeiro(pl, mRegistry, "001");
            while (continuaLeilao) {
                jTextArea1.append(leiloeiro.publicarLeilao());
                jTextArea1.setCaretPosition(jTextArea1.getText().length());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Form_Leiloeiro3.class.getName()).log(Level.SEVERE, null, ex);
                }
                aux++;
                if(aux==50)
                {
                    pl.setTempoTerminoLeilao(pl.getTempoTerminoLeilao()-1);
                    aux=0;
                }
                if(pl.getTempoTerminoLeilao()==0)
                {
                    continuaLeilao=false;
                    try {
                        mRegistry.getRegistry().unbind("001");
                    } catch (NotBoundException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccessException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    leiloeiro.closePublish();
                        jTextArea1.append("O vencedor do leilao foi "+leiloeiro.getNome());
                   
                }
            }
            try {
                        mRegistry.getRegistry().unbind("001");
                        
                    } catch (NotBoundException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccessException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    leiloeiro.closePublish();
                      //  InterfaceClienteLeilao refCli=(InterfaceClienteLeilao)mRegistry.getRegistry().lookup("G");
                        jTextArea1.append("O vencedor do leilao foi "+leiloeiro.getNome());
                       // refCli.getNome();
                   

        } catch (RemoteException ex) {
            Logger.getLogger(Form_Leiloeiro3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setContinuaLeilao(boolean aux)
    {
        this.continuaLeilao=aux;
    }
}
