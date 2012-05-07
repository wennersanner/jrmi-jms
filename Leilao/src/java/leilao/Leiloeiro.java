/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author tiago
 */
public class Leiloeiro {

    private ProdutoLeilao produtoLeilao;
    private Registry meuRegistry;
    private String identificacaoProcesso;
    private JmsPublisher publicarLeilao;

    public Leiloeiro(ProdutoLeilao produtoLeilao, meuRegistry mRegistry, String identificacaoProcesso) throws RemoteException {
         this.produtoLeilao = produtoLeilao;
        this.identificacaoProcesso = identificacaoProcesso;
        try {
            publicarLeilao = new JmsPublisher("ConnectionFactory", "topic/LeilaoStatus");
        } catch (JMSException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
           
    }

    public void publicarLeilao() throws RemoteException {
        InterfaceLeiloeiro refLeiloeiro = new ClasseServenteLeiloeiro();
        try {
            meuRegistry = LocateRegistry.getRegistry();
            meuRegistry.bind(identificacaoProcesso, refLeiloeiro);
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
