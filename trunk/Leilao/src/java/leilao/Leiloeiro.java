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
    private String nome;
    
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

    public void verificarLance(String nome,int lance) {
        if (lance > produtoLeilao.getPrecoAtual()) {
            produtoLeilao.setPrecoAtual(lance);
            this.nome=nome;
            try {
                publicarLeilao.publish(identificacaoProcesso + " " + produtoLeilao.getCodigo() + " " + produtoLeilao.getNome() + " " + produtoLeilao.getPrecoInicial() + "/" + produtoLeilao.getPrecoAtual() + " " + produtoLeilao.getTempoTerminoLeilao());
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void publicarFimLeilao() throws JMSException
    {
        publicarLeilao.publish("O vencedor e "+this.nome+" "+produtoLeilao.getNome()+" "+produtoLeilao.getPrecoInicial()+"/"+produtoLeilao.getPrecoAtual());
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public int getPrecoAtual()
    {
        return produtoLeilao.getPrecoAtual();
    }
    public void closePublish()
    {
        try {
            publicarLeilao.close();
        } catch (JMSException ex) {
            Logger.getLogger(Leiloeiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
