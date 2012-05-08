/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author geovanevinicius
 */
public class ClasseServenteLeiloeiro extends UnicastRemoteObject implements InterfaceLeiloeiro{

    private Leiloeiro leiloeiro;
    
    public ClasseServenteLeiloeiro(Leiloeiro leiloeiro) throws  RemoteException
    {
        this.leiloeiro=leiloeiro;
    }
    
    @Override
    public void darLance(int lance) throws RemoteException {
        leiloeiro.verificarLance(lance);
    }

    @Override
    public int getPrecoAtual() throws RemoteException {
        return leiloeiro.getPrecoAtual();
    }
    
}
