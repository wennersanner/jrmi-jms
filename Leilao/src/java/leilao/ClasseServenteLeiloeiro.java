/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;

/**
 *
 * @author tiago
 */
public class ClasseServenteLeiloeiro implements InterfaceLeiloeiro{

    private Leiloeiro leiloeiro;
    
    public ClasseServenteLeiloeiro(Leiloeiro leiloeiro)
    {
        this.leiloeiro=leiloeiro;
    }
    
    @Override
    public void darLance(int lance) throws RemoteException {
        leiloeiro.verificarLance(lance);
    }
    
    
}
