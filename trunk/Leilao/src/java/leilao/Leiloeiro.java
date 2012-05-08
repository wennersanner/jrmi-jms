/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author tiago
 */
public class Leiloeiro {

    private ProdutoLeilao produtoLeilao;
    private meuRegistry meuRegistry;
    private String identificacaoProcesso;
    private JmsPublisher publicarLeilao;
    private InterfaceLeiloeiro refLeiloeiro;
    
    public Leiloeiro(ProdutoLeilao produtoLeilao, meuRegistry mRegistry, String identificacaoProcesso) throws RemoteException {
        this.produtoLeilao = produtoLeilao;
        this.identificacaoProcesso = identificacaoProcesso;
        this.meuRegistry = mRegistry;
        try {
            publicarLeilao = new JmsPublisher("ConnectionFactory", "topic/LeilaoStatus");
        } catch (JMSException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        refLeiloeiro = new ClasseServenteLeiloeiro(this);
        
    }

    public String publicarLeilao() throws RemoteException {
        try {
            meuRegistry.getRegistry().rebind(identificacaoProcesso, refLeiloeiro);
            publicarLeilao.publish(identificacaoProcesso + " " + produtoLeilao.getCodigo() + " " + produtoLeilao.getNome() + " " + produtoLeilao.getPrecoInicial() + "/" + produtoLeilao.getPrecoAtual() + " " + produtoLeilao.getTempoTerminoLeilao());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
            return (identificacaoProcesso + " " + produtoLeilao.getCodigo() + " " + produtoLeilao.getNome() + " " + produtoLeilao.getPrecoInicial() + "/" + produtoLeilao.getPrecoAtual() + " " + produtoLeilao.getTempoTerminoLeilao()+"\n");
    }

    public void verificarLance(int lance) {
        if (lance > produtoLeilao.getPrecoAtual()) {
            produtoLeilao.setPrecoAtual(lance);
            try {
                publicarLeilao.publish(identificacaoProcesso + " " + produtoLeilao.getCodigo() + " " + produtoLeilao.getNome() + " " + produtoLeilao.getPrecoInicial() + "/" + produtoLeilao.getPrecoAtual() + " " + produtoLeilao.getTempoTerminoLeilao());
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, RemoteException, AlreadyBoundException, NamingException, JMSException {
        meuRegistry mRegistry = new meuRegistry();
        ProdutoLeilao pl = new ProdutoLeilao(1, 10, 10, "Mouse", 60);
        Leiloeiro leiloeiro = new Leiloeiro(pl, mRegistry, "001");
        while (true) {
            leiloeiro.publicarLeilao();
            Thread.sleep(2000);
        }

    }
}
