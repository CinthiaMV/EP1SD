/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epServidor;

import epCliente.Mensageiro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Gestor extends UnicastRemoteObject implements Mensageiro{
    PartsRepository repositorio = new PartsRepository();
    protected Gestor() throws RemoteException{
        super();
    }
   
    @Override
    public String sucesso() throws RemoteException{
        return "conexao com sucesso";
    }

    @Override
    public void listarp() {
        repositorio.listar();
    }
    
}
