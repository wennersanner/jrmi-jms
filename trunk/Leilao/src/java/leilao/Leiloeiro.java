/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
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

    public Leiloeiro(ProdutoLeilao produtoLeilao, meuRegistry meuRegistry, String identificacaoProcesso) {
        this.produtoLeilao = produtoLeilao;
        this.meuRegistry = meuRegistry;
        this.identificacaoProcesso = identificacaoProcesso;
        try {
            publicarLeilao = new JmsPublisher("ConnectionFactory", "topic/LeilaoStatus");
        } catch (JMSException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }

    }

    public void publicarLeilao() {
        InterfaceLeiloeiro refLeiloeiro = new ClasseServenteLeiloeiro(this);
        try {
            meuRegistry.getRegistry().bind(identificacaoProcesso, refLeiloeiro);
            publicarLeilao.publish(identificacaoProcesso + " " + produtoLeilao.getCodigo() + " " + produtoLeilao.getNome() + " " + produtoLeilao.getPrecoInicial() + "/" + produtoLeilao.getPrecoAtual() + " " + produtoLeilao.getTempoTerminoLeilao());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (AlreadyBoundException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
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
}
