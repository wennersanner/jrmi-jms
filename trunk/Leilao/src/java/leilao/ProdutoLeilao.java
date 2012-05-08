/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

/**
 *
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public class ProdutoLeilao 
{
    private String nome;
    private int codigo,precoInicial,precoAtual,tempoTerminoLeilao;
    
    public ProdutoLeilao(int codigo, int precoInicial, int precoAtual,String nome, int tempoTerminoLeilao)
    {
        this.codigo=codigo;
        this.precoAtual=precoAtual;
        this.precoInicial=precoInicial;
        this.nome=nome;
        this.tempoTerminoLeilao=tempoTerminoLeilao;
    }
    
    public int getCodigo()
    {
        return codigo;
    }
    
    public int getPrecoInicial()
    {
        return precoInicial;
    }
    
    public int getPrecoAtual()
    {
        return precoAtual;
    }
    
    public int getTempoTerminoLeilao()
    {
        return tempoTerminoLeilao;
    }
    
    public String getNome()
    {
        return nome;
    }

    public void setPrecoAtual(int precoAtual)
    {
        this.precoAtual=precoAtual;
    }
    
    public void setTempoTerminoLeilao(int temp)
    {
        this.tempoTerminoLeilao=temp;
    }
}
