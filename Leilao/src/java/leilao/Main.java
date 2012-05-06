/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import cliente.Cliente;
import cliente.Servidor.Servidor;

/**
 *
 * @author geovanevinicius
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        meuRegistry mRegistry=new meuRegistry();
        Servidor s=new Servidor(mRegistry.getRegistry());
        Cliente cl=new Cliente(mRegistry.getRegistry());
        //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa
    }
}
