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
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public class Leiloeiro {

    private ProdutoLeilao produtoLeilao;
    private meuRegistry meuRegistry;
    private String identificacaoProcesso;
    private JmsPublisher publicarLeilao;
    private InterfaceLeiloeiro refLeiloeiro;
    private String nome;
    private InterfaceCliente [] refClientes;
    private int pointerRefClientes=0;
    /**
     * Conecta via JMS
     * Cria a calsse servente do leiloeiro
     * 
     * @param produtoLeilao Produto Leiloado
     * @param mRegistry     meu Registry
     * @param identificacaoProcesso Identificacao do Processo
     * @throws RemoteException 
     */
    
    public Leiloeiro(ProdutoLeilao produtoLeilao, meuRegistry mRegistry, String identificacaoProcesso) throws RemoteException {
        refClientes=new InterfaceCliente[50];
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
    /**
     * Responsavel por publicar o leilao 
     * @return Mensagem JMS que foi postada
     * @throws RemoteException 
     */
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
    /**
     * Responsavel por verificar lance recebido, e atulizar o valor atual
     * caso seja maior que o valor maximo dos lances 
     * @param nome Nome da pessoa que deu o lance
     * @param lance Valor do lance dado
     */
    public void verificarLance(InterfaceCliente refCli,String nome,int lance) throws RemoteException {
        if (lance > produtoLeilao.getPrecoAtual()) {
            produtoLeilao.setPrecoAtual(lance);
            this.nome=nome;
            refClientes[pointerRefClientes]= refCli;
            pointerRefClientes++;
            for(int i=0;i<refClientes.length;i++)
            {
                refClientes[i].notificacao(identificacaoProcesso + " " + produtoLeilao.getCodigo() + " " + produtoLeilao.getNome() + " " + produtoLeilao.getPrecoInicial() + "/" + produtoLeilao.getPrecoAtual() + " " + produtoLeilao.getTempoTerminoLeilao());
            }
            try {
                publicarLeilao.publish(identificacaoProcesso + " " + produtoLeilao.getCodigo() + " " + produtoLeilao.getNome() + " " + produtoLeilao.getPrecoInicial() + "/" + produtoLeilao.getPrecoAtual() + " " + produtoLeilao.getTempoTerminoLeilao());
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
            
        }
    }
    /**
     * Publica o fim do leilao com o nome do vencedor, produto e valor negociado 
     * @throws JMSException 
     */
    public void publicarFimLeilao() throws JMSException, RemoteException
    {
         publicarLeilao.publish("O vencedor e "+this.nome+" "+produtoLeilao.getNome()+" "+produtoLeilao.getPrecoInicial()+"/"+produtoLeilao.getPrecoAtual());
         for(int i=0;i<refClientes.length;i++)
            {
                refClientes[i].fimLeilao("O vencedor e "+this.nome+" "+produtoLeilao.getNome()+" "+produtoLeilao.getPrecoInicial()+"/"+produtoLeilao.getPrecoAtual());
            }
    }
    /**
     * Retorna nome 
     * @return Nome da pessoa de maior lance dado 
     */
    public String getNome()
    {
        return nome;
    }
    /**
     * Retorna preco atual
     * @return Retora o maior lance dado
     */
    public int getPrecoAtual()
    {
        return produtoLeilao.getPrecoAtual();
    }
    /**
     * Fechar Publish
     */
    public void closePublish()
    {
        try {
            publicarLeilao.close();
        } catch (JMSException ex) {
            Logger.getLogger(Leiloeiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getIdentificacao()
    {
        return identificacaoProcesso;
    }
}
