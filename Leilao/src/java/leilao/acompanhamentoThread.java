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
import javax.jms.JMSException;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Theard responsavel da parte do leiloeiro;
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */

public class acompanhamentoThread extends Thread implements Runnable {

    private JTextArea jTextArea1;
    private JTextField jTextField1, jTextField2, jTextField3,edt_CodProd;
    private boolean continuaLeilao=true;
    /**
     * Dados necessarios para iniciar o leilao
     * @param jTextArea1  Caixa de acompanhamento do leilão com os lances dados
     * @param jTextField1 Valor Inicial
     * @param jTextField2 Nome do Produto
     * @param jTextField3 Tempo de duracao
     * @param edt_CodProd Codigo produto
     */
    public acompanhamentoThread(JTextArea jTextArea1, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTextField edt_CodProd) {
        this.jTextArea1 = jTextArea1;
        this.jTextField1 = jTextField1;
        this.jTextField2 = jTextField2;
        this.jTextField3 = jTextField3;
        this.edt_CodProd=edt_CodProd;
    }

    @Override
    /**
     * Responsavel por criar o leilao atraves de leiloeiro;
     * Verifica o tempo do leilao; 
     * Publicar o fim do leilao;
     */
    public void run() {
        meuRegistry mRegistry = new meuRegistry();
        ProdutoLeilao pl = new ProdutoLeilao(1, Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField1.getText()), jTextField2.getText(), Integer.parseInt(jTextField3.getText()));
        Leiloeiro leiloeiro;
        int aux=1;
        try {
            leiloeiro = new Leiloeiro(pl, mRegistry, edt_CodProd.getText());
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
                        mRegistry.getRegistry().unbind(leiloeiro.getIdentificacao());
                    } catch (NotBoundException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccessException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        leiloeiro.publicarFimLeilao();
                    } catch (JMSException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    leiloeiro.closePublish();
                   
                }
            }
            try {
                        mRegistry.getRegistry().unbind(leiloeiro.getIdentificacao());
                        
                    } catch (NotBoundException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccessException ex) {
                        Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
            try {
                leiloeiro.publicarFimLeilao();
            } catch (JMSException ex) {
                Logger.getLogger(acompanhamentoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            leiloeiro.closePublish();
                   

        } catch (RemoteException ex) {
            Logger.getLogger(Form_Leiloeiro3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Responsavel por parar ou nao o leilao
     * @param aux 
     */
    public void setContinuaLeilao(boolean aux)
    {
        this.continuaLeilao=aux;
    }
}
