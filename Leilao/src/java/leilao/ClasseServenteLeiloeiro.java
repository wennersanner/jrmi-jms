/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Geovane Ferreira
 * @author Tiago 
 * 
 * 
 */
public class ClasseServenteLeiloeiro extends UnicastRemoteObject implements InterfaceLeiloeiro{

    private Leiloeiro leiloeiro;
    
    public ClasseServenteLeiloeiro(Leiloeiro leiloeiro) throws  RemoteException
    {
        this.leiloeiro=leiloeiro;
    }
    
    @Override
    public void darLance(InterfaceCliente refCli,String nome,int lance) throws RemoteException {
        leiloeiro.verificarLance(refCli,nome,lance);
    }

    @Override
    public int getPrecoAtual() throws RemoteException {
        return leiloeiro.getPrecoAtual();
    }
    
}
