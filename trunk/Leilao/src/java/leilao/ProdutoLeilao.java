/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

/**
 *  Produto a ser leiloado
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public class ProdutoLeilao 
{
    private String nome;
    private int codigo,precoInicial,precoAtual,tempoTerminoLeilao;
    /**
     * 
     * @param codigo codigo do produto
     * @param precoInicial preco inicial de negociacao
     * @param precoAtual preco atual do produto
     * @param nome  nome do produto
     * @param tempoTerminoLeilao  tempo para o termino do leilao
     */
    public ProdutoLeilao(int codigo, int precoInicial, int precoAtual,String nome, int tempoTerminoLeilao)
    {
        this.codigo=codigo;
        this.precoAtual=precoAtual;
        this.precoInicial=precoInicial;
        this.nome=nome;
        this.tempoTerminoLeilao=tempoTerminoLeilao;
    }
    /**
     * 
     * @return codigo do produto 
     */
    public int getCodigo()
    {
        return codigo;
    }
    /**
     * 
     * @return preco inicial do produto 
     */
    public int getPrecoInicial()
    {
        return precoInicial;
    }
    /**
     * 
     * @return preco atual do produto 
     */
    public int getPrecoAtual()
    {
        return precoAtual;
    }
    /**
     * 
     * @return tempo de execucao do leilao 
     */
    public int getTempoTerminoLeilao()
    {
        return tempoTerminoLeilao;
    }
    /**
     * 
     * @return nome do produto 
     */
    public String getNome()
    {
        return nome;
    }
    /**
     * 
     * @param precoAtual seta o valor do preco atual
     */
    public void setPrecoAtual(int precoAtual)
    {
        this.precoAtual=precoAtual;
    }
   /**
    * 
    * @param temp seta o tempo do termino do leilao
    */  
    public void setTempoTerminoLeilao(int temp)
    {
        this.tempoTerminoLeilao=temp;
    }
}
